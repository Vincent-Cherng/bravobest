package com.bravo.bravobest;

import com.bravo.bravobest.common.page.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@MapperScan("com.bravo.bravobest.dao")
public class BravobestWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(com.bravo.bravobest.BravobestWebApplication.class, args);
    }

    @Bean
    public PaginationInterceptor getPaginationInterceptor(){
        return new PaginationInterceptor();
    }

}
