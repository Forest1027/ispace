package com.ispace.rest;

import com.ispace.dto.ArticleDTO;
import com.ispace.entity.ArticleCategory;
import com.ispace.service.ArticleService;
import com.ispace.validgroups.CreateArticle;
import com.ispace.validgroups.UpdateArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/articleManagement")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/v1/articles")
    public ResponseEntity<List<ArticleDTO>> getArticleList(@RequestParam int page, @RequestParam int size, @RequestParam(required = false) String search) {
        return new ResponseEntity<>(articleService.getArticleList(page, size, search), HttpStatus.OK);
    }

    @GetMapping("/v1/articleCategories")
    public ResponseEntity<List<ArticleCategory>> getArticleCategoryList(@RequestParam int page, @RequestParam int size) {
        return new ResponseEntity<>(articleService.getArticleCategoryList(page, size), HttpStatus.OK);
    }

    @GetMapping("/v1/articleCategories/hierarchy")
    public ResponseEntity<Map<String, List<ArticleCategory>>> getArticleCategoryListHierarchy() {
        return new ResponseEntity<>(articleService.getArticleCategoryListHierarchy(), HttpStatus.OK);
    }

    @GetMapping("/v1/articles/{id}")
    public ResponseEntity<ArticleDTO> getArticleById(@PathVariable int id) {
        return new ResponseEntity<>(articleService.getArticleById(id), HttpStatus.OK);
    }

    @PostMapping("/v1/articles")
    public ResponseEntity<ArticleDTO> createArticle(@Validated(CreateArticle.class) @RequestBody ArticleDTO articleDTO, @RequestHeader(name="Authorization", required = false) String idToken) {
        return new ResponseEntity<>(articleService.createArticle(articleDTO, idToken), HttpStatus.OK);
    }

    @PutMapping("/v1/articles")
    public ResponseEntity<ArticleDTO> updateArticle(@Validated(UpdateArticle.class) @RequestBody ArticleDTO articleDTO, @RequestHeader(name="Authorization", required = false) String idToken) {
        return new ResponseEntity<>(articleService.updateArticle(articleDTO, idToken), HttpStatus.OK);
    }

    @DeleteMapping("/v1/articles/{id}")
    public ResponseEntity<String> deleteArticleById(@PathVariable int id, @RequestHeader(name="Authorization", required = false) String idToken) {
        return new ResponseEntity<>(articleService.deleteArticleById(id, idToken), HttpStatus.OK);
    }

    @GetMapping("/v1/articles/count/{email}")
    public ResponseEntity<Integer> getArticleCountByAuthor(@PathVariable String email, @RequestHeader(name="Authorization", required = false) String idToken) {
        return new ResponseEntity<>(articleService.getArticleCountByAuthor(email, idToken), HttpStatus.OK);
    }

}
