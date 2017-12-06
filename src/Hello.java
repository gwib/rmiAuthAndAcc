import java.rmi.Remote;
import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

public interface Hello extends Remote {

    String sayHello() throws RemoteException;
    //authentication
    String welcome() throws RemoteException;

    boolean newUser(String userName, char[] pw) throws RemoteException, NoSuchAlgorithmException, InvalidKeySpecException;

    boolean login(String userName, String pw) throws RemoteException, SQLException;

    boolean validateSession(String userName) throws RemoteException;

    boolean logout(String userName) throws  RemoteException;

    // functionalities

    String print(String username, String pw, String filename, String printer) throws RemoteException, SQLException;   // prints file filename on the specified printer

    String queue(String user, String pw) throws  RemoteException, SQLException;   // lists the print queue on the user's display in lines of the form <job number>   <file name>

    String topQueue(String user, String pw, int job) throws RemoteException, SQLException;   // moves job to the top of the queue

    String start(String user, String pw) throws RemoteException, SQLException;   // starts the print server

    String stop(String user, String pw) throws RemoteException, SQLException;   // stops the print server

    String restart(String user, String pw) throws RemoteException, SQLException;   // stops the print server, clears the print queue and starts the print server again

    String status(String user, String pw) throws RemoteException, SQLException;  // prints status of printer on the user's display

    String readConfig(String user, String pw, String parameter) throws RemoteException, SQLException;   // prints the value of the parameter on the user's display

    String setConfig(String user, String pw, String parameter, String value) throws RemoteException, SQLException;   // sets the parameter to value

}
