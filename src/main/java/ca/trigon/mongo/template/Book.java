package ca.trigon.mongo.template;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

public class Book {
 
 
    @Id
    private String id;
    
    
    

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

 

     



	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author
				+ ", price=" + price + ", tags=" + tags + ", priceList="
				+ priceList + ", type=" + type + "]";
	}

	public Book(String id, String title, String author, String type, int price, List<String> tags) {
        this.id = id;
    	this.title = title;
        this.author = author;
        this.price = price;
        this.type = type;
        this.tags = tags;

    }
    
    public Book(String id, String title, String author, String type, int price) {
        this.id = id;
    	this.title = title;
        this.author = author;
        this.price = price;
        this.type = type;
        this.tags = new ArrayList<String>();

    }
    
    public Book() {}
     
    private String title;
    private String author;
    private int price;
    private List<String> tags;
    private List<Price> priceList;
    
    
    
 
    public List<Price> getPriceList() {
		return priceList;
	}

	public void setPriceList(List<Price> priceList) {
		this.priceList = priceList;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	private String type;

     
     
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }   
}
