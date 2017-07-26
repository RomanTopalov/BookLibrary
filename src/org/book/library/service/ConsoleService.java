package org.book.library.service;

public interface ConsoleService {

	public static final int ACTION_ADD_BOOK = 1;
	public static final int ACTION_REMOVE_BOOK = 2;
	public static final int ACTION_EDIT_BOOK = 3;
	public static final int ACTION_SHOW_BOOK = 4;
	public static final int ACTION_EXIT_BOOK = 5;

	public static final String SELECT_ACTION_MESSAGE = "\nSelect the action you want to do\n"
														+ "1) add a book\n"
														+ "2) remove a book\n" 
														+ "3) edit a book\n" 
														+ "4) list of all books\n"
														+ "5) exit";
	public static final String EXIT_MESSAGE = "Goodbye";
	public static final String ERROR_MESSAGE = "You have entered incorrect data. Try again";

	public void start() throws Exception;

	public void displayChoice() throws Exception;

}
