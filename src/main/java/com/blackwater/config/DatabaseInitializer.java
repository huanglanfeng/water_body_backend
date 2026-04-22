package com.blackwater.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;

/**
 * 数据库自动初始化：启动时检查关键表是否存在，不存在则自动建表并导入示例数据
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
            // 检查 user 表是否存在
            boolean tableExists = checkTableExists(conn, "user");
            if (tableExists) {
                log.info("数据库表已存在，跳过初始化");
                return;
            }

            log.info("检测到数据库表不存在，开始自动初始化...");

            // 读取并执行初始化SQL
            String sql = readInitSql();
            if (sql == null || sql.isEmpty()) {
                log.error("初始化SQL文件为空");
                return;
            }

            try (Statement stmt = conn.createStatement()) {
                // 按分号分割执行（简单处理，跳过注释行）
                String[] statements = sql.split(";");
                int success = 0;
                int failed = 0;
                for (String statement : statements) {
                    String trimmed = statement.trim();
                    if (trimmed.isEmpty() || trimmed.startsWith("--") || trimmed.startsWith("SET ")) {
                        continue;
                    }
                    try {
                        stmt.execute(trimmed);
                        success++;
                    } catch (Exception e) {
                        // 跳过某些可忽略的错误
                        if (!e.getMessage().contains("already exists")) {
                            failed++;
                            log.warn("SQL执行警告: {}", e.getMessage());
                        }
                    }
                }
                log.info("数据库初始化完成！成功: {}, 失败: {}", success, failed);
            }
        } catch (Exception e) {
            log.error("数据库初始化失败: {}", e.getMessage());
        }
    }

    private boolean checkTableExists(Connection conn, String tableName) throws Exception {
        var rs = conn.getMetaData().getTables(null, null, tableName, null);
        return rs.next();
    }

    private String readInitSql() {
        try {
            // 从classpath读取init.sql
            var is = getClass().getClassLoader().getResourceAsStream("init.sql");
            if (is == null) {
                log.warn("未找到init.sql文件，跳过数据库初始化");
                return null;
            }
            Scanner scanner = new Scanner(is, "UTF-8").useDelimiter("\\A");
            return scanner.hasNext() ? scanner.next() : "";
        } catch (Exception e) {
            log.error("读取init.sql失败: {}", e.getMessage());
            return null;
        }
    }
}
