package org.book.library.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.book.library.pojo.Book;

import com.mysql.jdbc.PreparedStatement;

public class BookDAOImpl implements BookDAO {

	private static BookDAOImpl instance;
	private static Connection connection = null;
	private BookLibraryProperties props;

	@Override
	public void initializeDB() throws SQLException, IOException {
		props = BookLibraryProperties.getInstance();
		createDatabase();
		createTable();
	}

	private void runStatement(String sql) throws SQLException {
		Statement statement = null;
		try {
			statement = getConnection().createStatement();
			statement.execute(sql);
		} finally {
			statement.close();
		}
	}

	private Connection getConnection() throws SQLException {
		if (connection == null) {
			String hostFull = props.getProperty(BookLibraryProperties.HOST_FULL_PATH);
			String login = props.getProperty(BookLibraryProperties.LOGIN);
			String password = props.getProperty(BookLibraryProperties.PASSWORD);
			connection = DriverManager.getConnection(hostFull, login, password);
		}
		return connection;
	}

	private void createDatabase() throws SQLException, IOException {
		Connection dbConnection = null;
		Statement statement = null;
		try {

			String host = props.getProperty(BookLibraryProperties.HOST);
			String login = props.getProperty(BookLibraryProperties.LOGIN);
			String password = props.getProperty(BookLibraryProperties.PASSWORD);
			dbConnection = DriverManager.getConnection(host, login, password);
			statement = dbConnection.createStatement();
			String createDB = props.getProperty(BookLibraryProperties.CREATE_DB_SQL);
			statement.execute(createDB);
			System.out.println(BOOK_DB_MESSAGE);
		} catch (SQLException ex) {
			System.out.println(EXCEPTION_MESSAGE + ex.getMessage());
		} finally {
			try {
				dbConnection.close();
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException ex) {
				System.out.println(EXCEPTION_MESSAGE + ex.getMessage());
			}
		}
	}

	private void createTable() throws SQLException {
		String createTable = props.getProperty(BookLibraryProperties.CREATE_TB_SQL);
		runStatement(createTable);
		System.out.println(TABLE_CREATE_MESSAGE);
	}

	@Override
	public void addBook(Book book) throws SQLException {
		PreparedStatement preparedStatement = null;
		try {
			String addBook = props.getProperty(BookLibraryProperties.ADD_BOOK_SQL);
			preparedStatement = (PreparedStatement) getConnection().prepareStatement(addBook);
			preparedStatement.setString(FIRS_ELEMENT_QUERY, book.getName());
			preparedStatement.setString(SECOND_ELEMENT_QUERY, book.getAuthor());

			preparedStatement.executeUpdate();
			System.out.println(ADD_BOOK_MESSAGE_SUCCESS);
		} finally {
			preparedStatement.close();
		}
	}

	@Override
	public void removeBook(Book book) throws SQLException {
		PreparedStatement preparedStatement = null;
		try {
			String removeBook = props.getProperty(BookLibraryProperties.REMOVE_BOOK_SQL);
			preparedStatement = (PreparedStatement) getConnection().prepareStatement(removeBook);
			preparedStatement.setString(FIRS_ELEMENT_QUERY, book.getName());
			preparedStatement.setString(SECOND_ELEMENT_QUERY, book.getBookId());
			preparedStatement.executeUpdate();
			System.out.println(REMOVE_BOOK_MESSAGE_SUCCESS);
		} finally {
			preparedStatement.close();
		}
	}

	@Override
	public void editBook(Book book, int choice) throws SQLException {
		PreparedStatement preparedStatement = null;

		if (choice == EDIT_BOOK_NAME) {
			try {
				String editBookName = props.getProperty(BookLibraryProperties.EDIT_BOOK_NAME_SQL);
				preparedStatement = (PreparedStatement) getConnection().prepareStatement(editBookName);
				preparedStatement.setString(FIRS_ELEMENT_QUERY, book.getName());
				preparedStatement.setString(SECOND_ELEMENT_QUERY, book.getAuthor());
				preparedStatement.setString(THIRD_ELEMENT_QUERY, book.getBookId());
				preparedStatement.executeUpdate();
				System.out.println(EDIT_BOOK_MESSAGE_SUCCESS);
			} finally {
				preparedStatement.close();
			}
		}
		if (choice == EDIT_BOOK_AUTHOR) {
			try {
				String editBookaUthor = props.getProperty(BookLibraryProperties.RDIT_BOOK_AUTHOR_SQL);
				preparedStatement = (PreparedStatement) getConnection().prepareStatement(editBookaUthor);
				preparedStatement.setString(FIRS_ELEMENT_QUERY, book.getName());
				preparedStatement.setString(SECOND_ELEMENT_QUERY, book.getAuthor());
				preparedStatement.setString(THIRD_ELEMENT_QUERY, book.getBookId());
				preparedStatement.executeUpdate();
				System.out.println(EDIT_BOOK_MESSAGE_SUCCESS);
			} finally {
				preparedStatement.close();
			}
		}
	}

	@Override
	public void showAllBooks() throws SQLException {
		Statement statement = null;
		try {
			String showAllbooks = props.getProperty(BookLibraryProperties.SHOW_ALL_BOOKS_SQL);
			statement = getConnection().createStatement();
			ResultSet result = statement.executeQuery(showAllbooks);

			while (result.next()) {
				Integer id = result.getInt(BOOK_ID_COLUMN_NAME);
				String name = result.getString(BOOK_NAME_COLUMN_NAME);
				String author = result.getString(BOOK_AUTHOR_COLUMN_NAME);
				System.out.println(id + ". " + author + "  " + name);
			}
		} finally {
			statement.close();
		}
	}

	@Override
	public List<Book> getBooksByNameAndColumn(String name, int columnNameId) throws SQLException {
		PreparedStatement preparedStatement = null;

		if (columnNameId == EDIT_BOOK_NAME) {
			try {
				String getBookNameSql = props.getProperty(BookLibraryProperties.GET_BOOK_BY_NAME_SQL);
				preparedStatement = (PreparedStatement) getConnection().prepareStatement(getBookNameSql);
				preparedStatement.setString(FIRS_ELEMENT_QUERY, name);
				ResultSet result = preparedStatement.executeQuery();

				List<Book> list = new ArrayList<>();
				while (result.next()) {
					String bookName = result.getString(BOOK_NAME_COLUMN_NAME);
					String author = result.getString(BOOK_AUTHOR_COLUMN_NAME);
					String bookId = result.getString(BOOK_ID_COLUMN_NAME);
					Book book = new Book(author, bookName, bookId);
					list.add(book);
				}
				return list;

			} finally {
				preparedStatement.close();
			}

		}
		if (columnNameId == EDIT_BOOK_AUTHOR) {
			try {
				String getBookAuthorSql = props.getProperty(BookLibraryProperties.GET_BOOK_BY_AUTHOR_SQL);
				preparedStatement = (PreparedStatement) getConnection().prepareStatement(getBookAuthorSql);
				preparedStatement.setString(FIRS_ELEMENT_QUERY, name);
				ResultSet result = preparedStatement.executeQuery();

				List<Book> list = new ArrayList<>();
				while (result.next()) {
					String bookId = result.getString(BOOK_ID_COLUMN_NAME);
					String bookName = result.getString(BOOK_NAME_COLUMN_NAME);
					String author = result.getString(BOOK_AUTHOR_COLUMN_NAME);
					Book book = new Book(author, bookName, bookId);
					list.add(book);
					return list;
				}
			} finally {
				preparedStatement.close();
			}
		}
		return null;
	}

	public static BookDAOImpl getInstance() {
		if (instance == null) {
			instance = new BookDAOImpl();
		}
		return instance;
	}

	private BookDAOImpl() {
		// Singleton
	}
}