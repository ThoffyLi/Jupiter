package db.db.mongodb;

/**
 * @author Thoffy
 * @date 2019/10/14 16:43
 */
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;
import org.bson.Document;
import java.util.Arrays;

public class MongoDBTableCreation {


    // Run as Java application to create MongoDB collections with index.
    public static void main(String[] args) {
        //connect to MongoDB

        MongoClient mongoClient = MongoClients.create(MongoDBUtil.DB_URL);
        MongoDatabase db = mongoClient.getDatabase(MongoDBUtil.DB_NAME);

        //remove old collections.
        db.getCollection("users").drop();
        db.getCollection("items").drop();
        //create new collections, user_id and item_id are unique index
        IndexOptions options = new IndexOptions().unique(true);
        db.getCollection("users").createIndex(new Document("user_id", 1), options);
        db.getCollection("items").createIndex(new Document("item_id", 1), options);
        //insert fake user data and create index.
        db.getCollection("users").insertOne(
                new Document().append("user_id", "1111").append("password",
                        "3229c1097c00d497a0fd282d586be050")
                        .append("first_name", "John").append("last_name",
                        "Smith"));
        mongoClient.close();
        System.out.println("Import is done successfully.");
    }

}



