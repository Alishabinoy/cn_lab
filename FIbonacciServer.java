import java.io.*;
import java.net.*;

public class FIbonacciServer {
    public static void main(String[] args) throws Exception{
    ServerSocket ss = new ServerSocket(5000);
    System.out.println("Waiting for client...");

    Socket s = ss.accept();
 
    DataInputStream dis =
        new DataInputStream(s.getInputStream());
    DataOutputStream dos =
        new DataOutputStream(s.getOutputStream());

    int num = dis.readInt();
    int a = 0;
    int b = 1;
    int c;
    String result = "";

    for(int i=1; i<num; i++){
        result = result + a + " ";

        c = a+b;
        a = b;
        b = c;
    }

    dos.writeUTF(result);
    dos.writeInt(a);


    dis.close();
    dos.close();
    s.close();
    ss.close();

    }
}
