package edu.sjsu.cmpe.library.domain;

import java.util.HashMap;

public class Books {
	
	private static final Books INSTANCE = new Books();
	private HashMap<Long,Book> books = new HashMap<Long,Book>();
	long isbn;
	
	//private constructor will prohibit creating instances from outside
	private Books(){
		
	}
 	 
    public void addBook(long isbn, Book book) {
    	books.put(isbn, book);
    }

    public void deleteBook(long isbn) {
    	books.remove(isbn);
    }
    
    public HashMap<Long,Book> getBooks() {
    	return books;
    }

    public void setBooks(HashMap<Long,Book> books) {
    	this.books = books;
    }
    
    public static Books getInstance(){
    	return INSTANCE;
    }
}
