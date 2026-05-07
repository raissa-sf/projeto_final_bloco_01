package bookstore.model;

public class PhysicalBook extends Book{
	
	private String coverType;
	
	public PhysicalBook(int id, int type, String title, String author, float price, String coverType) {
		super(id, type, title, author, price);
		this.coverType = coverType;
	}

	@Override
	public void view() {
		super.view();
		System.out.println("Cover Type: " + this.coverType);
	}

	public String getCoverType() {
		return coverType;
	}

	public void setCoverType(String coverType) {
		this.coverType = coverType;
	}
	

}
