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
     * 手动触发数据库初始化（建表+导入示例数据）
     * 调用方式：POST /health/init
     */
    @PostMapping("/health/init")
    public Result initDatabase() {
        Map<String, Object> result = new HashMap<>();
        try (Connection conn = dataSource.getConnection()) {
            // 检查表是否已存在
            java.sql.ResultSet rs = conn.getMetaData().getTables(null, null, "user", null);
            if (rs.next()) {
                result.put("status", "SKIP");
                result.put("message", "数据库表已存在，无需初始化");
                return new Result(1, "跳过初始化", result);
            }

            log.info("开始执行数据库初始化...");
            
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
                String[] statements = sql.split(";");
                int success = 0;
                int failed = 0;
                StringBuilder errors = new StringBuilder();

                for (String statement : statements) {
                    String trimmed = statement.trim();
                    if (trimmed.isEmpty() || trimmed.startsWith("--") || trimmed.startsWith("SET ")) {
                        continue;
                    }
                    try {
                        stmt.execute(trimmed);
                        success++;
                    } catch (Exception e) {
                        if (!e.getMessage().contains("already exists")) {
                            failed++;
                            errors.append(e.getMessage()).append("; ");
                            log.warn("SQL执行警告: {}", e.getMessage());
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
