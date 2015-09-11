package com.handchina.yunmart.core.domain.product;

import com.handchina.yunmart.core.domain.DomainObject;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Created by markfredchen on 9/8/15.
 */
@Entity
@Table(name = "T_ProductCategory")
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long productCategoryID;

    @NotEmpty
    private String name;

    public Long getProductCategoryID() {
        return productCategoryID;
    }

    public void setProductCategoryID(Long productCategoryID) {
        this.productCategoryID = productCategoryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
