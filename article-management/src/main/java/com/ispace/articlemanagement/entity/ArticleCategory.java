package com.ispace.articlemanagement.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "isp_article_category")
public class ArticleCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "category_name")
    @NotNull
    private String categoryName;

    @Column(name = "parent_category_id")
    private Integer parentCategoryId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(Integer parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    @Override
    public String toString() {
        return "ArticleCategory{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", parentCategoryId=" + parentCategoryId +
                '}';
    }
}
