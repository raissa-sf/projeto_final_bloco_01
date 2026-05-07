package bookstore.util;

import bookstore.controller.BookController;
import bookstore.model.PhysicalBook;
import bookstore.model.Ebook;

public class DatabaseMock {

    public static void fill(BookController controller) {

        controller.register(new PhysicalBook(controller.generateId(), 1, "The Hobbit", "J.R.R. Tolkien", 45.90f, "Hardcover"));
        controller.register(new PhysicalBook(controller.generateId(), 1, "Clean Code", "Robert C. Martin", 95.00f, "Softcover"));
      
        controller.register(new Ebook(controller.generateId(), 2, "Java How to Program", "Deitel", 120.00f, "PDF"));
        controller.register(new Ebook(controller.generateId(), 2, "Spring Boot in Action", "Craig Walls", 89.90f, "EPUB"));
        
        System.out.println("\n" + Colors.TEXT_CYAN_BOLD + ">>> Database Mock: Test data loaded! <<<" + Colors.TEXT_RESET);
    }
}