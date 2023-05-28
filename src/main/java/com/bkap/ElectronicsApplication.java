package com.bkap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableAsync;

//@SpringBootApplication(scanBasePackages = { "com.bkap" },exclude = {SecurityAutoConfiguration.class})
@SpringBootApplication(scanBasePackages = {"com.bkap"})
@EnableAsync
public class ElectronicsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElectronicsApplication.class, args);
    }

    @Bean
    JsonMessageConverter jsonMessageConverter() {
        return new JsonMessageConverter();
    }

    @Bean
    JavaMailSender javaMailSender() {
        return new JavaMailSenderImpl();
    }
}
