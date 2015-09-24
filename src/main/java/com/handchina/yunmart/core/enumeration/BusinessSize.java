package com.handchina.yunmart.core.enumeration;

/**
 * Created by markfredchen on 9/21/15.
 */
public enum BusinessSize implements SysEnum {
    SMALL(3001), MEDIUM(3002), LARGE(3003), ENTERPRISE(3004);

    private Integer id;

    BusinessSize(Integer id) {
        this.id = id;
    }


    @Override
    public Integer getID() {
        return this.id;
    }

    public static BusinessSize parse(Integer id) {
        BusinessSize bs = null;
        for (BusinessSize businessSize : BusinessSize.values()) {
            if (businessSize.getID().equals(id)) {
                bs = businessSize;
                break;
            }
        }
        return bs;
    }
}
