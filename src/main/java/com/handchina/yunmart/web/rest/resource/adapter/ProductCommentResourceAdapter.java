package com.handchina.yunmart.web.rest.resource.adapter;

import com.handchina.yunmart.core.domain.User;
import com.handchina.yunmart.core.domain.product.Product;
import com.handchina.yunmart.core.domain.product.ProductComment;
import com.handchina.yunmart.web.rest.resource.ProductCommentResource;

import java.util.UUID;

/**
 * Created by markfredchen on 9/20/15.
 */
public class ProductCommentResourceAdapter implements ResourceAdapter<ProductComment, ProductCommentResource> {
    @Override
    public ProductComment toDomainObject(ProductCommentResource productCommentResource) {
        ProductComment pc = new ProductComment();
        pc.setCommentID(productCommentResource.getCommentID());
        pc.setContent(productCommentResource.getContent());
        pc.setRating(productCommentResource.getRating());
        Product product = new Product();
        product.setProductOID(UUID.fromString(productCommentResource.getProductOID()));
        pc.setProduct(product);
        return pc;
    }

    @Override
    public ProductCommentResource toResource(ProductComment productComment) {
        ProductCommentResource pcr = new ProductCommentResource();
        pcr.setCommentID(productComment.getCommentID());
        pcr.setProductOID(productComment.getProduct().getProductOID().toString());
        pcr.setUsername(productComment.getCreatedBy());
        pcr.setContent(productComment.getContent());
        pcr.setRating(productComment.getRating());
        pcr.setCreatedDate(productComment.getCreatedDate());
        return pcr;
    }
}
