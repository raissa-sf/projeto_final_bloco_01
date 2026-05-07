package bookstore;

import java.util.InputMismatchException;
import java.util.Scanner;

import bookstore.controller.BookController;
import bookstore.model.Book;
import bookstore.model.Ebook;
import bookstore.model.PhysicalBook;
import bookstore.util.Colors;

public class Menu {

	private static final Scanner scanner = new Scanner(System.in);
    private static final BookController bookController = new BookController();
    
	public static void main(String[] args) {

		testData();
		
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

			try {
				option = scanner.nextInt();
				scanner.nextLine();
			} catch (InputMismatchException e) {
				System.err.println("\nError: Please enter an integer number!");
				scanner.nextLine(); 
				option = -1;
			}

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
				listAllBooks();
				keyPress();
				break;
			case 3:
				System.out.println(Colors.TEXT_WHITE + "Search Book by ID\n\n");
				searchBook();
				keyPress();
				break;
			case 4:
				System.out.println(Colors.TEXT_WHITE + "Update Book Data\n\n");
				updateBook();
				keyPress();
				break;
			case 5:
				System.out.println(Colors.TEXT_WHITE + "Delete Book\n\n");
				deleteBook();
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
	
	public static void listAllBooks() {
	    bookController.listAll(); 
	}
	
	public static void searchBook() {
        System.out.print("Enter the Book ID: ");
        int id = scanner.nextInt();
        bookController.searchById(id);
    }
	
	public static void updateBook() {
        System.out.print("Enter the Book ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Book book = bookController.findInCollection(id);

        if (book != null) {
            System.out.print("New Title (Current: " + book.getTitle() + "): ");
            String title = scanner.nextLine();
            if(title.isEmpty()) title = book.getTitle();

            System.out.print("New Author (Current: " + book.getAuthor() + "): ");
            String author = scanner.nextLine();
            if(author.isEmpty()) author = book.getAuthor();

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
        } else {
            System.out.println(Colors.TEXT_RED + "Book not found!");
        }
    }
	
	public static void deleteBook() {
        System.out.print("Enter the Book ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Are you sure? (Y/N): ");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("Y")) {
            bookController.delete(id);
        } else {
            System.out.println("Operation canceled.");
        }
    }
	
	public static void testData() {
		bookController.register(new PhysicalBook(bookController.generateId(), 1, "The Hobbit", "J.R.R. Tolkien", 45.0f, "Hardcover"));
        bookController.register(new Ebook(bookController.generateId(), 2, "Clean Code", "Robert Martin", 89.9f, "PDF"));
        bookController.register(new PhysicalBook(bookController.generateId(), 1, "1984", "George Orwell", 35.0f, "Paperback"));
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