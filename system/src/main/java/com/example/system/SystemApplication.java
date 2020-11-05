package com.example.system;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.core.env.Environment;

/**
 * Description:
 * date: 2020/10/29 19:31
 *
 * @author 896951384
 * @since JDK 1.8
 */
@SpringBootApplication(scanBasePackages = {"com.example.server","com.example.system"})
@EnableEurekaClient
@MapperScan("com.course.server.mapper")
public class SystemApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(SystemApplication.class);

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(SystemApplication.class);
        Environment environment = springApplication.run(args).getEnvironment();
        LOGGER.info("启动成功 !!!");
        LOGGER.info("System地址\t http://127.0.0.1:{}",environment.getProperty("server.port"));
    }
}
