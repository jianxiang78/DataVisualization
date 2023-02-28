package com.auto17.dataVisualization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Startup class
 */


@Configuration
@SpringBootApplication
@EnableScheduling
public class AppWeb {

    public static void main(String[] args) {
        SpringApplication.run(AppWeb.class, args);
        System.out.println("***********************************************\n");
        System.out.println("**** Data Visualization Start Successfully ****\n");
        System.out.println("***********************************************\n");
    }

}
