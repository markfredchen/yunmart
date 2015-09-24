package com.handchina.yunmart.web.rest.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.DateTime;
import org.springframework.hateoas.ResourceSupport;

import javax.validation.constraints.NotNull;

/**
 * Created by markfredchen on 9/15/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ProductCommentResource extends BaseResource {

    private Long commentID;
    private String productOID;
    private String username;
    private String content;
    private int rating;
    private DateTime createdDate;

    public Long getCommentID() {
        return commentID;
    }

    public void setCommentID(Long commentID) {
        this.commentID = commentID;
    }

    public String getProductOID() {
        return productOID;
    }

    public void setProductOID(String productOID) {
        this.productOID = productOID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public DateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(DateTime createdDate) {
        this.createdDate = createdDate;
    }
}
