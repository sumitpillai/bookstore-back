package com.pluralsight.bookstore.rest;

import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import com.pluralsight.bookstore.model.Book;
import com.pluralsight.bookstore.repository.BookRepository;

@RequestScoped
@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookEndPoint {
	
	@Inject
	BookRepository bookRepository;
	

	@POST
	public Response create(final Book book) {
		//TODO: process the given book 
		//here we use Book#getId(), assuming that it provides the identifier to   retrieve the created Book resource. 
		return Response.created(UriBuilder.fromResource(BookEndPoint.class).path(String.valueOf(book.getId())).build())
				.build();
	}

	@GET
	@Path("/{id:\\d+}")
	public Response getBook(@PathParam("id") final Long id) {
		//TODO: retrieve the book 
		Book book = bookRepository.find(id);
		if (book == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(book).build();
	}

	@GET
	public Response getBooks() {
		 
		final List<Book> books = bookRepository.findAll();
		
		if(books.size()==0) 
			return Response.status(Status.NO_CONTENT).build();
		
		
		return Response.ok(books).build();
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteBook(@PathParam("id") final Long id) {
		bookRepository.delete(id);
		return Response.noContent().build();
	}

	@GET
	@Path("/count")
	@Produces(TEXT_PLAIN)
	public Response countBooks() {
		return Response.ok(bookRepository.countAll()).build();
	}

}
