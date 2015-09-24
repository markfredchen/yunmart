package com.handchina.yunmart.core.persistence.product;

import com.handchina.yunmart.core.domain.product.ProductComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Created by markfredchen on 9/15/15.
 */
public interface ProductCommentRepository extends JpaRepository<ProductComment, Long>{
    Page<ProductComment> findByProductProductOIDOrderByCreatedDateDesc(UUID productOID, Pageable pageable);
}
