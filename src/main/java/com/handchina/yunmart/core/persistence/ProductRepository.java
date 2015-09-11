package com.handchina.yunmart.core.persistence;

import com.handchina.yunmart.core.domain.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

/**
 * Created by markfredchen on 9/7/15.
 */
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    Product findOneByProductOID(UUID productOID);

    Page<Product> findByCategoryProductCategoryID(Long categoryId, Pageable pageable);
}
