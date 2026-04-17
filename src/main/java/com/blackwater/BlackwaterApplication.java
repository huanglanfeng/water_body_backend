package com.blackwater;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.net.InetAddress;
import java.net.UnknownHostException;


@SpringBootApplication
@MapperScan("com.blackwater.dao")
@EnableSwagger2
@EnableWebMvc
@Slf4j
@EnableCaching

public class BlackwaterApplication {


    public static void main(String[] args) throws UnknownHostException {

        ConfigurableApplicationContext applicationContext = SpringApplication.run(BlackwaterApplication.class, args);
        Environment env = applicationContext.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        log.info("\n----------------------------------------------------------\n\t" +
                "Swagger-bootstrap文档: \thttp://localhost" + ":" + port + "/doc.html\n" +
                "----------------------------------------------------------");
    }


}
