package com.ispace.articlemanagement.repository;

import com.ispace.articlemanagement.entity.ArticleCategory;
import org.h2.mvstore.Page;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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