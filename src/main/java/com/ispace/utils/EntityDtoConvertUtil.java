package com.ispace.utils;

import com.ispace.dto.ArticleDTO;
import com.ispace.entity.ArticleDetail;
import com.ispace.entity.UserInfo;

public class EntityDtoConvertUtil {
    public static ArticleDTO convertArticleEntityToDTO(ArticleDetail articleDetail) {
        if (articleDetail == null) {
            return null;
        } else {
            UserInfo author = articleDetail.getAuthor();
            String authorName = (author.getNickName() == null || author.getNickName().isEmpty()) ? author.getFirstName() + " " + author.getLastName() : author.getNickName();
            return new ArticleDTO.Builder().withId(articleDetail.getId()).withTitle(articleDetail.getTitle())
                    .withDescription(articleDetail.getDescription()).withContent(articleDetail.getContent())
                    .withArticleCategory(articleDetail.getArticleCategory()).withAuthorId(author.getId())
                    .withAuthorName(authorName).withAuthorEmail(author.getEmail())
                    .withUpdateTime(articleDetail.getUpdateTime()).withCreateTime(articleDetail.getCreateTime()).build();
        }
    }

    public static ArticleDetail convertArticleDTOToEntity(ArticleDTO articleDTO) {
        if (articleDTO == null) {
            return null;
        } else {
            UserInfo author = new UserInfo();
            author.setEmail(articleDTO.getAuthorEmail());
            return new ArticleDetail.Builder().withId(articleDTO.getId()).withTitle(articleDTO.getTitle())
                    .withDescription(articleDTO.getDescription()).withArticleCategory(articleDTO.getArticleCategory()).withContent(articleDTO.getContent())
                    .withAuthor(author).withCreateTime(articleDTO.getCreateTime()).withUpdateTime(articleDTO.getUpdateTime()).build();
        }
    }
}
