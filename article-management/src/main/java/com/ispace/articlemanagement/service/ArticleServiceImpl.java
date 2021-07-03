package com.ispace.articlemanagement.service;

import com.ispace.articlemanagement.dao.ArticleCategoryRepository;
import com.ispace.articlemanagement.dao.ArticleDetailRepository;
import com.ispace.articlemanagement.dto.ArticleDTO;
import com.ispace.articlemanagement.entity.ArticleCategory;
import com.ispace.articlemanagement.entity.ArticleDetail;
import com.ispace.shared.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDetailRepository articleDetailRepository;

    @Autowired
    private ArticleCategoryRepository articleCategoryRepository;

    @Override
    public List<ArticleDTO> getArticleList(int page, int size) {
        List<ArticleDetail> articleDetails = articleDetailRepository.getArticleBrief(Pageable.ofSize(size).withPage(page));
        System.out.println(articleDetails.size());
        return articleDetails.stream()
                .map(articleDetail -> {
                    UserInfo author = articleDetail.getAuthor();
                    String authorName = author.getNickName().isEmpty() ? author.getFirstName() + " " + author.getLastName() : author.getNickName();
                    return new ArticleDTO(
                            articleDetail.getId(),
                            articleDetail.getTitle(),
                            articleDetail.getDescription(),
                            articleDetail.getContent(),
                            articleDetail.getArticleCategory(),
                            author.getId(),
                            authorName,
                            articleDetail.getCreateTime(),
                            articleDetail.getUpdateTime()
                    );
                }).collect(Collectors.toList());
    }

    @Override
    public List<ArticleCategory> getArticleCategoryList(int page, int size) {
        return articleCategoryRepository.findByParentIdNotNull(Pageable.ofSize(size).withPage(page));
    }
}
