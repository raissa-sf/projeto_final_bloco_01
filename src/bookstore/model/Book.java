package bookstore.model;

import bookstore.util.Colors;

public abstract class Book {

    private int id;
    private int type; 
    private String title;
    private String author;
    private float price;

    public Book(int id, int type, String title, String author, float price) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void view() {

        String typeName = (this.type == 1) ? "Physical Book" : "E-book";

        System.err.println("\n***********************************************************");
        System.out.println(Colors.TEXT_BLACK_BOLD + "Book Details:");
        System.err.println("***********************************************************");
        System.out.printf("%-10s: %d\n", "ID", this.id);
        System.out.printf("%-10s: %s\n", "Type", typeName);
        System.out.printf("%-10s: %s\n", "Title", this.title);
        System.out.printf("%-10s: %s\n", "Author", this.author);
        System.out.printf("%-10s: $ %.2f\n", "Price", this.price);
        
    }
}