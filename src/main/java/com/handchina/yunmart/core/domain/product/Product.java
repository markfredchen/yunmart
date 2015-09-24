package com.handchina.yunmart.core.domain.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.handchina.yunmart.core.domain.DomainObject;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * Created by markfredchen on 9/7/15.
 */
@Entity
@Table(name = "YM_Product")
public class Product extends DomainObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    protected Long productID;

    @NotNull
    private UUID productOID;

    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "categoryID", referencedColumnName = "categoryID")
    private ProductCategory category;

    @NotEmpty
    private String name;

    @NotEmpty
    private String shortDescription;

    @NotEmpty
    private String description;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime setPreferredDate;

    @Embedded
    @NotNull
    private ProductStatistic statistic;

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

    public DateTime getSetPreferredDate() {
        return setPreferredDate;
    }

    public void setSetPreferredDate(DateTime setPreferredDate) {
        this.setPreferredDate = setPreferredDate;
    }

    public ProductStatistic getStatistic() {
        return statistic;
    }

    public void setStatistic(ProductStatistic statistic) {
        this.statistic = statistic;
    }

    @Override
    public String toString() {
        return "Product{" +
            "productID=" + productID +
            ", productOID=" + productOID +
            ", category=" + category +
            ", name='" + name + '\'' +
            ", shortDescription='" + shortDescription + '\'' +
            ", description='" + description + '\'' +
            ", setPreferredDate=" + setPreferredDate +
            ", statistic=" + statistic +
            '}';
    }
}
