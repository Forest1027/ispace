package com.ispace.articlemanagement.service;

import com.ispace.articlemanagement.dto.ArticleDTO;
import com.ispace.articlemanagement.entity.ArticleCategory;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleService {
    List<ArticleDTO> getArticleList(int page, int size);

    List<ArticleCategory> getArticleCategoryList(int page, int size);

    ArticleDTO getArticleById(int id);

    ArticleDTO createArticle(ArticleDTO articleDTO, String idToken);

    ArticleDTO updateArticle(ArticleDTO articleDTO, String idToken);

    String deleteArticleById(int id, String idToken);
}
