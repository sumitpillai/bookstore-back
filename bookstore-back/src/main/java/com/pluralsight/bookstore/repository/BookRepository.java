package com.pluralsight.bookstore.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import com.pluralsight.bookstore.model.Book;

@Transactional(value = TxType.SUPPORTS)
public class BookRepository {

	@PersistenceContext(unitName = "bookStorePU")
	EntityManager em;

	@Transactional(value = TxType.REQUIRED)
	public Book create(final Book book) {
		em.persist(book);
		return book;
	}

	public Book find(final Long id) {
		return em.find(Book.class, id);
	}

	@Transactional(value = TxType.REQUIRED)
	public void delete(final Long id) {
		em.remove(em.getReference(Book.class, id));
	}

	public List<Book> findAll() {
		return em.createQuery("select b from Book b order by b.title asc", Book.class).getResultList();
	}

	public Long countAll() {
		return em.createQuery("select count(b) from Book b", Long.class).getSingleResult();
	}
}
