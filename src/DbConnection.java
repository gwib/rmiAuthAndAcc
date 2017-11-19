import java.sql.*;


public class DbConnection {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.sqlite.JDBC";
    static final String DB_URL = "jdbc:sqlite:rmi.sqlite";

    public Connection connectToDb(){
        Connection conn = null;
        try {
            // Register JDBC drive
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(DB_URL);
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