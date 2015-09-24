package com.handchina.yunmart.core.domain.common;

import com.handchina.yunmart.core.domain.DomainObject;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by markfredchen on 9/15/15.
 */
@MappedSuperclass
abstract public class Comment extends DomainObject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long commentID;

    @NotEmpty
    protected String content;

    protected int thumbUp;

    protected int thumbDown;

    @NotNull
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Column(nullable = false)
    private DateTime createdDate = DateTime.now();

    @CreatedBy
    @NotNull
    @Column(nullable = false, length = 50, updatable = false)
    private String createdBy;

    public Long getCommentID() {
        return commentID;
    }

    public void setCommentID(Long commentID) {
        this.commentID = commentID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getThumbUp() {
        return thumbUp;
    }

    public void setThumbUp(int thumbUp) {
        this.thumbUp = thumbUp;
    }

    public int getThumbDown() {
        return thumbDown;
    }

    public void setThumbDown(int thumbDown) {
        this.thumbDown = thumbDown;
    }

    public DateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(DateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
