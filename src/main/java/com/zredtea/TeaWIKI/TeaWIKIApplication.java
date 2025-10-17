package com.zredtea.TeaWIKI;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zredtea.TeaWIKI.mapper")
public class TeaWIKIApplication {
    public static void main(String[] args) {
        SpringApplication.run(TeaWIKIApplication.class, args);
    }
}
