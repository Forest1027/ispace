package com.ispace.repository;

import com.ispace.entity.ArticleCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ArticleCategoryRepositoryTest {

    @Autowired
    private ArticleCategoryRepository underTest;

    @BeforeEach
    void setUp() {
        ArticleCategory ac1 = new ArticleCategory();
        ac1.setId(1);
        ac1.setCategoryName("test1");
        underTest.saveAndFlush(ac1);
        ArticleCategory ac2 = new ArticleCategory();
        ac2.setId(2);
        ac2.setCategoryName("test2");
        ac2.setParentCategoryId(1);
        underTest.saveAndFlush(ac2);
        ArticleCategory ac3 = new ArticleCategory();
        ac3.setId(3);
        ac3.setCategoryName("test3");
        ac3.setParentCategoryId(2);
        underTest.saveAndFlush(ac3);
    }

    @Test
    void checkResultParentIdNotNull() {
        // given
        List<ArticleCategory> articleCategories = underTest.findByParentIdNotNull(Pageable.ofSize(10).withPage(0));
        // when
        boolean hasParentIdNull = articleCategories.stream().anyMatch(articleCategory -> articleCategory.getParentCategoryId() == null);
        // then
        assertThat(hasParentIdNull).isFalse();
        assertThat(articleCategories.size()).isGreaterThan(0);
    }
}