package com.mongoJava;

/**
 * Hello world!
 *
 */
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.changestream.FullDocument;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.changestream.ChangeStreamDocument;
import org.bson.Document;

public class App 
{
   
	

	
	public static void main( String[] args )
    {
		Block<ChangeStreamDocument<Document>> printBlock = new Block<ChangeStreamDocument<Document>>() {
		    @Override
		    public void apply(final ChangeStreamDocument<Document> changeStreamDocument) {
		        System.out.println(changeStreamDocument);
		    }
		};
    	
		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017,localhost:27018"));
    	MongoDatabase database = mongoClient.getDatabase("test");
    	MongoCollection<Document> collection = database.getCollection("sessions");
    	//System.out.println("Connected to the collection successfully");
    	
    	collection.watch().forEach(printBlock);
    	
//    	collection.watch(asList(Aggregates.match(Filters.in("operationType", asList("insert", "update", "replace", "delete")))))
  //      .fullDocument(FullDocument.UPDATE_LOOKUP).forEach(printBlock);

    }
}
