package com.ispace.articlemanagement.dao;

import com.ispace.articlemanagement.entity.ArticleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:3000")
public interface ArticleDetailRepository extends JpaRepository<ArticleDetail, Integer> {
}
