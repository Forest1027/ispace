package com.ispace.articlemanagement.utils;

import com.ispace.articlemanagement.dto.ArticleDTO;
import com.ispace.articlemanagement.entity.ArticleCategory;
import com.ispace.articlemanagement.entity.ArticleDetail;
import com.ispace.shared.entity.UserInfo;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EntityDtoConvertUtilTest {

    private static String title = "test";

    private static String description = "test description";

    private static String content = "content";

    private static String firstName = "Author";

    private static String lastName = "Hu";

    private static String authorName = firstName + " " + lastName;

    private static String authorEmail = "test@gmail.com";

    @Test
    void canConvertArticleEntityToDTO() {
        // given
        ArticleDetail articleDetail = getArticleDetail();
        // when
        ArticleDTO articleDTO = EntityDtoConvertUtil.convertArticleEntityToDTO(articleDetail);
        // then
        assertThat(articleDTO.getTitle()).isEqualTo(title);
        assertThat(articleDTO.getDescription()).isEqualTo(description);
        assertThat(articleDTO.getContent()).isEqualTo(content);
        assertThat(articleDTO.getAuthorName()).isEqualTo(authorName);
        assertThat(articleDTO.getAuthorEmail()).isEqualTo(authorEmail);
    }

    @Test
    void convertArticleDTOToEntity() {
        // given
        ArticleDTO articleDTO = getArticleDTO();
        // when
        ArticleDetail articleDetail = EntityDtoConvertUtil.convertArticleDTOToEntity(articleDTO);
        // then
        assertThat(articleDetail.getTitle()).isEqualTo(title);
        assertThat(articleDetail.getDescription()).isEqualTo(description);
        assertThat(articleDetail.getContent()).isEqualTo(content);
        assertThat(articleDetail.getAuthor().getEmail()).isEqualTo(authorEmail);
    }

    private static ArticleDTO getArticleDTO() {
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setTitle(title);
        articleDTO.setDescription(description);
        articleDTO.setContent(content);
        articleDTO.setAuthorName(authorName);
        articleDTO.setAuthorEmail(authorEmail);
        return articleDTO;
    }

    private static UserInfo getUserInfo(String email, String firstName, String lastName) {
        UserInfo author = new UserInfo();
        author.setEmail(email);
        author.setFirstName(firstName);
        author.setLastName(lastName);
        return author;
    }

    private static ArticleDetail getArticleDetail() {
        ArticleDetail articleDetail = new ArticleDetail();
        articleDetail.setTitle(title);
        articleDetail.setDescription(description);
        articleDetail.setContent(content);
        articleDetail.setAuthor(getUserInfo(authorEmail, firstName, lastName));
        return articleDetail;
    }
}