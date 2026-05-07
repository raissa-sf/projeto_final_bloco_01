package bookstore;

import java.util.InputMismatchException;
import java.util.Scanner;

import bookstore.controller.BookController;
import bookstore.model.Ebook;
import bookstore.model.PhysicalBook;
import bookstore.util.Colors;
import bookstore.util.DatabaseMock;

public class Menu {

	private static final Scanner scanner = new Scanner(System.in);
	private static final BookController bookController = new BookController();

	public static void main(String[] args) {

		DatabaseMock.fill(bookController);

		int option;

		while (true) {

			System.out.println(Colors.TEXT_CYAN_BOLD + "*****************************************************");
			System.out.println("                                                     ");
			System.out.println("                GALAXY BOOKSTORE                     ");
			System.out.println("                                                     ");
			System.out.println("*****************************************************");
			System.out.println("                                                     ");
			System.out.println("            1 - Add New Book                         ");
			System.out.println("            2 - List All Books                       ");
			System.out.println("            3 - Search Book by ID                    ");
			System.out.println("            4 - Update Book Data                     ");
			System.out.println("            5 - Delete Book                          ");
			System.out.println("            0 - Exit                                 ");
			System.out.println("                                                     ");
			System.out.println("*****************************************************");
			System.out.println("Choose an option:                                    ");
			System.out.println("                                                     " + Colors.TEXT_RESET);

			try {
				option = scanner.nextInt();
				scanner.nextLine();
			} catch (InputMismatchException e) {
				System.err.println("\nError: Please enter an integer number!");
				scanner.nextLine();
				option = -1;
			}

			if (option == 0) {
				System.out.println(Colors.TEXT_CYAN_BOLD + "\nGalaxy Bookstore - Your next adventure starts here!");
				about();
				System.out.println(Colors.TEXT_RESET + "Closing system...");
				scanner.close();
				System.exit(0);
			}

			switch (option) {
			case 1:
				System.out.println(Colors.TEXT_CYAN_BOLD + "Add New Book\n\n");
				addBook();
				keyPress();
				break;
			case 2:
				System.out.println(Colors.TEXT_CYAN_BOLD + "List All Books\n\n");
				listAllBooks();
				keyPress();
				break;
			case 3:
				System.out.println(Colors.TEXT_CYAN_BOLD + "Search Book by ID\n\n");
				searchBook();
				keyPress();
				break;
			case 4:
				System.out.println(Colors.TEXT_CYAN_BOLD + "Update Book Data\n\n");
				updateBook();
				keyPress();
				break;
			case 5:
				System.out.println(Colors.TEXT_CYAN_BOLD + "Delete Book\n\n");
				deleteBook();
				keyPress();
				break;
			default:
				System.out.println(Colors.TEXT_CYAN_BOLD + "\nInvalid Option!\n");
				keyPress();
				break;
			}
		}

	}

	public static void addBook() {
	    System.out.print("Book Title: ");
	    String title = scanner.nextLine();

	    System.out.print("Author: ");
	    String author = scanner.nextLine();

	    float price = 0;
	    while (true) {
	        try {
	            System.out.print("Price: ");
	            price = scanner.nextFloat();
	            if (price >= 0) break;
	            System.out.println(Colors.TEXT_RED + "Price cannot be negative!");
	        } catch (InputMismatchException e) {
	            System.out.println(Colors.TEXT_RED + "Invalid input! Please enter a numeric value for price (e.g., 49,90).");
	            scanner.nextLine();
	        }
	    }

	    int type = 0;
	    while (true) {
	        try {
	            System.out.print("Type (1-Physical | 2-Ebook): ");
	            type = scanner.nextInt();
	            scanner.nextLine(); 
	            if (type == 1 || type == 2) break;
	            System.out.println(Colors.TEXT_RED + "Please, choose 1 or 2.");
	        } catch (InputMismatchException e) {
	            System.out.println(Colors.TEXT_RED + "Invalid input! Enter a number (1 or 2).");
	            scanner.nextLine(); 
	        }
	    }

	    int id = bookController.generateId();

	    switch (type) {
	        case 1 -> {
	            System.out.print("Cover Type (Hardcover/Paperback): ");
	            String cover = scanner.nextLine();
	            bookController.register(new PhysicalBook(id, type, title, author, price, cover));
	        }
	        case 2 -> {
	            System.out.print("Format (PDF/EPUB/MOBI): ");
	            String format = scanner.nextLine();
	            bookController.register(new Ebook(id, type, title, author, price, format));
	        }
	    }

	    System.out.printf(Colors.TEXT_GREEN_BOLD + "\nBook '%s' (ID: %d) was successfully registered!%n" + Colors.TEXT_RESET, title, id);
	}

	public static void listAllBooks() {
		bookController.listAll();
	}

	public static void searchBook() {
		System.out.print("Enter the Book ID: ");
		int id = scanner.nextInt();
		scanner.nextLine();
		bookController.searchById(id);
	}

	public static void updateBook() {
		System.out.print("Enter the Book ID to update: ");
		int id = scanner.nextInt();
		scanner.nextLine();

		bookController.findInCollection(id).ifPresentOrElse(book -> {
			System.out.print("New Title (Current: " + book.getTitle() + "): ");
			String title = scanner.nextLine();
			if (title.isEmpty())
				title = book.getTitle();

			System.out.print("New Author (Current: " + book.getAuthor() + "): ");
			String author = scanner.nextLine();
			if (author.isEmpty())
				author = book.getAuthor();

			System.out.print("New Price (Current: " + book.getPrice() + "): ");
			float price = scanner.nextFloat();
			scanner.nextLine();

			int type = book.getType();

			if (type == 1) {
				System.out.print("New Cover Type: ");
				String cover = scanner.nextLine();
				bookController.update(new PhysicalBook(id, type, title, author, price, cover));
			} else {
				System.out.print("New Format: ");
				String format = scanner.nextLine();
				bookController.update(new Ebook(id, type, title, author, price, format));
			}
		}, () -> {
			System.out.println(Colors.TEXT_RED + "Book not found!");
		});
	}

	public static void deleteBook() {
		System.out.print("Enter the Book ID: ");
		int id = scanner.nextInt();
		scanner.nextLine();

		bookController.findInCollection(id).ifPresentOrElse(book -> {
			System.out.print("Are you sure you want to delete '" + book.getTitle() + "'? (Y/N): ");
			if (scanner.nextLine().equalsIgnoreCase("Y")) {
				bookController.delete(id);
			} else {
				System.out.println(Colors.TEXT_YELLOW + "\nOperation canceled. The book was not deleted.");
			}
		}, () -> System.out.println(Colors.TEXT_RED + "Book not found!"));
	}

	public static void about() {
		System.out.println(Colors.TEXT_CYAN_BOLD + "\n*********************************************************");
		System.out.println(Colors.TEXT_WHITE_BOLD + " Project Developed by: ");
		System.out.println(Colors.TEXT_YELLOW + " Raissa Santos Feitosa");
		System.out.println(Colors.TEXT_WHITE + " raissa.feitosa06@gmail.com");
		System.out.println(Colors.TEXT_WHITE + " github.com/raissa-sf");
		System.out.println(Colors.TEXT_CYAN_BOLD + "*********************************************************");
	}

	public static void keyPress() {
		try {
			System.out.println(Colors.TEXT_RESET + "\n\nPress Enter to Continue...");
			System.in.read();
		} catch (Exception e) {
			System.out.println("Error pressing enter...");
		}
	}
}