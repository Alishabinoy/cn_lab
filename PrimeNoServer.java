import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class PrimeNoServer {
    public static void main(String[] args) throws Exception{
    ServerSocket ss = new ServerSocket(5000);
    System.out.println("Waiting for client...");

    Socket s = ss.accept();
 
    DataInputStream dis =
        new DataInputStream(s.getInputStream());
    DataOutputStream dos =
        new DataOutputStream(s.getOutputStream());

    int num = dis.readInt();

    int flag=0;

    for(int i=2; i<num; i++){
        if(num%i==0){
            flag=1;
            break;
        }
    }

    if(flag==1){
        dos.writeUTF("Not a Prime");
    }
    else
        dos.writeUTF("is a prime");


    dis.close();
    dos.close();
    s.close();
    ss.close();
    }
}
