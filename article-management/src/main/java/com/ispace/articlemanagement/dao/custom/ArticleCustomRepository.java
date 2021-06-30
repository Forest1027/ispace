package com.ispace.articlemanagement.dao.custom;

import com.ispace.articlemanagement.entity.ArticleDetail;

import java.util.List;

public interface ArticleCustomRepository {
    List<ArticleDetail> getArticleBrief(int pageNumber, int pageSize);
}
