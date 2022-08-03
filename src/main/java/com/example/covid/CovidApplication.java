package com.example.covid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class CovidApplication {

    public static void main(String[] args) {
        SpringApplication.run(CovidApplication.class, args);
    }

}
