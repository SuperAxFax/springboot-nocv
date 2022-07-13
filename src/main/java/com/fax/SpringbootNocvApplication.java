package com.fax;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.fax.dao")
@EnableScheduling//定时启动
@EnableConfigurationProperties
public class SpringbootNocvApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootNocvApplication.class, args);
    }

}
