package com.ispace.articlemanagement.dao;

import com.ispace.articlemanagement.entity.ArticleDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleDetailRepository extends JpaRepository<ArticleDetail, Integer> {
}
