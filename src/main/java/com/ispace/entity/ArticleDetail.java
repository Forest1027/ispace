package com.ispace.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "isp_article_detail")
public class ArticleDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    @NotNull
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ArticleCategory articleCategory;

    @Column(name = "tag")
    private String tag;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "author_email", referencedColumnName = "email")
    private UserInfo author;

    @CreationTimestamp
    @Column(name = "create_time", nullable = false, updatable = false)
    private Date createTime;

    @UpdateTimestamp
    @Column(name = "update_time", nullable = false)
    private Date updateTime;

    public ArticleDetail() {
    }

    public ArticleDetail(int id, String title, String description, ArticleCategory articleCategory, UserInfo author, Date createTime, Date updateTime) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.articleCategory = articleCategory;
        this.author = author;
        this.createTime = createTime;
        this.updateTime = updateTime;
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public UserInfo getAuthor() {
        return author;
    }

    public void setAuthor(UserInfo author) {
        this.author = author;
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

    @Override
    public String toString() {
        return "ArticleDetail{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", content='" + content + '\'' +
                ", articleCategory=" + articleCategory +
                ", tag='" + tag + '\'' +
                ", author=" + author +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleDetail that = (ArticleDetail) o;
        return id == that.id && Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(content, that.content) && Objects.equals(articleCategory, that.articleCategory) && Objects.equals(tag, that.tag) && Objects.equals(author, that.author) && Objects.equals(createTime, that.createTime) && Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, content, articleCategory, tag, author, createTime, updateTime);
    }

    public static class Builder {
        private int id;
        private String title;
        private String description;
        private String content;
        private ArticleCategory articleCategory;
        private String tag;
        private UserInfo author;
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

        public Builder withTag(String tag) {
            this.tag = tag;
            return this;
        }

        public Builder withAuthor(UserInfo author) {
            this.author = author;
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

        public ArticleDetail build() {
            ArticleDetail articleDetail = new ArticleDetail();
            articleDetail.setId(id);
            articleDetail.setTitle(title);
            articleDetail.setContent(content);
            articleDetail.setDescription(description);
            articleDetail.setAuthor(author);
            articleDetail.setArticleCategory(articleCategory);
            articleDetail.setTag(tag);
            articleDetail.setUpdateTime(updateTime);
            articleDetail.setCreateTime(createTime);
            return articleDetail;
        }

    }

}
