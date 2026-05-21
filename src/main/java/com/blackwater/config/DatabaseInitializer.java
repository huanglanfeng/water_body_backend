package com.blackwater.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
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

    private boolean checkTablesExist() {
        try {
            jdbcTemplate.queryForObject("SELECT 1 FROM user LIMIT 1", Integer.class);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void initializeDatabase() {
        try {
            Resource resource = new ClassPathResource("init.sql");
            if (!resource.exists()) {
                log.error("[DB] 找不到 init.sql 文件");
                return;
            }

            Reader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8);
            String sql = FileCopyUtils.copyToString(reader);
            reader.close();

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
