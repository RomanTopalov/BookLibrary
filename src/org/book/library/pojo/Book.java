package org.book.library.pojo;

public class Book {
	private String author;
	private String name;
	private String bookId;

	public Book(String author, String name, String bookId) {
		this.author = author;
		this.name = name;
		this.bookId = bookId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

}
