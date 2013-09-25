package edu.sjsu.cmpe.library.domain;

public class Review {
	private int id;
	private int rating;
	private String comment;
	private static int reviewSeed = 0;
	
	public Review(){
		id = reviewSeed++;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
