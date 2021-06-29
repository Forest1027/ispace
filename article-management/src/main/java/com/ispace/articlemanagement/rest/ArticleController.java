package com.ispace.articlemanagement.rest;

import com.ispace.articlemanagement.dto.ArticleDTO;
import com.ispace.articlemanagement.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/articleManagement")
@CrossOrigin(origins = "http://localhost:3000")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/v1/articles")
    public List<ArticleDTO> getArticleList() {
        return null;
    }
}
