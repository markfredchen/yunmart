package com.handchina.yunmart.web.rest.resource;

import org.joda.time.DateTime;

import java.util.UUID;

/**
 * Created by markfredchen on 9/23/15.
 */
public class ProductResource extends BaseResource {
    private UUID productOID;
    private String name;
    private String shortDescription;
    private String description;
    private DateTime setPreferredDate;
    private int numberOfViewed;
    private int numberOfComments;
    private int averageRating;

    public UUID getProductOID() {
        return productOID;
    }

    public void setProductOID(UUID productOID) {
        this.productOID = productOID;
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

    public int getNumberOfViewed() {
        return numberOfViewed;
    }

    public void setNumberOfViewed(int numberOfViewed) {
        this.numberOfViewed = numberOfViewed;
    }

    public int getNumberOfComments() {
        return numberOfComments;
    }

    public void setNumberOfComments(int numberOfComments) {
        this.numberOfComments = numberOfComments;
    }

    public int getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(int averageRating) {
        this.averageRating = averageRating;
    }
}
