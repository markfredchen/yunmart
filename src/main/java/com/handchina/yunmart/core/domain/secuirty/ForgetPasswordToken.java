package com.handchina.yunmart.core.domain.secuirty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.handchina.yunmart.core.domain.User;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * Created by markfredchen on 9/15/15.
 */
@Entity
@Table(name = "T_ForgetPasswordToken")
public class ForgetPasswordToken {

    @Id
    @Column(unique = true, updatable = false)
    private UUID forgetPasswordToken;

    @NotNull
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    private User user;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Column(nullable = false)
    @JsonIgnore
    private DateTime createdDate = DateTime.now();

    public UUID getForgetPasswordToken() {
        return forgetPasswordToken;
    }

    public void setForgetPasswordToken(UUID forgetPasswordToken) {
        this.forgetPasswordToken = forgetPasswordToken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public DateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(DateTime createdDate) {
        this.createdDate = createdDate;
    }
}
