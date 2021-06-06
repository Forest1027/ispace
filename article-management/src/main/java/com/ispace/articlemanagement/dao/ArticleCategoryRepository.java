package com.ispace.articlemanagement.dao;

import com.ispace.articlemanagement.entity.ArticleCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleCategoryRepository extends JpaRepository<ArticleCategory, Integer> {
}
