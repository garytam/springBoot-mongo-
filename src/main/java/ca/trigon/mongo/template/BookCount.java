package ca.trigon.mongo.template;

public class BookCount {
	
	private String author;
	private long total;
	private String type;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	@Override
	public String toString() {
		return "BookCount [author=" + author + " type= " + type + ", total=" + total +  "]";
	}
	
	

}
