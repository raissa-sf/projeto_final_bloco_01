package bookstore.controller;

import java.util.ArrayList;
import bookstore.model.Book;
import bookstore.repository.BookRepository;
import bookstore.util.Colors;

public class BookController implements BookRepository {

    private ArrayList<Book> bookList = new ArrayList<Book>();

    @Override
    public void searchById(int id) {
        var book = findInCollection(id);

        if (book != null) {
            book.view();
        } else {
            System.out.printf(Colors.TEXT_RED_BOLD + "\nBook ID: %d was not found!%n" + Colors.TEXT_RESET, id);
        }
    }

    @Override
    public void listAll() {
        if (bookList.isEmpty()) {
            System.out.println(Colors.TEXT_RED_BOLD + "\nThe bookstore is empty!" + Colors.TEXT_RESET);
        } else {
            for (var book : bookList) {
                book.view();
            }
        }
    }

    @Override
    public void register(Book book) {
        bookList.add(book);
        System.out.printf(Colors.TEXT_GREEN_BOLD + "\nBook ID %d was successfully registered!%n" + Colors.TEXT_RESET, book.getId());
    }

    @Override
    public void update(Book book) {
        var searchBook = findInCollection(book.getId());

        if (searchBook != null) {
            bookList.set(bookList.indexOf(searchBook), book);
            System.out.printf(Colors.TEXT_GREEN_BOLD + "\nBook ID: %d was successfully updated!%n" + Colors.TEXT_RESET, book.getId());
        } else {
            System.out.printf(Colors.TEXT_RED_BOLD + "\nBook ID: %d was not found for update!%n" + Colors.TEXT_RESET, book.getId());
        }
    }

    @Override
    public void delete(int id) {
        var book = findInCollection(id);

        if (book != null) {
            if (bookList.remove(book)) {
                System.out.printf(Colors.TEXT_GREEN_BOLD + "\nBook ID: %d was successfully deleted!%n" + Colors.TEXT_RESET, id);
            }
        } else {
            System.out.printf(Colors.TEXT_RED_BOLD + "\nBook ID: %d was not found for deletion!%n" + Colors.TEXT_RESET, id);
        }
    }

    public Book findInCollection(int id) {
        for (var book : bookList) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }
}