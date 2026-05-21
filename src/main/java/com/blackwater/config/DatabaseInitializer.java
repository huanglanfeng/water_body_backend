package com.blackwater.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * 启动时检查数据库连接
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
                log.info("[DB] 数据库连接成功");
            } else {
                log.error("[DB] 数据库连接无效");
            }
        } catch (Exception e) {
            log.error("[DB] 数据库连接失败: {}", e.getMessage());
            // 不抛出异常，让应用继续启动
        }
    }
}
