package com.handchina.yunmart.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Created by markfredchen on 9/7/15.
 */
@Entity
@Table(name = "T_User")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long userID;

    @NotNull
    protected UUID userOID;

    @NotNull
    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "accountID", referencedColumnName = "accountID")
    protected Account account;

    @NotEmpty
    protected String username;

    protected String password;

    protected String email;

    protected String fullName;

    protected String title;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
        name = "T_UserAuthority",
        joinColumns = {@JoinColumn(name = "userID", referencedColumnName = "userID")},
        inverseJoinColumns = {@JoinColumn(name = "authorityName", referencedColumnName = "name")})
    protected Set<Authority> rights = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user")
    private Set<PersistentToken> persistentTokens = new HashSet<>();

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public UUID getUserOID() {
        return userOID;
    }

    public void setUserOID(UUID userOID) {
        this.userOID = userOID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Set<Authority> getRights() {
        return rights;
    }

    public void setRights(Set<Authority> rights) {
        this.rights = rights;
    }

    public Set<PersistentToken> getPersistentTokens() {
        return persistentTokens;
    }

    public void setPersistentTokens(Set<PersistentToken> persistentTokens) {
        this.persistentTokens = persistentTokens;
    }
}
