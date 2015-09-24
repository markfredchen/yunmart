package com.handchina.yunmart.web.rest.resource;

import java.util.UUID;

/**
 * Created by markfredchen on 9/21/15.
 */
public class CompanyResource extends BaseResource{

    private String companyOID;

    private String overview;

    private Integer industryID;

    private Integer businessSizeID;

    public String getCompanyOID() {
        return companyOID;
    }

    public void setCompanyOID(String companyOID) {
        this.companyOID = companyOID;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Integer getIndustryID() {
        return industryID;
    }

    public void setIndustryID(Integer industryID) {
        this.industryID = industryID;
    }

    public Integer getBusinessSizeID() {
        return businessSizeID;
    }

    public void setBusinessSizeID(Integer businessSizeID) {
        this.businessSizeID = businessSizeID;
    }
}
