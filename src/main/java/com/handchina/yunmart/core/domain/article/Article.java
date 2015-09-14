package com.handchina.yunmart.core.domain.article;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

/**
 * Created by markfredchen on 9/7/15.
 */
@Entity
@Table(name = "T_Article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long articleID;

    @NotNull
    private UUID articleOID;

    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "categoryID", referencedColumnName = "articleCategoryID")
    private ArticleCategory category;

    @NotEmpty
    private String title;

    @NotEmpty
    private String author;

    @NotEmpty
    private String shortDescription;

    @NotEmpty
    private String content;

    private Date createdDate;

    public Long getArticleID() {
        return articleID;
    }

    public void setArticleID(Long articleID) {
        this.articleID = articleID;
    }

    public UUID getArticleOID() {
        return articleOID;
    }

    public void setArticleOID(UUID articleOID) {
        this.articleOID = articleOID;
    }

    public ArticleCategory getCategory() {
        return category;
    }

    public void setCategory(ArticleCategory category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
