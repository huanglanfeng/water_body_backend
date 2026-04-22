package com.blackwater.controller;

import com.blackwater.config.until.Result;
import com.blackwater.config.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

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
}
