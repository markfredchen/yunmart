package com.handchina.yunmart.core.domain;

import com.handchina.yunmart.core.enumeration.BusinessSize;
import com.handchina.yunmart.core.enumeration.Industry;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * Created by markfredchen on 9/18/15.
 */
@Entity
@Table(name = "YM_Company")
public class Company extends DomainObject{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long companyID;

    @NotNull
    private UUID companyOID;

    private String overview;

    private Integer industryID;

    private Integer businessSizeID;

    public Long getCompanyID() {
        return companyID;
    }

    public void setCompanyID(Long companyID) {
        this.companyID = companyID;
    }

    public UUID getCompanyOID() {
        return companyOID;
    }

    public void setCompanyOID(UUID companyOID) {
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

    public Industry getIndustry() {
        return Industry.parse(this.industryID);
    }
    public void setIndustry(Industry industry) {
        this.industryID = industry.getID();
    }

    public BusinessSize getBusinessSize() {
        return BusinessSize.parse(this.businessSizeID);
    }

    public void setBusinessSize(BusinessSize businessSize) {
        this.businessSizeID = businessSize.getID();
    }

}
