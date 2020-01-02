package com.example.os_be;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
@MapperScan("com.example.os_be.dao")
public class OsBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(OsBeApplication.class, args);
    }

}
