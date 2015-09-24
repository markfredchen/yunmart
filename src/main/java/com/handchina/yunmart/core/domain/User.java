package com.handchina.yunmart.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import com.handchina.yunmart.core.enumeration.UserStatus;

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
public class User extends AbstractAuditingEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userID;

    @NotNull
    private UUID userOID;

    @NotNull
    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "accountID", referencedColumnName = "accountID")
    private Account account;

    @NotEmpty
    @Email
    @Column(unique = true)
    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private String mobile;

    private String companyName;

    private Integer statusID;

    private String avatarFileType;


    @JsonIgnore
    @ManyToMany
    @JoinTable(
        name = "T_UserAuthority",
        joinColumns = {@JoinColumn(name = "userID", referencedColumnName = "userID")},
        inverseJoinColumns = {@JoinColumn(name = "authorityName", referencedColumnName = "name")})
    private Set<Authority> rights = new HashSet<>();

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public UserStatus getStatus() {
        return UserStatus.parse(this.statusID);
    }

    public void setStatus(UserStatus status) {
        this.statusID = status.getID();
    }

    public Integer getStatusID() {
        return statusID;
    }

    public void setStatusID(Integer statusID) {
        this.statusID = statusID;
    }

    public String getAvatarFileType() {
        return avatarFileType;
    }

    public void setAvatarFileType(String avatarFileType) {
        this.avatarFileType = avatarFileType;
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
