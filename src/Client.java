import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Client {

    private Client() {}

    private String userName;
    private String password;

    private void setCredentials(){
        BufferedReader br = null;

        try {

            br = new BufferedReader(new InputStreamReader(System.in));

                System.out.print("Please enter your username : ");
                this.userName = br.readLine();

                System.out.print("Please enter your password!");
                this.password = br.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public static void main(String[] args) {
        Client c = new Client();

        String host = (args.length < 1) ? null : args[0];
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            c.setCredentials();
            Hello stub = (Hello) registry.lookup("Hello");
            System.out.println(stub.sayHello());
            if(stub.login(c.getUserName(), c.getPassword())){
                System.out.println("Login Successful!");
            }
            else {
                System.out.println("Login failed, please try again!");
                return;
            }
            System.out.println(stub.print(c.getUserName(), c.getPassword(), "asdf", "afds"));


        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}