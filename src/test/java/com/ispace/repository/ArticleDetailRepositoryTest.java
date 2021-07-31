package com.ispace.repository;

import com.ispace.entity.ArticleCategory;
import com.ispace.entity.ArticleDetail;
import com.ispace.entity.UserInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleDetailRepositoryTest {
    @Autowired
    private ArticleDetailRepository underTest;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private ArticleCategoryRepository articleCategoryRepository;


    @BeforeEach
    void setUp() {
        ArticleCategory articleCategory = new ArticleCategory();
        articleCategory.setCategoryName("testCat");
        articleCategoryRepository.saveAndFlush(articleCategory);
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail("test@gmail.com");
        userInfoRepository.saveAndFlush(userInfo);
        ArticleDetail articleDetail1 = new ArticleDetail.Builder().withId(1).withTitle("title1")
                .withDescription("description1").withArticleCategory(articleCategory).withContent("content")
                .withAuthor(userInfo).withCreateTime(new Date()).withUpdateTime(new Date()).build();
        underTest.saveAndFlush(articleDetail1);

        UserInfo userInfo1 = new UserInfo();
        userInfo1.setEmail("second@gmail.com");
        userInfoRepository.saveAndFlush(userInfo1);
        ArticleDetail articleDetail2 = new ArticleDetail.Builder().withId(2).withTitle("title2")
                .withDescription("description2").withArticleCategory(articleCategory).withContent("content")
                .withAuthor(userInfo1).withCreateTime(new Date()).withUpdateTime(new Date()).build();
        underTest.saveAndFlush(articleDetail2);
    }

    @Test
    void canGetArticleCountByAuthor() {
        // given
        // when
        Integer count = underTest.getArticleCountByAuthor("test@gmail.com");
        // then
        assertThat(count).isEqualTo(1);
    }
}