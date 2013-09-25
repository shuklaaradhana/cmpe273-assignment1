package edu.sjsu.cmpe.library.api.resources;

import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yammer.dropwizard.jersey.params.LongParam;
import com.yammer.metrics.annotation.Timed;

import edu.sjsu.cmpe.library.domain.Author;
import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.Books;
import edu.sjsu.cmpe.library.domain.Review;
import edu.sjsu.cmpe.library.dto.AuthorDto;
import edu.sjsu.cmpe.library.dto.BookDto;
import edu.sjsu.cmpe.library.dto.LinkDto;
import edu.sjsu.cmpe.library.dto.LinksDto;
import edu.sjsu.cmpe.library.dto.ReviewDto;

@Path("/v1/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {
	static long isbnSeed = getLongRandomSeed();

	public BookResource() {
		// do nothing
	}

	@GET
	@Path("/{isbn}")
	@Timed(name = "view-book")
	public BookDto getBookByIsbn(@PathParam("isbn") LongParam isbn) {
		
		HashMap<Long, Book> books = Books.getInstance().getBooks();

		Book book = new Book();
		book = books.get(isbn.get());

		BookDto bookResponse = new BookDto(book);
		bookResponse.addLink(new LinkDto("view-book", "/books/"
				+ String.valueOf(isbn), "GET"));
		bookResponse.addLink(new LinkDto("update-book", "/books/"
				+ String.valueOf(isbn), "PUT"));
		bookResponse.addLink(new LinkDto("delete-book", "/books/"
				+ String.valueOf(isbn), "DELETE"));
		bookResponse.addLink(new LinkDto("create-review", "/books/"
				+ String.valueOf(isbn) + "/reviews", "POST"));
		if(book.hasReviews()){
    		bookResponse.addLink(new LinkDto("view-all-reviews", "/books/"+ String.valueOf(isbn)+ "/reviews", "GET"));
    	}

		return bookResponse;
	}

	@GET
	@Path("/{isbn}/reviews/{id}")
	@Timed(name = "view-review")
	public ReviewDto getReviewById(@PathParam("isbn") LongParam isbn,
			@PathParam("id") LongParam id) {
		// FIXME - Dummy code
		HashMap<Long, Book> books = Books.getInstance().getBooks();
		Book book = books.get(isbn.get());
		Review review = book.getReview(id.get());

		ReviewDto reviewResponse = new ReviewDto(review);

		reviewResponse.addLink(new LinkDto("view-review", "/books/"
				+ String.valueOf(isbn) + "/reviews/" + String.valueOf(id),
				"GET"));

		return reviewResponse;
	}

	@GET
	@Path("/{isbn}/reviews/")
	@Timed(name = "view-all-reviews")
	public ReviewDto getAllReviews(@PathParam("isbn") LongParam isbn) {
		// FIXME - Dummy code
		HashMap<Long, Book> books = Books.getInstance().getBooks();
		Book book = books.get(isbn.get());

		ReviewDto reviewResponse = new ReviewDto();
		reviewResponse.setReviews(book.getReviews());
		return reviewResponse;
	}

	@GET
	@Path("/{isbn}/authors/{id}")
	@Timed(name = "view-author")
	public AuthorDto getAuthorById(@PathParam("isbn") LongParam isbn,
			@PathParam("id") LongParam id) {

		HashMap<Long, Book> books = Books.getInstance().getBooks();
		Book book = books.get(isbn.get());
		Author author = book.getAuthor(id.get());

		AuthorDto authorResponse = new AuthorDto(author);

		authorResponse.addLink(new LinkDto("view-author", "/books/"
				+ String.valueOf(isbn) + "/authors/" + String.valueOf(id),
				"GET"));

		return authorResponse;
	}

	@GET
	@Path("/{isbn}/authors/")
	@Timed(name = "view-all-authors")
	public AuthorDto getAllAuthors(@PathParam("isbn") LongParam isbn) {

		HashMap<Long, Book> books = Books.getInstance().getBooks();
		Book book = books.get(isbn.get());

		AuthorDto authorResponse = new AuthorDto();
		authorResponse.setAuthors(book.getAuthors());
		return authorResponse;
	}

	@POST
	@Timed(name = "create-book")
	public Response createBook(final String data) {
		Books books = Books.getInstance();

		ObjectMapper mapper = new ObjectMapper();
		Book book = new Book();
		try {
			book = mapper.readValue(data, Book.class);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		long isbn = isbnSeed++;
		book.setIsbn(isbn);

		books.addBook(isbn, book);

		LinksDto links = new LinksDto();
		links.addLink(new LinkDto("view-book",
				"/books/" + String.valueOf(isbn), "GET"));
		links.addLink(new LinkDto("update-book", "/books/"
				+ String.valueOf(isbn), "PUT"));
		links.addLink(new LinkDto("delete-book", "/books/"
				+ String.valueOf(isbn), "DELETE"));
		links.addLink(new LinkDto("create-review", "/books/"
				+ String.valueOf(isbn) + "/reviews", "POST"));

		return Response.ok(links).build();
	}

	@POST
	@Path("/{isbn}/reviews")
	@Timed(name = "create-book-review")
	public Response createReview(@PathParam("isbn") LongParam isbn,
			final String data) {

		Review review = new Review();

		HashMap<Long, Book> books = Books.getInstance().getBooks();
		Book book = books.get(isbn.get());

		ObjectMapper mapper = new ObjectMapper();

		try {
			review = mapper.readValue(data, Review.class);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		book.addReview(review);
		LinksDto links = new LinksDto();
		links.addLink(new LinkDto("view-review", "/books/"
				+ String.valueOf(isbn) + "/reviews/"
				+ String.valueOf(review.getId()), "POST"));

		return Response.ok(links).build();
	}

	public static long getLongRandomSeed() {
		long startLong = 1234567L;
		long endLong = 23456789L;
		Random r = new Random();
		long number = startLong
				+ ((long) (r.nextDouble() * (endLong - startLong)));
		return number;
	}	
	
	
	   	@DELETE
	    @Path("{isbn}")
	    @Timed(name="delete-book")
	    public Response deleteBook(@PathParam("isbn") LongParam isbn)
	    { 
	    		
	    	Books.getInstance().deleteBook(isbn.get());
	    	
	    	LinksDto links = new LinksDto();
	    	links.addLink(new LinkDto("create-book", "/books", "POST"));
	    	return Response.ok(links).build();
	    }
	   
	
	   
	   	@PUT
	    @Path("{isbn}")
	    @Timed(name="update-book")
	    public Response updateBookStatus(@QueryParam("status") String status, @PathParam("isbn") LongParam isbn)
	    {
		   
			
			HashMap<Long, Book> books = Books.getInstance().getBooks();

			Book book = new Book();
			book = books.get(isbn.get());
			book.setStatus(status);

			//BookDto bookResponse = new BookDto(book);
			
			LinksDto links = new LinksDto();
			links.addLink(new LinkDto("view-book", "/books/"+ String.valueOf(isbn), "GET"));
			links.addLink(new LinkDto("update-book", "/books/"+ String.valueOf(isbn), "PUT"));
			links.addLink(new LinkDto("delete-book", "/books/"+ String.valueOf(isbn), "DELETE"));
			links.addLink(new LinkDto("create-review", "/books/"+ String.valueOf(isbn)+"/reviews", "POST"));
	    	if(book.hasReviews()){
	    		links.addLink(new LinkDto("view-all-reviews", "/books/"+ String.valueOf(isbn)+"/reviews", "GET"));
	    	}
	    	return Response.ok(links).build();
	    }
}