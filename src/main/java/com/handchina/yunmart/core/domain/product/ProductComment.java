package com.handchina.yunmart.core.domain.product;

import com.handchina.yunmart.core.domain.common.Comment;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by markfredchen on 9/15/15.
 */
@Entity
@Table(name = "YM_ProductComment")
public class ProductComment extends Comment{
    @NotNull
    @ManyToOne
    @JoinColumn(name = "productID", referencedColumnName = "productID")
    private Product product;

    @NotNull
    @Max(5)
    @Min(0)
    protected int rating;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
