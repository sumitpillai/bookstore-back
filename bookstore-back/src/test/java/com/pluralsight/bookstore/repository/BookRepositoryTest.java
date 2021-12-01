package com.pluralsight.bookstore.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.pluralsight.bookstore.model.Book;
import com.pluralsight.bookstore.model.Language;
import com.pluralsight.bookstore.util.IsbnGenerator;
import com.pluralsight.bookstore.util.NumberGenerator;
import com.pluralsight.bookstore.util.TextUtil;

@RunWith(Arquillian.class)
public class BookRepositoryTest {

	@Inject
	private BookRepository bookRepository;

	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class)
				.addClass(BookRepository.class)
				.addClass(Book.class)
				.addClass(Language.class)
				.addClass(NumberGenerator.class)
				.addClass(TextUtil.class)
				.addClass(IsbnGenerator.class)
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsManifestResource("META-INF/test-persistence.xml", "persistence.xml");
	}

	@Test(expected = Exception.class)
	public void createInvalidBook() {
		Book book = new Book(null, "Part 1", 100.00F, "isbn", new Date(), 500, "http://harrypotter.com",
				Language.ENGLISH);
		bookRepository.create(book);
	}

	@Test
	public void create() {
		// fail("Not yet implemented");

		assertEquals(Long.valueOf(0), bookRepository.countAll());
		assertEquals(0, bookRepository.findAll().size());

		Book book = new Book("Harry    Potter", "Part 1", 100.00F, "isbn", new Date(), 500, "http://harrypotter.com",
				Language.ENGLISH);

		book = bookRepository.create(book);

		Long id = book.getId();

		assertNotNull(id);

		book = bookRepository.find(id);
		System.out.println(book);
		assertEquals("Harry Potter", book.getTitle());

		assertEquals(Long.valueOf(1), bookRepository.countAll());
		assertEquals(1, bookRepository.findAll().size());



	}

}
