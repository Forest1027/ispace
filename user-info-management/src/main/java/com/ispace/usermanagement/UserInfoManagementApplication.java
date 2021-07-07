package com.ispace.usermanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.ispace.shared", "com.ispace.usermanagement"})
@EnableJpaRepositories({"com.ispace.shared", "com.ispace.usermanagement"})
public class UserInfoManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserInfoManagementApplication.class, args);
    }
}
