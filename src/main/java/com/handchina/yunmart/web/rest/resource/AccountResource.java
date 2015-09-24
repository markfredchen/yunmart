package com.handchina.yunmart.web.rest.resource;

/**
 * Created by markfredchen on 9/18/15.
 */
public class AccountResource extends BaseResource{
    private String accountOID;
    private String name;
    private String ownerEntityOID;

    public String getAccountOID() {
        return accountOID;
    }

    public void setAccountOID(String accountOID) {
        this.accountOID = accountOID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerEntityOID() {
        return ownerEntityOID;
    }

    public void setOwnerEntityOID(String ownerEntityOID) {
        this.ownerEntityOID = ownerEntityOID;
    }
}
