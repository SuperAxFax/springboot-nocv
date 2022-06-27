package com.fax;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.fax.dao")
public class SpringbootNocvApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootNocvApplication.class, args);
    }

}
