package org.book.library.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BookLibraryProperties {
	public final static String PATH_DATABASE_PROPERTISE = "resources/database.properties";
	public final static String HOST = "db.host";
	public final static String LOGIN = "db.login";
	public final static String PASSWORD = "db.password";
	public final static String HOST_FULL_PATH = "db.hostfullpath";
	public final static String CREATE_DB_SQL = "db.createdb";
	public final static String CREATE_TB_SQL = "db.createtable";
	public final static String MAX_ID_SQL = "db.maxid";
	public final static String ADD_BOOK_SQL = "db.addbook";
	public final static String REMOVE_BOOK_SQL = "db.removebook";
	public final static String EDIT_BOOK_NAME_SQL = "db.editbookname";
	public final static String RDIT_BOOK_AUTHOR_SQL = "db.editbookauthor";
	public final static String SHOW_ALL_BOOKS_SQL = "db.showallbooks";
	public final static String GET_BOOK_BY_NAME_SQL = "db.getbookname";
	public final static String GET_BOOK_BY_AUTHOR_SQL = "db.getbookauthor";

	private Properties javaProps;
	private static BookLibraryProperties instance;

	private BookLibraryProperties() throws IOException {
		javaProps = new Properties();
		FileInputStream fis = new FileInputStream(BookLibraryProperties.PATH_DATABASE_PROPERTISE);
		javaProps.load(fis);
	}

	public static BookLibraryProperties getInstance() throws IOException {
		if (instance == null) {
			instance = new BookLibraryProperties();
		}
		return instance;
	}

	public String getProperty(String propertyKey) {
		return javaProps.getProperty(propertyKey);
	}

}
