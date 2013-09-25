package edu.sjsu.cmpe.library.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Book {
    private long isbn;
    private String title;
    private String publicationDate;
    private String language;
    private int numPages;
    private String status;
    private List<Author> authors =  new ArrayList<Author>();
    private List<Review> reviews =  new ArrayList<Review>();
    
    @JsonProperty("isbn")
    public long getIsbn() {
	return isbn;
    }
    
    @JsonProperty("isbn")
    public void setIsbn(long isbn) {
	this.isbn = isbn;
    }
    
    @JsonProperty("title")
    public String getTitle() {
	return title;
    }
    
    @JsonProperty("title")
    public void setTitle(String title) {
	this.title = title;
    }
    
    @JsonProperty("publication-date")
    public String getPublicationDate() {
    	return publicationDate;
    }
    
    @JsonProperty("publication-date")
    public void setPublicationDate(String publicationdate) {
    	this.publicationDate = publicationdate;
    }
    
    @JsonProperty("language")
    public String getLanguage() {
    	return language;
    }
    
    @JsonProperty("language")
    public void setLanguage(String language) {
    	this.language = language;
    }
    
    @JsonProperty("num-pages")
    public int getNumPages() {
    	return numPages;
    }
    
    @JsonProperty("num-pages")
    public void setNumPages(int numpages) {
    	this.numPages = numpages;
    }
    
    @JsonProperty("status")
    public String getStatus() {
    	return status;
    }
    
    @JsonProperty("status")
    public void setStatus(String status) {
    	this.status = status;
    }
    
    @JsonProperty("authors")
    public List<Author> getAuthors() {
    	return authors;
    }
    
    @JsonProperty("authors")
    public void setAuthors(List<Author> authors) {
    	this.authors = authors;
    }    
    
    @JsonProperty("author")
    public Author getAuthor(Long id){
    	int i;
    	for(i=0;i<authors.size();i++){
    		if(authors.get(i).getId() == id ){
    			break;
    		}    		
    	}    	
    	return authors.get(i);    	
    }  
    
    public void addReview(Review review){
    	this.reviews.add(review);
    }
    
    @JsonProperty("reviews")
    public List<Review> getReviews(){
	   return this.reviews;
	   
   }
    
    @JsonProperty("review")
    public Review getReview(Long id){
    	int i;
    	for(i=0;i<reviews.size();i++){
    		if(reviews.get(i).getId() == id ){
    			break;
    		}    		
    	}    	
    	return reviews.get(i);    	
    }  
    
    public boolean hasReviews(){
    	if(reviews.isEmpty())
    		return false;
    	else 
    		return true;
    }
   
}
