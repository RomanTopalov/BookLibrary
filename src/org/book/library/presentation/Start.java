package org.book.library.presentation;

import org.book.library.service.ConsoleService;
import org.book.library.service.ConsoleServiceImpl;

public class Start {

	public static void main(String[] args) throws Exception {
		ConsoleService console = new ConsoleServiceImpl();
		console.start();
	}

}
