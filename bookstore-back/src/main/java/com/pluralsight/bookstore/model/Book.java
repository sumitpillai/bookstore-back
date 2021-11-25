package com.pluralsight.bookstore.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

@Entity
public class Book {

	public Book() {
	}

	public Book(final String title, final String description, final Float unitCost, final String isbn,
			final Date publicationDate, final Integer nbOfPages, final String imageUrl, final Language language) {

		this.title = title;
		this.description = description;
		this.unitCost = unitCost;
		this.isbn = isbn;
		this.publicationDate = publicationDate;
		this.nbOfPages = nbOfPages;
		this.imageUrl = imageUrl;
		this.language = language;
	}

	@Id
	@GeneratedValue
	private Long id;

	@Column(length = 200)
	@NotNull
	@Size(min = 1, max = 200)
	private String title;

	@Column(length = 10000)
	@Size(max = 10000)
	private String description;

	@Column(name = "unit_cost")
	@Min(value = 1)
	private Float unitCost;

	@NotNull
	private String isbn;

	@Column(name = "publication_date")
	@Temporal(TemporalType.DATE)
	@Past
	private Date publicationDate;

	private Integer nbOfPages;
	@Column(name = "image_url")
	private String imageUrl;

	private Language language;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public Float getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(final Float unitCost) {
		this.unitCost = unitCost;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(final String isbn) {
		this.isbn = isbn;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(final Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public Integer getNbOfPages() {
		return nbOfPages;
	}

	public void setNbOfPages(final Integer nbOfPages) {
		this.nbOfPages = nbOfPages;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(final String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(final Language language) {
		this.language = language;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", description=" + description + ", unitCost=" + unitCost
				+ ", isbn=" + isbn + ", publicationDate=" + publicationDate + ", nbOfPages=" + nbOfPages + ", imageUrl="
				+ imageUrl + ", language=" + language + "]";
	}

}
