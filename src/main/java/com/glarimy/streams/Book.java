package com.glarimy.streams;

public class Book {
	int isbn;
	double price;
	String title;
	String author;

	public Book(int isbn, double price, String title, String author) {
		super();
		this.isbn = isbn;
		this.price = price;
		this.title = title;
		this.author = author;
	}

	public Book() {
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

	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", price=" + price + ", title=" + title + ", author=" + author + "]";
	}

}
