package ca.trigon.mongo.template;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookRunner {

 
	BookDAO bookDAO;
	List<Book> books = new ArrayList<Book>();
	
	public BookRunner(){}
	
	@Autowired
    public BookRunner(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }
	
	public void findNovels(){
		System.out.println("*******************************  Find by book type Novle ************************************");
		books = bookDAO.findByType("Novel");
		printBooks(books);
	}
	
	public void findByType(String type){
		System.out.println("*******************************  Find by book type *****************************************");
		books = bookDAO.findByType(type);
		printBooks(books);
	}
	
	public void findByBookEdition(String bookEdition){
		System.out.println("*******************************  Find by book edition *****************************************");
		books = bookDAO.findByBookEdition(bookEdition);
		printBooks(books);
	}
	
	public void findByPriceRange(Double staringPrice, Double endingPrice){
		System.out.println("*******************************  Find by book between prices *****************************************");
		books = bookDAO.findBooksBetweenWithSort(staringPrice, endingPrice);
		printBooks(books);
	}
	
	public void findPriceGreaterThan(Double price){
		System.out.println("*******************************  Find by book gt *****************************************");
		books = bookDAO.findBooksGT(price);
		printBooks(books);
	}
	
	
	public void findByListOfPrice(List<Double> prices){
		System.out.println("*******************************  Find by list of price *****************************************");
		
		books = bookDAO.findByListOfPrice(prices);
		printBooks(books);
	}
	
	
	public void findBookCountOfAuthors(){
		System.out.println("*******************************  findBookCountOfAuthors *****************************************");
		List<BookCount> bookCounts = bookDAO.findBookCountOfAuthors();
		for (BookCount bookCount : bookCounts){
			System.out.println(bookCount);
		}
	}
	
	public void findBookCountOfAuthorsByType(String author){
		System.out.println("*******************************  findBookCountOfAuthorsByType : " + author + "*****************************************");
		List<BookCount> bookCounts = bookDAO.findBookCountOfAuthorsByType(author);
		for (BookCount bookCount : bookCounts){
			System.out.println(bookCount);
		}
	}
	
	
	public void findBookCountOfType(){
		System.out.println("*******************************  findBookCountOfType *****************************************");
		List<BookCount> bookCounts = bookDAO.findBookCountOfType();
		for (BookCount bookCount : bookCounts){
			System.out.println(bookCount);
		}
	}
	
	public void tryAgg(){
		System.out.println("*******************************  tryAgg *****************************************");
		List<BookPrices> bookPrices = bookDAO.tryAgg();
		for (BookPrices bookPrice  : bookPrices){
			System.out.println(bookPrice);
		}
	}
	
	@Transactional
	public void testTrans(){
		try{
			Book book1 = new Book("10011", "Angels & Demons", "Dan Trans", "Thriller", 0);   
		    bookDAO.insert(book1); 
		    
		    
			Book dupBook = new Book("10011", "Angels & Demons", "Dan Trans", "Thriller", 0);   
		    bookDAO.insert(dupBook); 
		    
		    System.out.println("I should never got here");
		} catch (Exception e){
			System.out.println("*******************************  Test Trasnactional  *****************************************");
			System.out.println("exception => " + e.getMessage());
		}
	}
	
	
	private void printBooks(List<Book> books) {
		for (Book book : books){
			System.out.println(book);
		}
	}
}
