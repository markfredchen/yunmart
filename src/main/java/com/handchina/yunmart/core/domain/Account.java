package com.handchina.yunmart.core.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * Created by markfredchen on 9/7/15.
 */
@Entity
@Table(name = "YM_Account")
public class Account extends DomainObject{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accountID;

    @NotNull
    private UUID accountOID;

    @NotEmpty
    private String name;

    private UUID ownerEntityOID;

    public Long getAccountID() {
        return accountID;
    }

    public void setAccountID(Long accountID) {
        this.accountID = accountID;
    }

    public UUID getAccountOID() {
        return accountOID;
    }

    public void setAccountOID(UUID accountOID) {
        this.accountOID = accountOID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getOwnerEntityOID() {
        return ownerEntityOID;
    }

    public void setOwnerEntityOID(UUID ownerEntityOID) {
        this.ownerEntityOID = ownerEntityOID;
    }
}
