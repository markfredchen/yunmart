package com.handchina.yunmart.web.rest.controller;

import com.handchina.yunmart.core.domain.product.Product;
import com.handchina.yunmart.core.exception.ObjectNotFoundException;
import com.handchina.yunmart.core.persistence.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Created by markfredchen on 9/7/15.
 */
@Controller
@RequestMapping(value = "/api/products")
public class ProductController extends BaseController{

    @Autowired
    private ProductRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Page<Product> getProducts(@RequestParam("page") int page,
                                     @RequestParam("size") int size,
                                     @RequestParam(value = "productCategoryID", required = false) Long productCategoryID,
                                     @RequestParam(value = "orderBy", required = false) String orderBy,
                                     @RequestParam(value = "isDesc", required = false) boolean isDesc) {
        if (productCategoryID == null) {
            if (StringUtils.isEmpty(orderBy)) {
                return repository.findAll(new PageRequest(page, size));
            } else {
                return repository.findAll(new PageRequest(page, size, (isDesc) ? Sort.Direction.DESC : Sort.Direction.ASC, orderBy));
            }
        } else {
            if (StringUtils.isEmpty(orderBy)) {
                return repository.findByCategoryProductCategoryID(productCategoryID, new PageRequest(page, size));
            } else {
                return repository.findByCategoryProductCategoryID(productCategoryID, new PageRequest(page, size, (isDesc) ? Sort.Direction.DESC : Sort.Direction.ASC, orderBy));
            }
        }
    }

    @RequestMapping(value = "/{productOID}", method = RequestMethod.GET)
    @ResponseBody
    public Product getProduct(@PathVariable("productOID") UUID productOID) {
        Product product = repository.findOneByProductOID(productOID);
        if (product != null) {
            return product;
        } else {
            throw new ObjectNotFoundException(Product.class, productOID);
        }
    }

    @RequestMapping(value = "/category/{categoryID}")
    @ResponseBody
    public Page<Product> getProductByCategory(@PathVariable("categoryID") Long categoryID,
                                              @RequestParam("page") int page,
                                              @RequestParam("size") int size) {
        return repository.findByCategoryProductCategoryID(categoryID, new PageRequest(page, size));
    }
}
