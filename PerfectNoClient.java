import java.io.*;
import java.net.*;
import java.util.Scanner;

public class PerfectNoClient {
    public static void main(String[] args) throws Exception {
        //create client socket
        Socket s = new Socket("localhost",5000);

        //create input and output streams
        DataInputStream dis =
        new DataInputStream(s.getInputStream());
        DataOutputStream dos =
        new DataOutputStream(s.getOutputStream());

        //create scanner obj
        Scanner sc= new Scanner(System.in);

        //take user input
        System.out.println("Enter a number: ");
        int n = sc.nextInt();

        //send to server
        dos.writeInt(n);

        //print output
        System.out.println(dis.readUTF());

        dis.close();
        dos.close();
        s.close();
        sc.close();

    }
}
