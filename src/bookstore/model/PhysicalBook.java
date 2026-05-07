package bookstore.model;

import bookstore.util.Colors;

public class PhysicalBook extends Book{
	
	private String coverType;
	
	public PhysicalBook(int id, int type, String title, String author, float price, String coverType) {
		super(id, type, title, author, price);
		this.coverType = coverType;
	}

	@Override
	public void view() {
		super.view();
		System.out.printf(Colors.TEXT_WHITE_BOLD + "%-10s:" + Colors.TEXT_YELLOW + " %s\n", "Cover", this.coverType);
		System.out.println( Colors.TEXT_CYAN_BOLD + "***********************************************************\n" + Colors.TEXT_RESET);
	}

	public String getCoverType() {
		return coverType;
	}

	public void setCoverType(String coverType) {
		this.coverType = coverType;
	}
	

}
