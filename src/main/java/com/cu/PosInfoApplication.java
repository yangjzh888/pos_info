package com.cu;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


import javax.sql.DataSource;

@SpringBootApplication
public class PosInfoApplication extends SpringBootServletInitializer {

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(PosInfoApplication.class);
//    }

    public static void main(String[] args) {
        SpringApplication.run(PosInfoApplication.class, args);
    }

}
