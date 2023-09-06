package com.assignment.bookstore.domain;

public class Book {

	private String title;
	private String author;
	private int publicationYear;
	private int isbn;
	private double price;

	public Book() {
	}

	public Book(String title, String author, int publicatioYear, int isbn, double price) {
		this.title = title;
		this.author = author;
		this.publicationYear = publicatioYear;
		this.isbn = isbn;
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}