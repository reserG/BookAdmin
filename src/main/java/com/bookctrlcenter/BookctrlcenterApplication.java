package com.bookctrlcenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
@MapperScan("com.bookctrlcenter.dao")
    public class BookctrlcenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookctrlcenterApplication.class, args);
    }

}
