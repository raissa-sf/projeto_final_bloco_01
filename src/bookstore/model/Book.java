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

        String typeName = "";

        switch (this.type) {
            case 1:
                typeName = "Physical Book";
                break;
            case 2:
                typeName = "Ebook";
                break;
        }

        System.out.println("\n\n" + Colors.TEXT_RED + "***********************************************************" + Colors.TEXT_RESET);
        System.out.println("Book Details:");
        System.out.println(Colors.TEXT_RED + "***********************************************************" + Colors.TEXT_RESET);
        System.out.println("ID: " + this.id);
        System.out.println("Type: " + typeName);
        System.out.println("Title: " + this.title);
        System.out.println("Author: " + this.author);
        System.out.printf("Price: $ %.2f%n", this.price);
    }
}