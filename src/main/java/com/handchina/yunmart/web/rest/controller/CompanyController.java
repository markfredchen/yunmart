package com.handchina.yunmart.web.rest.controller;

import com.handchina.yunmart.core.domain.Company;
import com.handchina.yunmart.core.service.AccountService;
import com.handchina.yunmart.web.rest.resource.CompanyResource;
import com.handchina.yunmart.web.rest.resource.adapter.CompanyResourceAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by markfredchen on 9/21/15.
 */
@Controller
public class CompanyController {

    @Autowired
    AccountService accountService;

    @RequestMapping(value = "/api/company/create/{companyName}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<CompanyResource> createCompany(@RequestBody CompanyResource companyResource,
                                                         @PathVariable("companyName") String companyName) {
        CompanyResourceAdapter adapter = new CompanyResourceAdapter();
        Company company = accountService.createCompany(adapter.toDomainObject(companyResource), companyName);
        return ResponseEntity.ok(adapter.toResource(company));
    }
}
