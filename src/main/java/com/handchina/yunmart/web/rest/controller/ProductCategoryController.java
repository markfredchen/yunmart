package com.handchina.yunmart.web.rest.controller;

import com.handchina.yunmart.core.domain.product.ProductCategory;
import com.handchina.yunmart.core.persistence.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by markfredchen on 9/11/15.
 */
@Controller
public class ProductCategoryController {
    @Autowired
    private ProductCategoryRepository repository;

    @RequestMapping(value = "/api/product/categories", method = RequestMethod.GET)
    @ResponseBody
    public List<ProductCategory> findAll(){
        return repository.findAll();
    }
}
