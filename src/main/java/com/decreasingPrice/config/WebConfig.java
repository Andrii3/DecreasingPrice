package com.decreasingPrice.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.decreasingPrice.security.SecurityConfiguration;

@EnableWebMvc
@SpringBootApplication(scanBasePackages = "com.decreasingPrice")
@Import({TilesConfig.class, SecurityConfiguration.class})
public class WebConfig extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(WebConfig.class, args);
    }

}
