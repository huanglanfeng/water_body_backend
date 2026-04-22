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
                // 检查user表是否存在
                var rs = conn.getMetaData().getTables(null, null, "user", null);
                if (rs.next()) {
                    log.info("✅ 数据库连接正常，表已存在");
                } else {
                    log.info("⚠️ 数据库连接正常，但表不存在！请调用 POST /health/init 初始化数据库");
                }
            } else {
                log.error("❌ 数据库连接失败：连接无效");
            }
        } catch (Exception e) {
            log.error("❌ 数据库连接失败: {}", e.getMessage());
        }
    }
}
