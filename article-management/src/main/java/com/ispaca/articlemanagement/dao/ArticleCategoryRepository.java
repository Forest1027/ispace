package com.ispaca.articlemanagement.dao;

import com.ispaca.articlemanagement.entity.ArticleCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleCategoryRepository extends JpaRepository<ArticleCategory, Integer> {
}
