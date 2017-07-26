package org.book.library.service;

public interface BookService {

	public static final String ADD_BOOK_MESSAGE = "Please enter the title of the book, and this book will be added";
	public static final String ADD_BOOK_ERROR_MESSAGE = "Please, enter not empty name and author of the book";
	public static final String REMOVE_BOOK_MESSAGE = "Please enter the title of the book, and this book will be removed";
	public static final String REMOVE_BOOK_COPY_MESSAGE = "we have a few books with such name please choose one by typing a number of book: ";
	public static final String REMOVE_BOOK_NOT_FOUND_MESSAGE = "We do not have book with this name";
	public static final String EDIT_BOOK_CHANGED_MESSAGE = "Please enter the name of the book and it will be changed";
	public static final String EDIT_AUTHOR_CHANGED_MESSAGE = "Please enter the author of the book and it will be changed";
	public static final String EDIT_BOOK_AUTHOR_MESSAGE = "Please enter the name of the book which will be recorded in the database";
	public static final String EDIT_BOOK_MESSAGE = "Please enter author and he will be changed";
	public static final String EDIT_BOOK_ERROR_MESSAGE = "Sorry, there is no such book title in the database";
	public static final String EDIT_CHOISE_MESSAGE = "Please select an action:\n 1) edit the book by title\n 2) edit the book by author";
	public static final String EDIT_AUTHOR_ERROR_MESSAGE = "Sorry, there is no such book author in the database";
	public static final String ADD_BOOK_AUTHOR_MESSAGE = "Please enter the author and his book will be added";

	public static final String EDIT_ERROR_MESSAGE = "You have entered incorrect data. Try again";
	public static final String LIST_BOOK_MESSAGE = "This is a list of all books: \n";

	public static final int ACTION_BOOK_NAME = 1;
	public static final int ACTION_BOOK_AUTHOR = 2;

	public void addBook() throws Exception;

	public void removeBook() throws Exception;

	public void editBook() throws Exception;

	public void showAllBooks() throws Exception;

	public void init() throws Exception;

}
