package com.ispace.articlemanagement.utils;

import com.ispace.articlemanagement.dto.ArticleDTO;
import com.ispace.articlemanagement.entity.ArticleDetail;
import com.ispace.shared.entity.UserInfo;

public class EntityDtoConvertUtil {
    public static ArticleDTO convertArticleEntityToDTO(ArticleDetail articleDetail) {
        UserInfo author = articleDetail.getAuthor();
        String authorName = (author.getNickName()== null || author.getNickName().isEmpty()) ? author.getFirstName() + " " + author.getLastName() : author.getNickName();
        return new ArticleDTO(articleDetail.getId(), articleDetail.getTitle(),
                articleDetail.getDescription(), articleDetail.getContent(),
                articleDetail.getArticleCategory(), author.getId(), authorName, author.getEmail(), articleDetail.getCreateTime(), articleDetail.getUpdateTime());
    }

    public static ArticleDetail convertArticleDTOToEntity(ArticleDTO articleDTO) {
        UserInfo author = new UserInfo();
        author.setEmail(articleDTO.getAuthorEmail());
        return new ArticleDetail(articleDTO.getId(), articleDTO.getTitle(), articleDTO.getDescription(), articleDTO.getContent(), articleDTO.getArticleCategory(), author, articleDTO.getCreateTime(), articleDTO.getUpdateTime());
    }
}
