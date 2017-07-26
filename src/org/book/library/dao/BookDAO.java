package org.book.library.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.book.library.pojo.Book;

public interface BookDAO {

	public final static String BOOK_DB_MESSAGE = "Book_db has successfully been created";
	public final static String TABLE_CREATE_MESSAGE = "Book table has successfully been created";
	public final static String EXCEPTION_MESSAGE = "The following exception has occured: ";

	public static final String ADD_BOOK_MESSAGE_SUCCESS = "Book was added to the database";
	public static final String REMOVE_BOOK_MESSAGE_SUCCESS = "Book was deleted from the database";
	public static final String EDIT_BOOK_MESSAGE_SUCCESS = "Book was edited from the database";

	public static final String BOOK_ID_COLUMN_NAME = "bookId";
	public static final String BOOK_NAME_COLUMN_NAME = "bookName";
	public static final String BOOK_AUTHOR_COLUMN_NAME = "author";

	public static final int FIRS_ELEMENT_QUERY = 1;
	public static final int SECOND_ELEMENT_QUERY = 2;
	public static final int THIRD_ELEMENT_QUERY = 3;
	public static final int EDIT_BOOK_NAME = 1;
	public static final int EDIT_BOOK_AUTHOR = 2;

	public void initializeDB() throws SQLException, IOException;

	public void addBook(Book book) throws SQLException;

	public void removeBook(Book book) throws SQLException;

	public void editBook(Book book, int choice) throws SQLException;

	public void showAllBooks() throws SQLException;

	public List<Book> getBooksByNameAndColumn(String name, int columnNameId) throws SQLException;

}
