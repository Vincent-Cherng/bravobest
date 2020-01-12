package com.bravo.bravobest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.bravo.bravobest.dao")
public class BravobestWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(com.bravo.bravobest.BravobestWebApplication.class, args);
    }

}
