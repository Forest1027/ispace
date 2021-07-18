package com.ispace.articlemanagement.rest;

import com.ispace.articlemanagement.dto.ArticleDTO;
import com.ispace.usermanagement.entity.UserInfo;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class ArticleControllerIT {

    private static String email;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void canGetArticleList() throws Exception {
        this.mockMvc.perform(get("/articleManagement/v1/articles?page=1&size=10")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void canGetArticleCategoryList() throws Exception {
        this.mockMvc.perform(get("/articleManagement/v1/articleCategories?page=1&size=10")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void cnaGetArticleByIdNotFound() throws Exception {
        this.mockMvc.perform(get("/articleManagement/v1/articles/1")).andDo(print()).andExpect(status().isBadRequest()).andExpect(content().string(containsString("not found")));
    }

    @Test
    void cannotCreateArticleWithoutToken() throws Exception {
        ArticleDTO articleDTO = getArticleDTO(email);
        String json = JSONObject.wrap(articleDTO).toString();
        this.mockMvc.perform(post("/articleManagement/v1/articles").contentType(MediaType.APPLICATION_JSON).content(json)).andDo(print()).andExpect(status().isUnauthorized());
    }


    @Test
    void cannotUpdateArticleWithoutToken() throws Exception {
        ArticleDTO articleDTO = getArticleDTO(email);
        String json = JSONObject.wrap(articleDTO).toString();
        this.mockMvc.perform(put("/articleManagement/v1/articles").contentType(MediaType.APPLICATION_JSON).content(json)).andDo(print()).andExpect(status().isUnauthorized());
    }

    @Test
    void cannotDeleteArticleByIdWithoutToken() throws Exception {
        ArticleDTO articleDTO = getArticleDTO(email);
        String json = JSONObject.wrap(articleDTO).toString();
        this.mockMvc.perform(delete("/articleManagement/v1/articles/1")).andDo(print()).andExpect(status().isUnauthorized());
    }


    private static ArticleDTO getArticleDTO(String email) {
        return new ArticleDTO.Builder().withTitle("test").withAuthorEmail(email).build();
    }

    private static UserInfo getUserInfo(String email) {
        UserInfo author = new UserInfo();
        author.setEmail(email);
        return author;
    }

}