package bookstore.repository;

import bookstore.model.Book;

public interface BookRepository {
    
    public void searchById(int id);
    public void listAll();
    public void register(Book book);
    public void update(Book book);
    public void delete(int id);
    
}