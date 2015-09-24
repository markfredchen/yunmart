package com.handchina.yunmart.core.domain.product;

import com.handchina.yunmart.core.domain.common.DomainStatistic;

import javax.persistence.Embeddable;

/**
 * Created by markfredchen on 9/22/15.
 */
@Embeddable
public class ProductStatistic{
    protected int numberOfViewed;

    protected int numberOfComments;

    protected double averageRating;

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

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    @Override
    public String toString() {
        return "ProductStatistic{" +
            "numberOfViewed=" + numberOfViewed +
            ", numberOfComments=" + numberOfComments +
            ", averageRating=" + averageRating +
            '}';
    }
}
