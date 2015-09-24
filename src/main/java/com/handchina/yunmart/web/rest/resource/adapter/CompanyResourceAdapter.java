package com.handchina.yunmart.web.rest.resource.adapter;

import com.handchina.yunmart.core.domain.Company;
import com.handchina.yunmart.web.rest.resource.CompanyResource;

import java.util.UUID;

/**
 * Created by markfredchen on 9/21/15.
 */
public class CompanyResourceAdapter implements ResourceAdapter<Company, CompanyResource> {
    @Override
    public Company toDomainObject(CompanyResource companyResource) {
        Company company = new Company();
        company.setBusinessSizeID(companyResource.getBusinessSizeID());
        company.setIndustryID(companyResource.getIndustryID());
        company.setOverview(companyResource.getOverview());
        if (companyResource.getCompanyOID() != null) {
            company.setCompanyOID(UUID.fromString(companyResource.getCompanyOID()));
        }
        return company;
    }

    @Override
    public CompanyResource toResource(Company company) {
        CompanyResource cr = new CompanyResource();
        cr.setCompanyOID(company.getCompanyOID().toString());
        cr.setIndustryID(company.getIndustryID());
        cr.setOverview(company.getOverview());
        cr.setBusinessSizeID(company.getBusinessSizeID());
        return cr;
    }
}
