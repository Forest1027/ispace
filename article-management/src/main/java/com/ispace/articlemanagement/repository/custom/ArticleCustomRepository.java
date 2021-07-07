package com.ispace.articlemanagement.repository.custom;

import com.ispace.articlemanagement.entity.ArticleDetail;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleCustomRepository {
    List<ArticleDetail> getArticleBrief(Pageable pageable);
}
