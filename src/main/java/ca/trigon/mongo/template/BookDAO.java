package ca.trigon.mongo.template;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public interface BookDAO {
 
    public void insert(Book p);
    public void insertAll(Book[] p); 
    public Book findByTitle(String id);
    public List<Book> findByPrice(Double price);
    public List<Book> findByType(String bookType);
    public List<Book> findByListOfPrice(List<Double> prices);
    public List<Book> findBooksGT(Double price);
    public List<Book> findBooksBetweenWithSort(Double priceLower, Double priceUpper);
   
    public List<Book> findByBookEdition(String bookEdition);
    
    
    /**********************************************/
    /* regular expression                         */
    /**********************************************/
    public List<Book> findBooksStartsWithT(String startingString);
    public List<Book> findBooksEndsWithe(String endingString);
      
    
    /**********************************************/
    /* aggregation                                */
    /**********************************************/
    public List<BookCount> findBookCountOfAuthors();
    public List<BookCount> findBookCountOfAuthorsByType(String type);
    public List<BookCount> findBookCountOfType();
    public List<BookPrices> tryAgg();
    
    
    /**********************************************/
    /* misc dml                                   */
    /**********************************************/
    public void update(Book p);
     
    public int deleteByTitle(String id);
    public void dropCollectionIfExist();
    
   
    
    /**********************************************/
    /* create indexes                             */
    /**********************************************/
    public void createTitleAuthorIndex();
    public void createPriceIndex();
    public void createTagsIndex();
    public void createBookEditionIndex();
}
