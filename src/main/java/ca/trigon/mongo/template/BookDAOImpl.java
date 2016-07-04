package ca.trigon.mongo.template;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.WriteResult;
 
@Repository
public class BookDAOImpl  implements BookDAO {

    @Autowired
    private MongoOperations mongoOps;
    private static final String BOOK_COLLECTION = "Book";

    public BookDAOImpl(MongoOperations mongoOps) {
        this.mongoOps = mongoOps;
    }

    public BookDAOImpl() {

    }

    public void dropCollectionIfExist() {
        
        if (mongoOps.collectionExists(BOOK_COLLECTION)) {
            mongoOps.dropCollection(BOOK_COLLECTION);
            System.out.println("dropped collection");
        }
    }

  
    public void insert(Book p) {
        this.mongoOps.insert(p, BOOK_COLLECTION);
    }

    public void insertAll(Book[] books) {
        mongoOps.insert(Arrays.asList(books), BOOK_COLLECTION);
    }

   
    public Book findByTitle(String title) {
        Query query = new Query(Criteria.where("title").is(title));
        System.out.println("Query=>" + query.toString());
        return this.mongoOps.findOne(query, Book.class, BOOK_COLLECTION);

    }
    
    public List<Book> findByPrice(Double price) {
        Query query = new Query(Criteria.where("price").is(price));
        System.out.println("Query=>" + query.toString());
        return this.mongoOps.find(query, Book.class, BOOK_COLLECTION);

    }

    public List<Book> findByType(String bookType){
    	Query query = new Query(Criteria.where("type").is(bookType));
    	System.out.println("Query=>" + query.toString());
        return this.mongoOps.find(query, Book.class, BOOK_COLLECTION);
    }

    public List<Book> findByListOfPrice(List<Double> prices){
    	Query query = new Query(Criteria.where("price").in(prices));
    	return this.mongoOps.find(query, Book.class, BOOK_COLLECTION);
    }
    
    public List<Book> findBooksGT(Double price){
    	Query query = new Query(Criteria.where("price").gt(price));
    	return this.mongoOps.find(query, Book.class, BOOK_COLLECTION);
    }
    
    public List<Book> findBooksBetweenWithSort(Double priceLower, Double priceUpper){
    	Criteria criteria = new Criteria().where("price").exists(true).andOperator(
    			Criteria.where("price").gte(priceLower),
    			Criteria.where("price").lte(priceUpper));
    	
    	Query query = new Query(criteria).with(new Sort(Sort.Direction.DESC, "price"));
    	
    	System.out.println("findBooksBetween query => " + query.toString());
    	return this.mongoOps.find(query, Book.class, BOOK_COLLECTION);
    }
    
    public List<Book> findByBookEdition(String bookEdition){
    	Query query = new Query(Criteria.where("priceList.bookEdition").is(bookEdition));
    	return this.mongoOps.find(query, Book.class, BOOK_COLLECTION);
    }
    
    
    
 
    public void update(Book p) {
        this.mongoOps.save(p, BOOK_COLLECTION);
    }

    
    public int deleteByTitle(String title) {
        Query query = new Query(Criteria.where("title").is(title));
        WriteResult result = this.mongoOps.remove(query, Book.class, BOOK_COLLECTION);
        return result.getN();
    }
    
    
    public List<Book> findBooksStartsWithT(String startingString){
    	Criteria criteria = new Criteria().where("title").regex("^" + startingString);
    	Query query = new Query(criteria).with(new Sort(Sort.Direction.ASC, "title"));
    	
    	System.out.println("findBooksStartsWithT query => " + query.toString());
    	
    	return this.mongoOps.find(query, Book.class, BOOK_COLLECTION);
    }
    
    public List<Book> findBooksEndsWithe(String endingString){
    	Criteria criteria = new Criteria().where("title").regex(endingString + "$");
    	Query query = new Query(criteria).with(new Sort(Sort.Direction.ASC, "title"));
    	
    	System.out.println("findBooksStartsWithT query => " + query.toString());
    	
    	return this.mongoOps.find(query, Book.class, BOOK_COLLECTION);
    }
 
    
    public List<BookCount> findBookCountOfAuthors(){ 
    	
    	Aggregation agg = newAggregation(
    			match(Criteria.where("author").exists(true)),
    			group("author").count().as("total"),
    			project("total").and("author").previousOperation(),
    			sort(Sort.DEFAULT_DIRECTION.DESC, "total")
    	);
    	
    	AggregationResults<BookCount> groupResult  = mongoOps.aggregate(agg, BOOK_COLLECTION, BookCount.class);
    	
    	List<BookCount> result = groupResult.getMappedResults();
    	
    	return result;
    			
    	
    }
    
    public List<BookCount> findBookCountOfAuthorsByType(String type){ 
    	
		if (type == null)
			type = "";
		
    	Aggregation agg = newAggregation(
    			match(Criteria.where("type").regex("^" + type)),
    			group("type", "author").count().as("total")
//    			project("total").and("author").previousOperation(), 
//    			project("total", "author", "type"),
//    			sort(Sort.DEFAULT_DIRECTION.DESC, "total")
    	);
    	
    	AggregationResults<BookCount> groupResult  = mongoOps.aggregate(agg, BOOK_COLLECTION, BookCount.class);
    	
    	List<BookCount> result = groupResult.getMappedResults();
    	
    	return result;
    			
    	
    }
    
    public List<BookPrices> tryAgg(){
    	Aggregation agg = newAggregation(match(Criteria.where("title").exists(true)),
    		    project("title", "price","author")
    		        .andExpression("price + 1").as("netPricePlus1")
    		        .andExpression("price - 1").as("netPriceMinus1")
    		        .andExpression("price / 2").as("netPriceDiv2")
    		        .andExpression("price * 1.19").as("grossPrice") 
    		        .andExpression("(price * 0.8  + 1.2) * 1.19").as("grossPriceIncludingDiscountAndCharge")
    		        .andExpression("author").as("Author")

    		);
    	AggregationResults<BookPrices> result = mongoOps.aggregate(agg, BOOK_COLLECTION, BookPrices.class);
    	List<BookPrices> resultList = result.getMappedResults();
    	
    	return resultList;
    	
    }
     
    
    
    public List<BookCount> findBookCountOfType(){ 
    	
    	Aggregation agg = newAggregation(
    			match(Criteria.where("type").exists(true)),
    			group("type").count().as("total"),
    			project("total").and("type").previousOperation(),
    			sort(Sort.DEFAULT_DIRECTION.DESC, "total")
    	);
    	
    	AggregationResults<BookCount> groupResult  = mongoOps.aggregate(agg, BOOK_COLLECTION, BookCount.class);
    	
    	List<BookCount> result = groupResult.getMappedResults();
    	
    	return result;
    			
    	
    }


    
    
    
    
    /**********************************************/
    /* create indexes                             */
    /**********************************************/
    
    public void createTitleAuthorIndex(){ 
    	BasicDBObject obj = new BasicDBObject();
    	obj.put("title", 1);
    	obj.put("author", 1);
    	
    	BasicDBObject opt = new BasicDBObject();
    	opt.put("unique", true);
    	opt.put("name","titleAuthorIdx");
    	
    	this.mongoOps.getCollection(BOOK_COLLECTION).createIndex(obj, opt);
    }
    
    public void createPriceIndex(){
    	BasicDBObject obj = new BasicDBObject();
    	obj.put("price", 1); 
    	
    	BasicDBObject opt = new BasicDBObject(); 
    	opt.put("name","priceIdx");
    	
    	this.mongoOps.getCollection(BOOK_COLLECTION).createIndex(obj, opt);
    }
    
    public void createTagsIndex(){
    	BasicDBObject obj = new BasicDBObject();
    	obj.put("tags", 1); 
    	
    	BasicDBObject opt = new BasicDBObject(); 
    	opt.put("name","tagsIdx");
    	
    	this.mongoOps.getCollection(BOOK_COLLECTION).createIndex(obj, opt);
    }
    
    public void createBookEditionIndex(){
    	BasicDBObject obj = new BasicDBObject();
    	obj.put("priceList.bookEdition", 1); 
    	
    	BasicDBObject opt = new BasicDBObject(); 
    	opt.put("name","bookEditionIdx");
    	
    	this.mongoOps.getCollection(BOOK_COLLECTION).createIndex(obj, opt);
    }
 
}

