package com.mqz.monitor;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@SpringBootApplication
@EnableAdminServer
//@EnableWebSecurity
public class ApplicationMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationMonitorApplication.class, args);
    }

}
