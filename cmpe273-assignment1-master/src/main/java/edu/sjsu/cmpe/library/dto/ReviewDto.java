package edu.sjsu.cmpe.library.dto;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import edu.sjsu.cmpe.library.domain.Review;

@JsonPropertyOrder(alphabetic = true)
public class ReviewDto extends LinksDto {
	private List<Review> reviews = new ArrayList<Review>();
	
	public ReviewDto(){}
	
	public ReviewDto(Review review){
		this.reviews.add(review);
	}

    public void addReview(Review review) {
	reviews.add(review);
    }

    /**
     * @return the links
     */
    public List<Review> getReviews() {
	return reviews;
    }

    /**
     * @param links
     *            the links to set
     */
    public void setReviews(List<Review> reviews) {
	this.reviews = reviews;
    }

}