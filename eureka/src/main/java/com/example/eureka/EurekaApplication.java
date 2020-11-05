package com.example.eureka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.core.env.Environment;

/**
 * Description:
 * date: 2020/10/29 17:00
 *
 * @author 896951384
 * @since JDK 1.8
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(EnableEurekaServer.class);

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(EurekaApplication.class);
        Environment environment = springApplication.run(args).getEnvironment();
        LOGGER.info("启动成功 !!!");
        LOGGER.info("Eureka地址\t http://127.0.0.1:{}",environment.getProperty("server.port"));
    }
}
