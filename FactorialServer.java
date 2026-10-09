import java.io.*;
import java.net.*;

public class FactorialServer {
    public static void main(String[] args) throws Exception{
    ServerSocket ss = new ServerSocket(5000);
    System.out.println("Waiting for client...");

    Socket s = ss.accept();
 
    DataInputStream dis =
        new DataInputStream(s.getInputStream());
    DataOutputStream dos =
        new DataOutputStream(s.getOutputStream());

    int num = dis.readInt();
    int fact = 1;

    for(int i=1; i<=num; i++)
        fact = fact * i;

    dos.writeUTF("Factorial is: "+fact);

    dis.close();
    dos.close();
    s.close();
    ss.close();

    }
}
