import java.io.*;
import java.net.*;

public class PerfectNoServer {
    public static void main(String[] args) throws Exception{
    ServerSocket ss = new ServerSocket(5000);
    System.out.println("Waiting for client...");

    Socket s = ss.accept();
 
    DataInputStream dis =
        new DataInputStream(s.getInputStream());
    DataOutputStream dos =
        new DataOutputStream(s.getOutputStream());

    int num = dis.readInt();
    int sum=0;

    for(int i=1; i<=num/2; i++){
        if (num%i==0)
            sum = sum + i;
    }

    if(sum==num)
        dos.writeUTF("Perfect Number");
    else
        dos.writeUTF("Not a perfect number");

    dis.close();
    dos.close();
    s.close();
    ss.close();


    }
}
