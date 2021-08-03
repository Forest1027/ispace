package com.ispace.service;

import com.ispace.dto.ArticleDTO;
import com.ispace.entity.ArticleCategory;
import com.ispace.search.SearchCriteria;

import java.util.List;
import java.util.Map;

public interface ArticleService {
    List<ArticleDTO> getArticleList(int page, int size, String search);

    List<ArticleCategory> getArticleCategoryList(int page, int size);

    Map<String, List<ArticleCategory>> getArticleCategoryListHierarchy();

    ArticleDTO getArticleById(int id);

    ArticleDTO createArticle(ArticleDTO articleDTO, String idToken);

    ArticleDTO updateArticle(ArticleDTO articleDTO, String idToken);

    String deleteArticleById(int id, String idToken);

    Integer getArticleCountByAuthor(String email, String idToken);
}
