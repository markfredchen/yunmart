package com.handchina.yunmart.core.domain.common;

/**
 * Created by markfredchen on 9/22/15.
 */
public class DomainStatistic {

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

    public void updateAverageRating(double newRating) {
        this.averageRating =  (this.numberOfComments * this.averageRating + newRating) / (this.numberOfComments + 1);
    }
}
