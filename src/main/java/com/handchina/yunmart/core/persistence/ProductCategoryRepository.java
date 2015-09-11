package com.handchina.yunmart.core.persistence;

import com.handchina.yunmart.core.domain.product.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by markfredchen on 9/8/15.
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}
