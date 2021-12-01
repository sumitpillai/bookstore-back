package com.pluralsight.bookstore.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.CREATED;
import static org.junit.Assert.assertEquals;

import java.util.Date;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.extension.rest.client.ArquillianResteasyResource;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.pluralsight.bookstore.model.Book;
import com.pluralsight.bookstore.model.Language;
import com.pluralsight.bookstore.repository.BookRepository;
import com.pluralsight.bookstore.util.IsbnGenerator;
import com.pluralsight.bookstore.util.NumberGenerator;
import com.pluralsight.bookstore.util.TextUtil;

@RunWith(Arquillian.class)
@RunAsClient
public class BookEndPointTest {

	private Response response;
	private static String bookId;

	@Deployment(testable = false)
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class)
				.addClass(BookRepository.class)
				.addClass(Book.class)
				.addClass(Language.class)
				.addClass(NumberGenerator.class)
				.addClass(TextUtil.class)
				.addClass(IsbnGenerator.class)
				.addClass(BookEndPoint.class)
				.addClass(JAXRSConfiguration.class)
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsManifestResource("META-INF/test-persistence.xml", "persistence.xml");
	}

	@Test
	// @InSequence(3)
	public void shouldCreateABook(@ArquillianResteasyResource("api/books") final WebTarget webTarget) {
		// Creates a book

		// response = webTarget.path("count").request().get();
		// assertEquals(Response.Status.NO_CONTENT.getStatusCode(),
		// response.getStatus());

		// Find all
		response = webTarget.request(APPLICATION_JSON).get();
		assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());

		Book book = new Book("Harry Potter New", "Part 1", 100.00F, "isbn", new Date(), 500, "http://harrypotter.com",
				Language.ENGLISH);

		response = webTarget.request(APPLICATION_JSON).post(Entity.entity(book, APPLICATION_JSON));
		assertEquals(CREATED.getStatusCode(), response.getStatus());

	}

}
