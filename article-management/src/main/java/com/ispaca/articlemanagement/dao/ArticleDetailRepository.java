package com.ispaca.articlemanagement.dao;

import com.ispaca.articlemanagement.entity.ArticleDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleDetailRepository extends JpaRepository<ArticleDetail, Integer> {
}
