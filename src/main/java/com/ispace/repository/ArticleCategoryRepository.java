package com.ispace.repository;

import com.ispace.entity.ArticleCategory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
public interface ArticleCategoryRepository extends JpaRepository<ArticleCategory, Integer> {

    @Query("SELECT a FROM ArticleCategory a where parentCategoryId is not null")
    List<ArticleCategory> findByParentIdNotNull(Pageable pageable);
}
