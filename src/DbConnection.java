import java.sql.*;
//import mysql-connector-java-8.0.8-dmr.jar;
//import com.mysql.cj.jdbc.*;


public class DbConnection {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/main";
    String USER = "root";
    String PASS = "";

    public Connection connectToDb(){
        Connection conn = null;
        try {
            // Register JDBC drive
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connection to DB established");
            return conn;

        }
        catch (SQLException e) {
            e.printStackTrace();
            return conn;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return conn;
        }
    }
    public static void main(String[] args){
        DbConnection d = new DbConnection();
        d.connectToDb();
    }
}