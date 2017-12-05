import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Server2 implements Hello {

    private DbConnection dbc = new DbConnection();
    private Connection conn = dbc.connectToDb();

    public Server2() {}

    public String sayHello() {
        return "Hello, world!";
    }

    private boolean verifyActionPermission(String user, int action) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = null;
        rs = stmt.executeQuery("SELECT Permissions FROM userrolejoin WHERE Username='"+user+"'");
        //System.out.print(rs.getString("Password"));

        if (rs.last()) {
            System.out.print(rs.getString("Permissions"));
            if (rs.getString("Permissions").charAt(action) == '1') {
                rs.close();
                return true;
            }
        }
        rs.close();
        return false;
    }

    public String welcome() throws RemoteException {
        String welcomeMsg = "*********************************************************\n"
                + "** Welcome to this Service!\n"
                + "What would you like to do?"
                + "** 1.Log in\n** 3.Log out\n** 4.Terminate\n"
                + "*********************************************************\n";

        return welcomeMsg;
    }


    @Override
    public boolean newUser(String userName, char[] pw) throws RemoteException, NoSuchAlgorithmException, InvalidKeySpecException{
        System.out.println("REQUEST: Create a user:"+userName);
        //DbConnection db = new DbConnection();

        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //Connection conn = db.connectToMysql();
        String encryptedPw = hashPw(pw);
        return true; //TODO: change back to try/catch

      /* try {
           Statement stmt = conn.createStatement();
           int id = findLastId(conn)+1;
            // Creating statement to insert new user to DB
           String sql = "INSERT INTO userDB VALUES("+ id +","+ userName +"','"+encryptedPw+"','"+sdf.format(dt)+")";
           stmt.executeUpdate(sql);
           conn.close();
           System.out.println("New User created!\n");
           return true;
        } catch (SQLException e) {
           System.out.println("!!SQL exception while creating user "+userName);
       }
          return false; */
    }

    private String hashPw(char[] pw) {
        // Create instance of Argon2 object
     //   Argon2 argon2 = Argon2Factory.create();
     //   String hash =  argon2.hash(2, 65536, 1, pw);
     //   argon2.wipeArray(pw);
     //   return hash;
        return null;
    }

    private int findLastId(Connection conn) throws SQLException {
        int key;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT id FROM userDB ORDER BY ID");
        if (rs.last()) {
            key = rs.getInt("id");
        } else {
            key = 1;
        }
        rs.close();
        return  key;
    }

    @Override
    public boolean login(String u, String pw) throws SQLException, RemoteException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT Password FROM userrolejoin WHERE Username='"+u+"'");
        //System.out.print(rs.getString("Password"));

        if (rs.last()) {
            System.out.print(rs.getString("Password"));
            if(rs.getString("Password").equals(pw)){
                rs.close();
                return true;
            }
        }
        rs.close();
        return false;
    }

    private boolean verifyPwd(char[] pw) {
        // Create instance of Argon2 object
     //   Argon2 argon2 = Argon2Factory.create();
     //   String hash =  argon2.hash(2, 65536, 1, pw);
        if (0==0){
     //   if (argon2.verify(hash, pw)) {
            // Hash matches password
     //       argon2.wipeArray(pw);
            return true;
        } else {
            // Hash doesn't match password
     //       argon2.wipeArray(pw);
            return false;
        }
    }


    @Override
    public boolean validateSession(String userName) throws RemoteException {
        return false;
    }

    @Override
    public boolean logout(String userName) throws RemoteException {
        return false;
    }

    @Override
    public String print(String user, String pw, String filename, String printer) throws RemoteException, SQLException {
        if (login(user, pw) && verifyActionPermission(user, 0)){
            return "print(" + filename + "," + printer + ")";
        }
        return "Printing permission denied";
    }

    @Override
    public String queue() throws RemoteException {
        return "queue()";
    }

    @Override
    public String topQueue(int job) throws RemoteException {
        return "topQueue("+job+")";
    }

    @Override
    public String start() throws RemoteException {
        return "start()";
    }

    @Override
    public String stop() throws RemoteException {
        return "stop()";
    }

    @Override
    public String restart() throws RemoteException {
        return "restart()";
    }

    @Override
    public String status() throws RemoteException {
        return "status()";
    }

    @Override
    public String readConfig(String parameter) throws RemoteException {
        return "readConfig("+parameter+")";
    }

    @Override
    public String setConfig(String parameter, String value) throws RemoteException {
        return "setConfig("+parameter+")";
    }
    public static void main(String args[]) {

        try {
            Server2 obj = new Server2();
            Hello stub = (Hello) UnicastRemoteObject.exportObject(obj, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind("Hello", stub);

            System.err.println("Server 2 ready");
        } catch (Exception e) {
            System.err.println("Server 2 exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
