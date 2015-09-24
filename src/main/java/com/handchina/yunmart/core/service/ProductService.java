package com.handchina.yunmart.core.service;

import com.handchina.yunmart.core.domain.product.Product;
import com.handchina.yunmart.core.persistence.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by markfredchen on 9/22/15.
 */
@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Page<Product> findPopularProducts(Integer offset, Integer limit) {
        return productRepository.findAll(new PageRequest(
            offset,
            limit,
            new Sort(
                new Sort.Order(Sort.Direction.DESC, "statistic.averageRating"),
                new Sort.Order(Sort.Direction.DESC, "statistic.numberOfComments"),
                new Sort.Order(Sort.Direction.DESC, "statistic.numberOfViewed")
            )
        ));
    }
}
