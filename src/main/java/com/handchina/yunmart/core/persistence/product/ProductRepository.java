package com.handchina.yunmart.core.persistence.product;

import com.handchina.yunmart.core.domain.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.UUID;

/**
 * Created by markfredchen on 9/7/15.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findOneByProductOID(UUID productOID);

    Page<Product> findByCategoryCategoryID(Long categoryId, Pageable pageable);

    Page<Product> findBySetPreferredDateNotNullOrderBySetPreferredDateDesc(Pageable pageable);
}
