package com.example.springmem;

import freemarker.template.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static freemarker.core.TemplateClassResolver.SAFER_RESOLVER;

@SpringBootApplication
public class SpringMemApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringMemApplication.class, args);
    }

}
