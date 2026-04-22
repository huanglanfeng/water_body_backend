package com.blackwater.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * 启动时打印数据库连接信息，方便排查问题
 * 建表请调用 POST /health/init 手动触发
 */
@Component
@Order(1)
@Slf4j
public class DatabaseInitializer implements CommandLineRunner {

    @Autowired
    private DataSource dataSource;

    @Override
    public void run(String... args) throws Exception {
        try (Connection conn = dataSource.getConnection()) {
            boolean valid = conn.isValid(5);
            if (valid) {
                java.sql.ResultSet rs = conn.getMetaData().getTables(null, null, "user", null);
                if (rs.next()) {
                    log.info("[DB] Database connected, tables exist");
                } else {
                    log.info("[DB] Database connected, but tables NOT found! Call POST /health/init to initialize");
                }
            } else {
                log.error("[DB] Database connection invalid");
            }
        } catch (Exception e) {
            log.error("[DB] Database connection failed: {}", e.getMessage());
        }
    }
}
