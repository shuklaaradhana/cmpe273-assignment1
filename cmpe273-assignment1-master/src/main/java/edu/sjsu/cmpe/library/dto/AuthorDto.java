package edu.sjsu.cmpe.library.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import edu.sjsu.cmpe.library.domain.Author;

@JsonPropertyOrder(alphabetic = true)
public class AuthorDto extends LinksDto {
	private List<Author> authors = new ArrayList<Author>();
	
	public AuthorDto(){}
	
	public AuthorDto(Author author){
		this.authors.add(author);
	}

    public void addAuthor(Author author) {
    	authors.add(author);
    }

    /**
     * @return the links
     */
    public List<Author> getAuthors() {
	return authors;
    }

    /**
     * @param links
     *            the links to set
     */
    public void setAuthors(List<Author> authors) {
	this.authors = authors;
    }

}