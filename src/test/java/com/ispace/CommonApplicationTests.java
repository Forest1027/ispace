package com.ispace;

import com.ispace.articlemanagement.rest.ArticleController;
import com.ispace.usermanagement.rest.UserInfoController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommonApplicationTests {

    @Autowired
    private ArticleController articleController;

    @Autowired
    private UserInfoController userInfoController;

    @Test
    public void articleContextLoads() {
        assertThat(articleController).isNotNull();
    }

    @Test
    public void userContextLoads() {
        assertThat(userInfoController).isNotNull();
    }
}