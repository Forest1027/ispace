package com.ispace.articlemanagement.repository.custom;

import com.ispace.articlemanagement.entity.ArticleCategory;
import com.ispace.articlemanagement.entity.ArticleDetail;
import com.ispace.articlemanagement.repository.ArticleCategoryRepository;
import com.ispace.articlemanagement.repository.ArticleDetailRepository;
import com.ispace.shared.entity.UserInfo;
import com.ispace.shared.repository.UserInfoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleCustomRepositoryImplTest {

    @Autowired
    private ArticleDetailRepository underTest;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private ArticleCategoryRepository articleCategoryRepository;

    @Test
    void checkArticleBriefInfoQueried() {
        // given
        ArticleCategory articleCategory = new ArticleCategory();
        articleCategory.setCategoryName("testCat");
        articleCategoryRepository.saveAndFlush(articleCategory);
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail("test@gmail.com");
        userInfoRepository.saveAndFlush(userInfo);
        ArticleDetail articleDetail = new ArticleDetail(1, "title", "description", articleCategory, userInfo, new Date(), new Date());
        articleDetail.setContent("content");
        underTest.saveAndFlush(articleDetail);
        // when
        List<ArticleDetail> articles = underTest.getArticleBrief(Pageable.ofSize(10).withPage(0));
        // then
        assertThat(articles.size()).isGreaterThan(0);
        assertThat(articles.get(0).getTitle()).isNotNull();
        assertThat(articles.get(0).getContent()).isNotNull();
    }
}