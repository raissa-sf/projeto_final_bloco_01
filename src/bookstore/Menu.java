package bookstore;

import java.util.Scanner;

import bookstore.controller.BookController;
import bookstore.model.Ebook;
import bookstore.model.PhysicalBook;
import bookstore.util.Colors;

public class Menu {

	private static final Scanner scanner = new Scanner(System.in);
    private static final BookController bookController = new BookController();
    
	public static void main(String[] args) {

		int option;

		while (true) {

			System.out.println(Colors.TEXT_YELLOW + Colors.ANSI_BLACK_BACKGROUND
					+ "*****************************************************");
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

			option = scanner.nextInt();

			if (option == 0) {
				System.out.println(Colors.TEXT_WHITE_BOLD + "\nGalaxy Bookstore - Your next adventure starts here!");
				about();
				scanner.close();
				System.exit(0);
			}

			switch (option) {
			case 1:
				System.out.println(Colors.TEXT_WHITE + "Add New Book\n\n");
				addBook();
				keyPress();
				break;
			case 2:
				System.out.println(Colors.TEXT_WHITE + "List All Books\n\n");
				keyPress();
				break;
			case 3:
				System.out.println(Colors.TEXT_WHITE + "Search Book by ID\n\n");
				keyPress();
				break;
			case 4:
				System.out.println(Colors.TEXT_WHITE + "Update Book Data\n\n");
				keyPress();
				break;
			case 5:
				System.out.println(Colors.TEXT_WHITE + "Delete Book\n\n");
				keyPress();
				break;
			default:
				System.out.println(Colors.TEXT_RED_BOLD + "\nInvalid Option!\n" + Colors.TEXT_RESET);
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

		System.out.print("Price: ");
		float price = scanner.nextFloat();

		System.out.print("Type (1-Physical | 2-Ebook): ");
		int type = scanner.nextInt();
		scanner.nextLine();

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
		default -> System.out.println(Colors.TEXT_RED + "Invalid book type!");
		}
	}

	public static void about() {
		System.out.println("\n*********************************************************");
		System.out.println("Project Developed by: ");
		System.out.println("Raissa Santos Feitosa");
		System.out.println("raissa.feitosa06@gmail.com");
		System.out.println("github.com/raissa-sf");
		System.out.println("*********************************************************");
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