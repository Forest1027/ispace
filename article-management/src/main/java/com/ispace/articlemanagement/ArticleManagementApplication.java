package com.ispace.articlemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.ispace.shared", "com.ispace.articlemanagement"})
@EnableJpaRepositories({"com.ispace.shared", "com.ispace.articlemanagement"})
public class ArticleManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(ArticleManagementApplication.class, args);
    }
}
