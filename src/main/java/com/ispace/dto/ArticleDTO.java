package com.ispace.dto;

import com.ispace.entity.ArticleCategory;
import com.ispace.validgroups.CreateArticle;
import com.ispace.validgroups.UpdateArticle;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class ArticleDTO {

    private int id;

    @NotNull(message = "Title is required", groups = {CreateArticle.class, UpdateArticle.class})
    @NotBlank(message = "Title is required", groups = {CreateArticle.class, UpdateArticle.class})
    private String title;

    @NotNull(message = "Description is required", groups = {CreateArticle.class, UpdateArticle.class})
    @NotBlank(message = "Description is required", groups = {CreateArticle.class, UpdateArticle.class})
    private String description;

    @NotNull(message = "Content is required", groups = {CreateArticle.class, UpdateArticle.class})
    @NotBlank(message = "Content is required", groups = {CreateArticle.class, UpdateArticle.class})
    private String content;

    @Valid
    private ArticleCategory articleCategory;

    private Integer authorId;

    private String authorName;

    private String authorEmail;

    private Date createTime;

    private Date updateTime;

    public ArticleDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArticleCategory getArticleCategory() {
        return articleCategory;
    }

    public void setArticleCategory(ArticleCategory articleCategory) {
        this.articleCategory = articleCategory;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }

    public static class Builder {
        private int id;
        private String title;
        private String description;
        private String content;
        private ArticleCategory articleCategory;
        private Integer authorId;
        private String authorName;
        private String authorEmail;
        private Date createTime;
        private Date updateTime;

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withContent(String content) {
            this.content = content;
            return this;
        }

        public Builder withArticleCategory(ArticleCategory articleCategory) {
            this.articleCategory = articleCategory;
            return this;
        }

        public Builder withAuthorId(Integer authorId) {
            this.authorId = authorId;
            return this;
        }

        public Builder withAuthorName(String authorName) {
            this.authorName = authorName;
            return this;
        }

        public Builder withAuthorEmail(String authorEmail) {
            this.authorEmail = authorEmail;
            return this;
        }

        public Builder withCreateTime(Date createTime) {
            this.createTime = createTime;
            return this;
        }

        public Builder withUpdateTime(Date updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        public ArticleDTO build() {
            ArticleDTO articleDTO = new ArticleDTO();
            articleDTO.setId(id);
            articleDTO.setTitle(title);
            articleDTO.setDescription(description);
            articleDTO.setContent(content);
            articleDTO.setAuthorId(authorId);
            articleDTO.setAuthorName(authorName);
            articleDTO.setAuthorEmail(authorEmail);
            articleDTO.setArticleCategory(articleCategory);
            articleDTO.setCreateTime(createTime);
            articleDTO.setUpdateTime(updateTime);
            return articleDTO;
        }

    }

    @Override
    public String toString() {
        return "ArticleDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", content='" + content + '\'' +
                ", articleCategory=" + articleCategory +
                ", authorId=" + authorId +
                ", authorName='" + authorName + '\'' +
                ", authorEmail='" + authorEmail + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
