package com.handchina.yunmart.web.rest.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Set;

/**
 * Created by markfredchen on 9/13/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class UserResource extends BaseResource {

    private String userOID;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String password;

    private String firstName;

    private String lastName;

    private String mobile;

    private String companyName;

    private String userStatus;

    private String avatarFileType;

    @NotEmpty
    private String accountOID;

    private String companyOID;

    @NotEmpty
    private Set<String> roles;

    public String getUserOID() {
        return userOID;
    }

    public void setUserOID(String userOID) {
        this.userOID = userOID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getAvatarFileType() {
        return avatarFileType;
    }

    public void setAvatarFileType(String avatarFileType) {
        this.avatarFileType = avatarFileType;
    }

    public String getAccountOID() {
        return accountOID;
    }

    public void setAccountOID(String accountOID) {
        this.accountOID = accountOID;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getCompanyOID() {
        return companyOID;
    }

    public void setCompanyOID(String companyOID) {
        this.companyOID = companyOID;
    }
}
