package com.ispace.articlemanagement.dao;

import com.ispace.articlemanagement.entity.ArticleCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:3000")
public interface ArticleCategoryRepository extends JpaRepository<ArticleCategory, Integer> {
}
