package ca.trigon.mongo.template;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

@Configuration
@EnableMongoRepositories
public class MongoConfiguration extends AbstractMongoConfiguration {


    @Value("${db.name}")
    private String dbName;
    
    @Value("${db.admin}")
    private String adminDatabaseName;
    
    @Value("${db.user}")
    private String dbUser;
    
    @Value("${db.password}")
    private String dbPassowrd;
    
    @Value("${db.host}")
    private String dbHost;
    
    @Value("${db.port}")
    private String dbPort;
    
    @Override
    protected String getDatabaseName() {
        return dbName;
    }

    @Override
    public Mongo mongo() throws Exception { 
        
      MongoCredential credential = MongoCredential.createCredential(adminDatabaseName, dbUser, dbPassowrd.toCharArray());
	  MongoClient mongoClient = new MongoClient(new ServerAddress(dbHost + ":" + dbPort), Arrays.asList(credential)); 
//    	MongoClient mongoClient = new MongoClient(new ServerAddress(dbHost + ":" + dbPort)); 
		return mongoClient;
    }
 
}
