package com.handchina.yunmart.web.rest.controller.product;

import com.handchina.yunmart.core.domain.product.Product;
import com.handchina.yunmart.core.domain.product.ProductComment;
import com.handchina.yunmart.core.persistence.product.ProductCommentRepository;
import com.handchina.yunmart.core.persistence.product.ProductRepository;
import com.handchina.yunmart.core.security.SecurityUtils;
import com.handchina.yunmart.web.rest.resource.ProductCommentResource;
import com.handchina.yunmart.web.rest.resource.adapter.ProductCommentResourceAdapter;
import com.handchina.yunmart.web.util.PaginationUtil;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by markfredchen on 9/15/15.
 */
@Controller
@RequestMapping(value = "/api/product/comments")
public class ProductCommentController {

    @Autowired
    ProductCommentRepository commentRepository;

    @Autowired
    ProductRepository productRepository;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public ResponseEntity<ProductCommentResource> createProductComment(@RequestBody ProductCommentResource commentResource) {
        ProductCommentResourceAdapter adapter = new ProductCommentResourceAdapter();
        ProductComment pc = adapter.toDomainObject(commentResource);
        Product product = productRepository.findOneByProductOID(pc.getProduct().getProductOID());
        pc.setCreatedDate(DateTime.now());
        pc.setProduct(product);
        pc.setCreatedBy(SecurityUtils.getCurrentLogin());
        commentRepository.save(pc);
        return ResponseEntity.ok(adapter.toResource(pc));
    }


    @RequestMapping(value = "/{productOID}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<ProductCommentResource>> findAll(
        @PathVariable(value = "productOID") UUID productOID,
        @RequestParam(value = "page", required = false) Integer offset,
        @RequestParam(value = "per_page", required = false) Integer limit) throws URISyntaxException {

        ProductCommentResourceAdapter adapter = new ProductCommentResourceAdapter();
        Page<ProductComment> page = commentRepository.findByProductProductOIDOrderByCreatedDateDesc(productOID, PaginationUtil.generatePageRequest(offset, limit));
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/product/comments/" + productOID, offset, limit);
        return new ResponseEntity<>(page.getContent().stream()
            .map(comment -> adapter.toResource(comment))
            .collect(Collectors.toCollection(LinkedList::new)), headers, HttpStatus.OK);
    }
}
