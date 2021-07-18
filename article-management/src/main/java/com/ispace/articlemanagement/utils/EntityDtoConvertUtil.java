package com.ispace.articlemanagement.utils;

import com.ispace.articlemanagement.dto.ArticleDTO;
import com.ispace.articlemanagement.entity.ArticleDetail;
import com.ispace.shared.entity.UserInfo;

public class EntityDtoConvertUtil {
    public static ArticleDTO convertArticleEntityToDTO(ArticleDetail articleDetail) {
        if (articleDetail == null) {
            return null;
        } else {
            UserInfo author = articleDetail.getAuthor();
            String authorName = (author.getNickName() == null || author.getNickName().isEmpty()) ? author.getFirstName() + " " + author.getLastName() : author.getNickName();
            return new ArticleDTO(articleDetail.getId(), articleDetail.getTitle(),
                    articleDetail.getDescription(), articleDetail.getContent(),
                    articleDetail.getArticleCategory(), author.getId(), authorName, author.getEmail(), articleDetail.getCreateTime(), articleDetail.getUpdateTime());
        }
    }

    public static ArticleDetail convertArticleDTOToEntity(ArticleDTO articleDTO) {
        if (articleDTO == null) {
            return null;
        } else {
            UserInfo author = new UserInfo();
            author.setEmail(articleDTO.getAuthorEmail());
            ArticleDetail result = new ArticleDetail(articleDTO.getId(), articleDTO.getTitle(), articleDTO.getDescription(), articleDTO.getArticleCategory(), author, articleDTO.getCreateTime(), articleDTO.getUpdateTime());
            result.setContent(articleDTO.getContent());
            return result;
        }
    }
}
