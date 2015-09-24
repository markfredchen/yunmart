package com.handchina.yunmart.core.enumeration;

/**
 * Created by markfredchen on 9/18/15.
 */
public enum Industry implements SysEnum {
    AFAF(2001),
    MINING(2002),
    MANUFACTURING(2003),
    ENERGY_SUPPLY(2004),
    CONSTRUCTION(2005),
    TSP(2006),
    ICS(2007),
    RETAILER(2008),
    AR(2009),
    FINANCE(2010),
    HOUSING(2011),
    RENT(2012),
    SCIENCE(2013),
    ENVIRONMENT(2014),
    SERVICE(2015),
    EDU(2016),
    SSASB(2017),
    CSE(2018),
    PMSO(2019);


    private Integer id;

    Industry(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getID() {
        return this.id;
    }

    public static Industry parse(Integer id) {
        Industry industry = null;
        for (Industry i : Industry.values()) {
            if (i.getID().equals(id)) {
                industry = i;
                break;
            }
        }
        return industry;
    }
}
