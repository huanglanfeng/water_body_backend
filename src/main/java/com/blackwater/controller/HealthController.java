package com.blackwater.controller;

import com.blackwater.config.until.Result;
import com.blackwater.config.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@RestController
@Slf4j
public class HealthController {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private RedisUtil redisUtil;

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String dbUsername;

    @Value("${spring.redis.host}")
    private String redisHost;

    @GetMapping("/health")
    public Result health() {
        Map<String, Object> status = new HashMap<>();
        
        // 数据库连接检测
        try (Connection conn = dataSource.getConnection()) {
            boolean valid = conn.isValid(3);
            status.put("database", valid ? "OK" : "FAIL");
            status.put("dbUrl", dbUrl);
            status.put("dbUser", dbUsername);
            log.info("数据库连接检测: {}", valid ? "成功" : "失败");
        } catch (Exception e) {
            status.put("database", "FAIL: " + e.getMessage());
            status.put("dbUrl", dbUrl);
            status.put("dbUser", dbUsername);
            log.error("数据库连接检测失败: {}", e.getMessage());
        }

        // Redis连接检测
        try {
            redisUtil.set("__health_check__", "1", 5000);
            String val = (String) redisUtil.get("__health_check__");
            redisUtil.del("__health_check__");
            status.put("redis", "1".equals(val) ? "OK" : "FAIL");
            status.put("redisHost", redisHost);
        } catch (Exception e) {
            status.put("redis", "FAIL: " + e.getMessage());
            status.put("redisHost", redisHost);
        }

        return new Result(1, "健康检查", status);
    }

    /**
     * 测试登录（绕过验证码，仅用于调试）
     * 调用方式：POST /health/test-login
     */
    @PostMapping("/health/test-login")
    public Result testLogin() {
        Map<String, Object> result = new HashMap<>();
        try (Connection conn = dataSource.getConnection()) {
            // 获取当前数据库名
            String catalog = conn.getCatalog();
            result.put("current_database", catalog);
            
            // 列出当前数据库的所有表
            java.sql.DatabaseMetaData meta = conn.getMetaData();
            java.sql.ResultSet tables = meta.getTables(catalog, null, "%", new String[]{"TABLE"});
            java.util.List<String> tableList = new java.util.ArrayList<>();
            while (tables.next()) {
                tableList.add(tables.getString("TABLE_NAME"));
            }
            result.put("tables_in_" + catalog, tableList);
            
            // 如果railway库没表，检查所有数据库
            if (tableList.isEmpty()) {
                java.sql.ResultSet dbs = meta.getCatalogs();
                java.util.List<String> dbList = new java.util.ArrayList<>();
                while (dbs.next()) {
                    dbList.add(dbs.getString("TABLE_CAT"));
                }
                result.put("all_databases", dbList);
            }
            // 查询admin用户
            java.sql.PreparedStatement ps = conn.prepareStatement("SELECT account, password, name, role FROM user WHERE account = ?");
            ps.setString(1, "admin");
            java.sql.ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result.put("account", rs.getString("account"));
                result.put("password", rs.getString("password"));
                result.put("name", rs.getString("name"));
                result.put("role", rs.getString("role"));
                result.put("status", "FOUND");
            } else {
                result.put("status", "NOT_FOUND");
                result.put("message", "admin用户不存在");
            }
            return new Result(1, "测试登录", result);
        } catch (Exception e) {
            result.put("status", "ERROR");
            result.put("message", e.getMessage());
            return new Result(0, "测试失败", result);
        }
    }

    /**
     * 手动触发数据库初始化（建表+导入示例数据）
     * 调用方式：POST /health/init
     */
    @PostMapping("/health/init")
    public Result initDatabase() {
        Map<String, Object> result = new HashMap<>();
        try (Connection conn = dataSource.getConnection()) {
            log.info("开始执行数据库初始化（强制模式）...");
            
            // 读取init.sql
            org.springframework.core.io.ClassPathResource resource = new ClassPathResource("init.sql");
            Scanner scanner = new Scanner(resource.getInputStream(), "UTF-8");
            scanner.useDelimiter("\\A");
            String sql = scanner.hasNext() ? scanner.next() : "";
            scanner.close();

            if (sql.isEmpty()) {
                result.put("status", "ERROR");
                result.put("message", "init.sql文件为空");
                return new Result(0, "初始化失败", result);
            }

            // 执行SQL
            try (Statement stmt = conn.createStatement()) {
                // 先执行SET语句
                try { stmt.execute("SET NAMES utf8mb4"); } catch (Exception e) { log.warn("SET NAMES: {}", e.getMessage()); }
                try { stmt.execute("SET FOREIGN_KEY_CHECKS = 0"); } catch (Exception e) { log.warn("SET FK: {}", e.getMessage()); }
                
                String[] statements = sql.split(";");
                int success = 0;
                int failed = 0;
                StringBuilder errors = new StringBuilder();

                for (String statement : statements) {
                    String trimmed = statement.trim();
                    if (trimmed.isEmpty() || trimmed.startsWith("SET ")) {
                        continue;
                    }
                    // 去除开头的SQL注释行
                    String[] lines = trimmed.split("\n");
                    StringBuilder cleanSql = new StringBuilder();
                    for (String line : lines) {
                        String lineTrimmed = line.trim();
                        if (!lineTrimmed.isEmpty() && !lineTrimmed.startsWith("--")) {
                            cleanSql.append(line).append("\n");
                        }
                    }
                    String finalSql = cleanSql.toString().trim();
                    if (finalSql.isEmpty()) {
                        continue;
                    }
                    try {
                        stmt.execute(finalSql);
                        success++;
                    } catch (Exception e) {
                        if (!e.getMessage().contains("already exists")) {
                            failed++;
                            errors.append(trimmed.substring(0, Math.min(trimmed.length(), 50))).append(": ").append(e.getMessage()).append("; ");
                            log.warn("SQL执行失败: {}", e.getMessage());
                        } else {
                            success++; // already exists也算成功
                        }
                    }
                }

                result.put("status", failed == 0 ? "OK" : "PARTIAL");
                result.put("success", success);
                result.put("failed", failed);
                if (errors.length() > 0) {
                    result.put("errors", errors.toString());
                }
                log.info("数据库初始化完成！成功: {}, 失败: {}", success, failed);
                
                // 恢复外键检查
                try { stmt.execute("SET FOREIGN_KEY_CHECKS = 1"); } catch (Exception e) { log.warn("RESTORE FK: {}", e.getMessage()); }
            }

            return new Result(1, "初始化完成", result);
        } catch (Exception e) {
            log.error("数据库初始化失败: {}", e.getMessage());
            result.put("status", "ERROR");
            result.put("message", e.getMessage());
            return new Result(0, "初始化失败", result);
        }
    }
}
