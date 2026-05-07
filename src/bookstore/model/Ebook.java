package bookstore.model;

public class Ebook extends Book {

	private String format;

	public Ebook (int id, int type, String title, String author, float price, String format) {
		super(id, type, title, author, price);
		this.format = format;
	}

	@Override
	public void view() {
		super.view();
		System.out.println("Format: " + this.format);
		System.err.println( "***********************************************************\n");
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

}
