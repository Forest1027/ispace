package com.ispace.repository.custom;

import com.ispace.entity.ArticleCategory;
import com.ispace.entity.ArticleDetail;
import com.ispace.repository.ArticleCategoryRepository;
import com.ispace.repository.ArticleDetailRepository;
import com.ispace.entity.UserInfo;
import com.ispace.repository.UserInfoRepository;
import com.ispace.search.SearchCriteria;
import com.ispace.utils.SearchUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ArticleCustomRepositoryImplTest {

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
    void checkArticleBriefInfoQueried() {
        // given
        String search = "";
        List<SearchCriteria> params = SearchUtil.extractSearchCriteria(search);
        // when
        List<ArticleDetail> articles = underTest.getArticleBrief(Pageable.ofSize(10).withPage(1), params);
        // then
        assertThat(articles.size()).isGreaterThan(0);
        assertThat(articles.get(0).getTitle()).isNotNull();
        assertThat(articles.get(0).getContent()).isNull();
    }

    @Test
    void checkArticleBriefWithCriteriaTitle() {
        // given
        String search = "title==title1";
        List<SearchCriteria> params = SearchUtil.extractSearchCriteria(search);
        // when
        List<ArticleDetail> articles = underTest.getArticleBrief(Pageable.ofSize(10).withPage(1), params);
        // then
        assertThat(articles.size()).isEqualTo(1);
        assertThat(articles.get(0).getTitle()).isEqualTo("title1");
    }

    @Test
    void checkArticleBriefWithCriteriaEmail() {
        // given
        String search = "authorEmail==test@gmail.com";
        List<SearchCriteria> params = SearchUtil.extractSearchCriteria(search);
        // when
        List<ArticleDetail> articles = underTest.getArticleBrief(Pageable.ofSize(10).withPage(1), params);
        // then
        assertThat(articles.size()).isEqualTo(1);
        assertThat(articles.get(0).getAuthor().getEmail()).isEqualTo("test@gmail.com");
    }
}