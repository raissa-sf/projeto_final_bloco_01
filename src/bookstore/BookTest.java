package bookstore;

import bookstore.model.PhysicalBook;
import bookstore.model.Ebook;

public class BookTest {
    public static void main(String[] args) {
        
        PhysicalBook pb = new PhysicalBook(1, 1, "The Hobbit", "J.R.R. Tolkien", 45.0f, "Hardcover");
        Ebook eb = new Ebook(2, 2, "Clean Code", "Robert C. Martin", 30.0f, "PDF");

        System.out.println("--- TESTING MODELS AND INHERITANCE ---");
        
        pb.view();
        eb.view();
        
        System.out.println("\n--- TEST COMPLETED ---");
    }
}