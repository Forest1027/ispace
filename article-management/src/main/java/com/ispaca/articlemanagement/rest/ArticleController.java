package com.ispaca.articlemanagement.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleController {
    @GetMapping("/")
    public String index() {
        return "Hello world";
    }
}
