import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class PrimeNoClient {
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
        System.out.println("Enter number: ");
        int num = sc.nextInt();
        dos.writeInt(num);

        System.out.println(dis.readUTF());
        

        dis.close();
        dos.close();
        s.close();
        sc.close();
    }
}
