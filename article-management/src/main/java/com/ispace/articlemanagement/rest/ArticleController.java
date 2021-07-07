package com.ispace.articlemanagement.rest;

import com.ispace.articlemanagement.dto.ArticleDTO;
import com.ispace.articlemanagement.entity.ArticleCategory;
import com.ispace.articlemanagement.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<ArticleDTO>> getArticleList(@RequestParam int page, @RequestParam int size) {
        return new ResponseEntity<>(articleService.getArticleList(page, size), HttpStatus.OK);
    }

    @GetMapping("/v1/articleCategories")
    public ResponseEntity<List<ArticleCategory>> getArticleCategoryList(@RequestParam int page, @RequestParam int size) {
        return new ResponseEntity<>(articleService.getArticleCategoryList(page, size), HttpStatus.OK);
    }

    @GetMapping("/v1/articles/{id}")
    public ResponseEntity<ArticleDTO> getArticleById(@PathVariable int id) {
        return new ResponseEntity<>(articleService.getArticleById(id), HttpStatus.OK);
    }

    @PostMapping("/v1/articles")
    public ResponseEntity<ArticleDTO> createArticle(@RequestBody ArticleDTO articleDTO, @RequestHeader(required = false) String idToken) {
        return new ResponseEntity<>(articleService.createArticle(articleDTO, idToken), HttpStatus.OK);
    }

    @PutMapping("/v1/articles")
    public ResponseEntity<ArticleDTO> updateArticle(@RequestBody ArticleDTO articleDTO, @RequestHeader(required = false) String idToken) {
        return new ResponseEntity<>(articleService.updateArticle(articleDTO, idToken), HttpStatus.OK);
    }

    @DeleteMapping("/v1/articles/{id}")
    public ResponseEntity<String> deleteArticleById(@PathVariable int id, @RequestHeader(required = false) String idToken) {
        return new ResponseEntity<>(articleService.deleteArticleById(id, idToken), HttpStatus.OK);
    }

}
