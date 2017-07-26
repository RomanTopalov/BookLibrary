package org.book.library.service;

import java.io.IOException;
import java.util.Scanner;

public class ConsoleServiceImpl implements ConsoleService {

	private Scanner sc = new Scanner(System.in);
	private BookService bookService = new BookServiceImpl();

	public void start() throws Exception, IOException {
		bookService.init();
		displayChoice();
	}

	public void displayChoice() throws Exception {
		int actionWithBooks;
		System.out.println(SELECT_ACTION_MESSAGE);
		actionWithBooks = sc.nextInt();
		sc.nextLine();
		if (actionWithBooks == ACTION_ADD_BOOK) {
			bookService.addBook();
		} else if (actionWithBooks == ACTION_REMOVE_BOOK) {
			bookService.removeBook();
		} else if (actionWithBooks == ACTION_EDIT_BOOK) {
			bookService.editBook();
		} else if (actionWithBooks == ACTION_SHOW_BOOK) {
			bookService.showAllBooks();
		} else if (actionWithBooks == ACTION_EXIT_BOOK) {
			System.out.println(EXIT_MESSAGE);
		} else {
			System.out.println(ERROR_MESSAGE);
			displayChoice();
		}
		displayChoice();
	}

}
