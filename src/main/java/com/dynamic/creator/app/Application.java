package com.dynamic.creator.app;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Application /*extends SpringBootServletInitializer*/ {

    /*@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }*/

    public static void main(String[] args) {

        SpringApplicationBuilder builder = new SpringApplicationBuilder(Application.class);
        builder.headless(false).run(args);
    }

}