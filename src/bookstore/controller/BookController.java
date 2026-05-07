package bookstore.controller;

import java.util.ArrayList;
import java.util.Optional;

import bookstore.model.Book;
import bookstore.repository.BookRepository;
import bookstore.util.Colors;

public class BookController implements BookRepository {

	private int id = 0;
    private ArrayList<Book> bookList = new ArrayList<Book>();

    @Override
    public void searchById(int id) {
        findInCollection(id).ifPresentOrElse(
            book -> book.view(),
            () -> System.out.printf(Colors.TEXT_RED_BOLD + "\nBook ID: %d was not found!%n" + Colors.TEXT_RESET, id)
        );
    }

    @Override
    public void listAll() {
        if (bookList.isEmpty()) {
            System.out.println(Colors.TEXT_RED_BOLD + "\nThe bookstore is empty!" + Colors.TEXT_RESET);
        } else {
        	bookList.forEach(book -> book.view());
            }
        }
    

    @Override
    public void register(Book book) {
        bookList.add(book);
        System.out.printf(Colors.TEXT_GREEN_BOLD + "\nBook ID %d was successfully registered!%n" + Colors.TEXT_RESET, book.getId());
    }

    @Override
    public void update(Book book) {
        findInCollection(book.getId()).ifPresentOrElse(
            searchBook -> {
                bookList.set(bookList.indexOf(searchBook), book);
                System.out.printf(Colors.TEXT_GREEN_BOLD + "\nBook ID: %d was successfully updated!%n" + Colors.TEXT_RESET, book.getId());
            },
            () -> System.out.printf(Colors.TEXT_RED_BOLD + "\nBook ID: %d was not found for update!%n" + Colors.TEXT_RESET, book.getId())
        );
    }

    @Override
    public void delete(int id) {
        findInCollection(id).ifPresentOrElse(
            book -> {
                bookList.remove(book);
                System.out.printf(Colors.TEXT_GREEN_BOLD + "\nBook ID: %d was successfully deleted!%n" + Colors.TEXT_RESET, id);
            },
            () -> System.out.printf(Colors.TEXT_RED_BOLD + "\nBook ID: %d was not found for deletion!%n" + Colors.TEXT_RESET, id)
        );
    }

    public Optional<Book> findInCollection(int id) {
        return bookList.stream()
                .filter(book -> book.getId() == id)
                .findFirst(); 
    }
    
    public int generateId() {
        return ++id;
    }
}