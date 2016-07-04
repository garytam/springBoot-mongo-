package ca.trigon.mongo.template;

public class Price {

	private String bookEdition;
	private Double price;
	
	public Price() {}
	
	
	public Price(String bookEdition, Double price) {
		super();
		this.bookEdition = bookEdition;
		this.price = price;
	}
	public String getBookEdition() {
		return bookEdition;
	}
	public void setBookEdition(String bookEdition) {
		this.bookEdition = bookEdition;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}


	@Override
	public String toString() {
		return "Price [bookEdition=" + bookEdition + ", price=" + price + "]";
	}
	
	
	
}
