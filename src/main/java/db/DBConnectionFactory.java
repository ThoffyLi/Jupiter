package db;


import db.db.mongodb.MongoDBConnection;
import db.db.mysql.MySQLConnection;
//import db.db.mysql.MySQLConnection;

/**
 * @author Thoffy
 * @date 2019/9/29 18:57
 */
public class DBConnectionFactory {

    private static final String DEFAULT_DB = "mongodb";

    public static DBConnection getConnection(String db) {
        switch (db) {
            case "mysql":
                return new MySQLConnection();
                //return new MySQLConnection();
            case "mongodb":
                return new MongoDBConnection();
           default:
               throw new IllegalArgumentException("Invalid db: " + db);
        }
    }

    public static DBConnection getConnection(){
        return getConnection(DEFAULT_DB);
    }


}
