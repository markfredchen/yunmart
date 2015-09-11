package com.handchina.yunmart.core.domain.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * Created by markfredchen on 9/7/15.
 */
@Entity
@Table(name = "T_Product")
public class Product{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    protected Long productID;

    @NotNull
    private UUID productOID;

    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "categoryID", referencedColumnName = "productCategoryID")
    private ProductCategory category;

    @NotEmpty
    private String name;

    @NotEmpty
    private String shortDescription;

    @NotEmpty
    private String description;

    private String cases;

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public UUID getProductOID() {
        return productOID;
    }

    public void setProductOID(UUID productOID) {
        this.productOID = productOID;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

}
