package com.ispace.articlemanagement.service;

import com.ispace.articlemanagement.dao.ArticleDetailRepository;
import com.ispace.articlemanagement.dto.ArticleDTO;
import com.ispace.articlemanagement.entity.ArticleDetail;
import com.ispace.shared.dao.UserInfoRepository;
import com.ispace.shared.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDetailRepository articleDetailRepository;

    @Override
    public List<ArticleDTO> getArticleList(int pageNumber, int pageSize) {
        List<ArticleDetail> articleDetails = articleDetailRepository.getArticleBrief(pageNumber, pageSize);
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
}
