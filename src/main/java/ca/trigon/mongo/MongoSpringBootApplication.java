package ca.trigon.mongo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ca.trigon.mongo.template.Book;
import ca.trigon.mongo.template.BookCount;
import ca.trigon.mongo.template.BookDAO;
import ca.trigon.mongo.template.BookPrices;
import ca.trigon.mongo.template.BookRunner;
import ca.trigon.mongo.template.Price;

@SpringBootApplication
public class MongoSpringBootApplication implements CommandLineRunner {
        // gary test
	@Autowired
	BookDAO bookDAO;
	
	public static void main(String[] args) {
		SpringApplication.run(MongoSpringBootApplication.class, args);
	}
	
	@Autowired
	BookRunner bookRunner;
	
	@Override
	public void run(String... args) throws Exception {
		 
		
//		this.setup();
		
//		bookRunner.testTrans();
		
		bookRunner.findBookCountOfAuthors();
		bookRunner.findBookCountOfAuthorsByType("Novel");
		bookRunner.findBookCountOfType();
		bookRunner.tryAgg();
		
//		bookRunner.findNovels();
//		bookRunner.findByType("Sci-Fi");
//		bookRunner.findByBookEdition("Paperback");
//		bookRunner.findByPriceRange(19d, 21d);
//		bookRunner.findPriceGreaterThan(21d);
//		
//		List<Double> prices = Arrays.asList(18d, 8d); 
//		bookRunner.findByListOfPrice(prices);
//
//		try{
//			Book duplicatePK = new Book("10001", "Angels & Demons", "Dan Brown", "Thriller", 0);   
//		    bookDAO.insert(duplicatePK); 
//		    System.out.println("I should never got here");
//		} catch (Exception e){
//			System.out.println("*******************************  duplicate unique key  *****************************************");
//			System.out.println("this is okay, exception => " + e.getMessage());
//		}
	}

	private void printBooks(List<Book> books) {
		for (Book book : books){
			System.out.println(book);
		}
	}
	
	private void setup(){
			
		
		bookDAO.dropCollectionIfExist();
		List<String> tags = Arrays.asList("tag1", "tag2");
		Price price_1 = new Price("hardCover", 25d);
		Price price_2 = new Price("ebook", 17d);
		Price price_3 = new Price("Paperback", 12d);
		Price price_4 = new Price("audio", 19d);
		
		List<Price> priceList1 = Arrays.asList(price_1, price_2);
		List<Price> priceList2 = Arrays.asList(price_1, price_2, price_3);
		
		Book[] books = new Book[]{
			  new Book("00001", "The Da Vinci Code", "Dan Brown", "thriller", 12),
			  new Book("00002", "Think and Grow Rich", "Napoleon Hill", "Motivational", 10),
			  new Book("00003", "The Hobbit", "J.R.R. Tolkien", "Fantasy", 8),
			  new Book("00004", "Le Petit Prince", "Antoine de Saint-Exupery","Novel", 8),
			  new Book("00005", "A Tale Of Two Cities", "Charles Dickens", "Novel", 10),
			  new Book("00006", "Joy Luck Club", "Amy Tan","Novel", 18),
			  new Book("00007", "Hamilton: The Revolution", "Lin-Manuel Miranda","Novel", 18),
			  new Book("00008", "The Kitchen God's wife", "Amy Tan","Novel", 18),
			  new Book("00009", "Saving Fish from Drowning", "Amy Tan","Novel", 28),
			  new Book("00010", "The Hundred Secret Senses", "Amy Tan","Novel", 18),
			  
			  new Book("00011", "The Crossing", "Michael Connelly","Suspense", 18),
			  new Book("00012", "Mariachi Plaza", "Michael Connelly","Novel", 18),
			  new Book("00013", "The Burning Room ", "Michael Connelly","Suspense", 18),
			  new Book("00014", "Nine Dragons", "Michael Connelly","Novel", 18),
			  new Book("00015", "The Hundred Secret Senses", "Michael Connelly","Suspense", 18), 
			  
			  
			  new Book("00016", "Call For the Dead", "John le Carré ","Thriller", 11),  
			  new Book("00017", "The Night Manger", "John le Carré ","Thriller", 12),  
			  new Book("00018", "A Delicate Truth", "John le Carré ","Thriller", 13),  
			  new Book("00019", "The spy who came in from the cold", "John le Carré ","Thriller", 14),  
			  new Book("00020", "Single And Single", "John le Carré ","Thriller", 15),  
			  
			  
			  
			  new Book("00021", "Foundation", "Isaac Asimov","Sci-Fi", 21),  
			  new Book("00022", "Complete Robot", "Isaac Asimov","Sci-Fi", 15),  
			  new Book("00023", "I, Robot", "Isaac Asimov","Sci-Fi", 19),  
			  new Book("00024", "The Foundation Trilogy", "Isaac Asimov","Sci-Fi", 13),  
			  new Book("00025", "Forward the Foundation", "Isaac Asimov","Sci-Fi", 28, tags),  
			  new Book("00026", "Nightfall", "Isaac Asimov","Sci-Fi", 18, tags),  
		  
	  };

      bookDAO.insertAll(books);
		
     
      Book specialPriceBook = new Book("10000", "Inferno", "Dan Brown", "Thriller", 0);
      specialPriceBook.setPriceList(priceList1);
      
      bookDAO.insert(specialPriceBook);
      
      
      Book specialPriceBook2 = new Book("10001", "Angels & Demons", "Dan Brown", "Thriller", 0);
      specialPriceBook2.setPriceList(priceList2);
      
      bookDAO.insert(specialPriceBook2);     
      
      bookDAO.createTitleAuthorIndex();
      
      bookDAO.createPriceIndex();
      
      bookDAO.createBookEditionIndex();
      
      bookDAO.createTagsIndex();
      
      
	}
	
//	@Override
//    public void run(String... args) throws Exception {
//
//        bookDAO.dropCollectionIfExist();
//        Book b = new Book("A Tale Of Two Cities", "Charles Dickens", "Novel", 10);
//
//        bookDAO.insert(b);
//
//        Book[] books = new Book[]{
//            new Book("The Da Vinci Code", "Dan Brown", "thriller", 12),
//            new Book("Think and Grow Rich", "Napoleon Hill", "Motivational", 10),
//            new Book("The Hobbit", "J.R.R. Tolkien", "Fantasy", 8),
//            new Book("Le Petit Prince", "Antoine de Saint-Exupery","Novel", 8),
//            new Book("Joy Luck Club", "Amy Tan","Novel", 18)
//        };
//
//        bookDAO.insertAll(books);
//
//        Book b1 = bookDAO.findByTitle("The Hobbit");
//        System.out.println("Retrieved Book:" + b1);
//        b1.setPrice(6);
//
//        bookDAO.update(b1);
//        Book b2 = bookDAO.findByTitle("The Hobbit");
//        System.out.println("Retrieved Book after update:" + b2);
//
//        int count = bookDAO.deleteByTitle("Think and Grow Rich");
//        System.out.println("Number of records deleted:" + count);
//    
//    }
}
