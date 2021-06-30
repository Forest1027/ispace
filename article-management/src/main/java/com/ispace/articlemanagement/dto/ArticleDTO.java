package com.ispace.articlemanagement.dto;

import com.ispace.articlemanagement.entity.ArticleCategory;

import java.util.Date;

public class ArticleDTO {
    public ArticleDTO() {
    }

    public ArticleDTO(int id, String title, String description, String content, ArticleCategory articleCategory, Integer authorId, String authorName, Date createTime, Date updateTime) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.content = content;
        this.articleCategory = articleCategory;
        this.authorId = authorId;
        this.authorName = authorName;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    private int id;

    private String title;

    private String description;

    private String content;

    private ArticleCategory articleCategory;

    private Integer authorId;

    private String authorName;

    private Date createTime;

    private Date updateTime;

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
}
