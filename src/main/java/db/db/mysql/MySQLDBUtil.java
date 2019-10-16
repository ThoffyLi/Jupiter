package db.db.mysql;

/**
 * @author Thoffy
 * @date 2019/9/29 19:08
 */
public class MySQLDBUtil {


    private static final String HOSTNAME = "localhost";
    private static final String PORT_NUM = "3306";
    public static final String DB_NAME = "jupiter";
    private static final String USERNAME = "root";
    //for PC
    private static final String PASSWORD = "hhxxttxs123";
    //for EC2
    //private static final String PASSWORD = "root";
    public static final String URL = "jdbc:mysql://"
            + HOSTNAME + ":" + PORT_NUM + "/" + DB_NAME
            + "?user=" + USERNAME + "&password=" + PASSWORD
            + "&autoReconnect=true&serverTimezone=UTC";

}
