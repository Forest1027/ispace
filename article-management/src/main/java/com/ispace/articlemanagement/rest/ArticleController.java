package com.ispace.articlemanagement.rest;

import com.ispace.articlemanagement.dto.ArticleDTO;
import com.ispace.articlemanagement.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articleManagement")
@CrossOrigin(origins = "http://localhost:3000")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/v1/articles")
    public ResponseEntity getArticleList(@RequestParam int page, @RequestParam int size) {
        return new ResponseEntity(articleService.getArticleList(page, size), HttpStatus.OK);
    }

    @GetMapping("/v1/articleCategories")
    public ResponseEntity getArticleCategoryList(@RequestParam int page, @RequestParam int size) {
        return new ResponseEntity(articleService.getArticleCategoryList(page, size), HttpStatus.OK);
    }
}
