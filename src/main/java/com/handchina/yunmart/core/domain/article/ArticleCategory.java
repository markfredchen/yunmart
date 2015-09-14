package com.handchina.yunmart.core.domain.article;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Created by markfredchen on 9/12/15.
 */
@Entity
@Table(name = "T_ArticleCategory")
public class ArticleCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long articleCategoryID;

    @NotEmpty
    private String name;

    public Long getArticleCategoryID() {
        return articleCategoryID;
    }

    public void setArticleCategoryID(Long articleCategoryID) {
        this.articleCategoryID = articleCategoryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
