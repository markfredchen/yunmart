package com.handchina.yunmart.web.rest.controller.product;

import com.handchina.yunmart.core.domain.product.Product;
import com.handchina.yunmart.core.exception.ObjectNotFoundException;
import com.handchina.yunmart.core.persistence.product.ProductRepository;
import com.handchina.yunmart.core.service.ProductService;
import com.handchina.yunmart.web.rest.controller.BaseController;
import com.handchina.yunmart.web.rest.resource.ProductResource;
import com.handchina.yunmart.web.rest.resource.adapter.ProductResourceAdapter;
import com.handchina.yunmart.web.util.PaginationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by markfredchen on 9/7/15.
 */
@Controller
@RequestMapping(value = "")
public class ProductController extends BaseController {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ProductService service;

    @RequestMapping(value= "/api/products", method = RequestMethod.GET)
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
                return repository.findByCategoryCategoryID(productCategoryID, new PageRequest(page, size));
            } else {
                return repository.findByCategoryCategoryID(productCategoryID, new PageRequest(page, size, (isDesc) ? Sort.Direction.DESC : Sort.Direction.ASC, orderBy));
            }
        }
    }

    @RequestMapping(value = "/api/products/{productOID}", method = RequestMethod.GET)
    @ResponseBody
    public Product getProduct(@PathVariable("productOID") UUID productOID) {
        Product product = repository.findOneByProductOID(productOID);
        if (product != null) {
            return product;
        } else {
            throw new ObjectNotFoundException(Product.class, productOID);
        }
    }

    @RequestMapping(value = "/api/products/category/{categoryID}")
    @ResponseBody
    public Page<Product> getProductByCategory(@PathVariable("categoryID") Long categoryID,
                                              @RequestParam("page") int page,
                                              @RequestParam("size") int size) {
        return repository.findByCategoryCategoryID(categoryID, new PageRequest(page, size));
    }

    @RequestMapping(value = "/api/product/preferred", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<ProductResource>> findAllPreferred(
        @RequestParam(value = "page") Integer offset,
        @RequestParam(value = "per_page") Integer limit
    ) throws URISyntaxException {
        ProductResourceAdapter adapter = new ProductResourceAdapter();
        Page<Product> page = repository.findBySetPreferredDateNotNullOrderBySetPreferredDateDesc(PaginationUtil.generatePageRequest(offset, limit));
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/product/preferred", offset, limit);
        return new ResponseEntity<>(page.getContent().stream()
            .map(product -> adapter.toResource(product))
            .collect(Collectors.toCollection(LinkedList::new)),
            headers,
            HttpStatus.OK
        );
    }

    @RequestMapping(value = "/api/product/popular", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<ProductResource>> findAllPopular(
        @RequestParam(value = "page") Integer offset,
        @RequestParam(value = "per_page") Integer limit
    ) throws URISyntaxException {
        ProductResourceAdapter adapter = new ProductResourceAdapter();
        Page<Product> page = service.findPopularProducts(offset - 1, limit);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/product/popular", offset, limit);
        return new ResponseEntity<>(page.getContent().stream()
            .map(product -> adapter.toResource(product))
            .collect(Collectors.toCollection(LinkedList::new)),
            headers,
            HttpStatus.OK
        );
    }
}
