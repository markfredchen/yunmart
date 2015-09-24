package com.handchina.yunmart.web.rest.resource.adapter;

import com.handchina.yunmart.core.domain.product.Product;
import com.handchina.yunmart.core.domain.product.ProductStatistic;
import com.handchina.yunmart.web.rest.resource.ProductResource;

/**
 * Created by markfredchen on 9/23/15.
 */
public class ProductResourceAdapter implements ResourceAdapter<Product, ProductResource> {
    @Override
    public Product toDomainObject(ProductResource productResource) {
        Product product = new Product();
        if (productResource.getProductOID() != null) {
            product.setProductOID(productResource.getProductOID());
        }
        product.setName(productResource.getName());
        product.setShortDescription(productResource.getShortDescription());
        product.setDescription(productResource.getDescription());
        product.setSetPreferredDate(product.getSetPreferredDate());
        product.setStatistic(new ProductStatistic());
        product.getStatistic().setAverageRating(productResource.getAverageRating());
        product.getStatistic().setNumberOfComments(productResource.getNumberOfComments());
        product.getStatistic().setNumberOfViewed(productResource.getNumberOfViewed());
        return product;
    }

    @Override
    public ProductResource toResource(Product product) {
        ProductResource productResource = new ProductResource();
        productResource.setProductOID(product.getProductOID());
        productResource.setName(product.getName());
        productResource.setShortDescription(product.getShortDescription());
        productResource.setDescription(product.getDescription());
        productResource.setAverageRating(new Double(product.getStatistic().getAverageRating()).intValue());
        productResource.setNumberOfComments(product.getStatistic().getNumberOfComments());
        productResource.setNumberOfViewed(product.getStatistic().getNumberOfViewed());
        return productResource;
    }
}
