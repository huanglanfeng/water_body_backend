package com.blackwater.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import javax.sql.DataSource;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;

/**
 * 启动时自动初始化数据库
 * 1. 检查数据库连接
 * 2. 如果表不存在，自动执行 init.sql 创建表和插入示例数据
 */
@Component
@Order(1)
@Slf4j
public class DatabaseInitializer implements CommandLineRunner {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        try (Connection conn = dataSource.getConnection()) {
            boolean valid = conn.isValid(5);
            if (valid) {
                log.info("[DB] 数据库连接成功");
                
                // 检查 user 表是否存在
                boolean tablesExist = checkTablesExist();
                if (tablesExist) {
                    log.info("[DB] 数据表已存在，跳过初始化");
                } else {
                    log.info("[DB] 数据表不存在，开始自动初始化...");
                    initializeDatabase();
                }
            } else {
                log.error("[DB] 数据库连接无效");
            }
        } catch (Exception e) {
            log.error("[DB] 数据库连接失败: {}", e.getMessage());
        }
    }

    /**
     * 检查关键表是否存在
     */
    private boolean checkTablesExist() {
        try {
            String[] tables = {"user", "address_data", "device", "water_data"};
            for (String table : tables) {
                try {
                    jdbcTemplate.queryForObject("SELECT 1 FROM " + table + " LIMIT 1", Integer.class);
                    return true; // 只要有一个表存在就认为已初始化
                } catch (Exception e) {
                    // 表不存在，继续检查下一个
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 执行 SQL 文件初始化数据库
     */
    private void initializeDatabase() {
        try {
            // 读取 init.sql 文件
            Resource resource = new ClassPathResource("init.sql");
            if (!resource.exists()) {
                log.error("[DB] 找不到 init.sql 文件");
                return;
            }

            Reader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8);
            String sql = FileCopyUtils.copyToString(reader);
            reader.close();

            // 分割 SQL 语句并执行
            String[] statements = sql.split(";");
            int successCount = 0;
            int failCount = 0;

            for (String statement : statements) {
                String trimmed = statement.trim();
                if (trimmed.isEmpty() || trimmed.startsWith("--") || trimmed.startsWith("/*")) {
                    continue;
                }
                
                try {
                    jdbcTemplate.execute(trimmed);
                    successCount++;
                } catch (Exception e) {
                    // 忽略 DROP TABLE IF EXISTS 的错误（表可能不存在）
                    if (!trimmed.toUpperCase().contains("DROP TABLE")) {
                        log.warn("[DB] SQL 执行失败: {}", e.getMessage());
                        failCount++;
                    }
                }
            }

            log.info("[DB] 数据库初始化完成: 成功 {} 条, 失败 {} 条", successCount, failCount);
            
        } catch (Exception e) {
            log.error("[DB] 数据库初始化失败: {}", e.getMessage(), e);
        }
    }
}
