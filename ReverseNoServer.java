import java.io.*;
import java.net.*;

public class ReverseNoServer {
    public static void main(String[] args) throws Exception {
        //create server socket
        ServerSocket ss = new ServerSocket(5000);
        System.out.println("Server Started!");
        System.out.println("Waiting for client...");

        //accept client connection
        Socket s = ss.accept();
        System.out.println("Client Connected");

        //create input and output streams
        DataInputStream dis =
        new DataInputStream(s.getInputStream());
        DataOutputStream dos =
        new DataOutputStream(s.getOutputStream());

        //read value from client
        int n = dis.readInt();

        int temp = n;
        int rev=0;

        while(temp>0){
            int rem =  temp%10;
            rev = (rev * 10) + rem;
            temp /= 10;
        }

        dos.writeUTF("The reverse is: "+rev);

        dis.close();
        dos.close();
        s.close();
        ss.close();
    } 
}
