package com.ymy.hrm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.ymy.hrm.mapper")
@EnableFeignClients
public class CourseApplication9002 {
    public static void main(String[] args) {
        SpringApplication.run(CourseApplication9002.class,args);
    }
}
