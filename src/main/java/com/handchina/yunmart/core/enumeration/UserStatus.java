package com.handchina.yunmart.core.enumeration;

/**
 * Created by markfredchen on 9/17/15.
 */
public enum UserStatus implements SysEnum{
    UNAUTHORIZED(1001),
    ENABLED(1002),
    DISABLED(1003),
    INVITED(1004);

    private Integer id;
    UserStatus(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getID() {
        return this.id;
    }

    public static UserStatus parse(Integer id) {
        UserStatus status = null;
        for (UserStatus userStatus : UserStatus.values()) {
            if (userStatus.getID().equals(id)) {
                status = userStatus;
                break;
            }
        }
        return status;
    }
}
