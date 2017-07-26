package org.book.library.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import org.book.library.dao.BookDAO;
import org.book.library.dao.BookDAOImpl;
import org.book.library.pojo.Book;

public class BookServiceImpl implements BookService {

	private Scanner sc = new Scanner(System.in);
	private BookDAO bookDAO = BookDAOImpl.getInstance();

	@Override
	public void init() throws Exception {
		try {
			bookDAO.initializeDB();
		} catch (SQLException | IOException ex) {
			throw new Exception(ex);
		}
	}

	@Override
	public void addBook() throws Exception {
		String bookId = "1";
		System.out.println(ADD_BOOK_MESSAGE);
		String bookName = sc.nextLine();
		System.out.println(ADD_BOOK_AUTHOR_MESSAGE);
		String author = sc.nextLine();

		if (!bookName.isEmpty() && !author.isEmpty()) {
			Book book = new Book(author, bookName, bookId);
			bookDAO.addBook(book);

		} else {
			System.out.println(ADD_BOOK_ERROR_MESSAGE);
			addBook();
		}
	}

	@Override
	public void removeBook() throws Exception {
		String printAuthor = ": Author - ";
		String printBook = " Book - ";
		int moreOneItem = 1;
		Book book = null;
		System.out.println(REMOVE_BOOK_MESSAGE);
		String bookName = sc.nextLine();

		List<Book> list = bookDAO.getBooksByNameAndColumn(bookName, ACTION_BOOK_NAME);
		if (!list.isEmpty()) {

			if (list.size() > moreOneItem) {
				System.out.println(REMOVE_BOOK_COPY_MESSAGE);

				for (int i = 0; i < list.size(); i++) {
					book = list.get(i);
					System.out.println(i + printAuthor + book.getAuthor() + printBook + book.getName());
				}

				int number = sc.nextInt();
				book = list.get(number);
				bookDAO.removeBook(book);

			} else {
				bookDAO.removeBook(list.get(0)); // get first element
			}

		} else {
			System.out.println(REMOVE_BOOK_NOT_FOUND_MESSAGE);
		}
	}

	@Override
	public void editBook() throws Exception {
		System.out.println(EDIT_CHOISE_MESSAGE);
		int choice = sc.nextInt();
		if (choice == ACTION_BOOK_NAME) {
			System.out.println(EDIT_BOOK_CHANGED_MESSAGE);
			String bookName = sc.next();
			System.out.println(EDIT_BOOK_AUTHOR_MESSAGE);
			String bookNameChanget = sc.next();
			System.out.println(EDIT_BOOK_MESSAGE);
			String author = sc.next();
			Book book = new Book(author, bookNameChanget, bookName);

			List<Book> list = bookDAO.getBooksByNameAndColumn(bookName, choice);
			if (!list.isEmpty()) {
				bookDAO.editBook(book, choice);

			} else {
				System.out.println(EDIT_BOOK_ERROR_MESSAGE);
			}
			
		}
		if (choice == ACTION_BOOK_AUTHOR) {
			System.out.println(EDIT_AUTHOR_CHANGED_MESSAGE);
			String bookAuthor = sc.next();
			System.out.println(EDIT_BOOK_AUTHOR_MESSAGE);
			String bookNameChanget = sc.next();
			System.out.println(EDIT_BOOK_MESSAGE);
			String author = sc.next();
			Book book = new Book(author, bookNameChanget, bookAuthor);

			List<Book> list = bookDAO.getBooksByNameAndColumn(bookAuthor, choice);
			if (!list.isEmpty()) {
				bookDAO.editBook(book, choice);

			} else {
				System.out.println(EDIT_AUTHOR_ERROR_MESSAGE);
			}

		}
		if (choice != ACTION_BOOK_NAME && choice != ACTION_BOOK_AUTHOR) {
			System.out.println(EDIT_ERROR_MESSAGE);
			editBook();
		}
	}

	@Override
	public void showAllBooks() throws Exception {
		System.out.println(LIST_BOOK_MESSAGE);
		bookDAO.showAllBooks();
		System.out.print("\n");
	}
}
