package com.ispace.articlemanagement;

import com.ispace.rest.ArticleController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ArticleManagementApplicationTests {
    @Autowired
    private ArticleController articleController;

    @Test
    public void contextLoads() {
        assertThat(articleController).isNotNull();
    }
}
