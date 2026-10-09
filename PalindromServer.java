import java.io.*;
import java.net.*;

public class PalindromServer {
    public static void main(String[] args) throws Exception{
        //create server socket
        ServerSocket ss = new ServerSocket(5000);
        System.out.println("Server Started!");
        System.out.println("Waiting for connection....");
        
        //connect to client Socket
        Socket s = ss.accept();

        //Create data input and output streams
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
        DataInputStream dis = new DataInputStream(s.getInputStream());

        //recieve string
        String str = dis.readUTF();
        String rev = "";

        //reverse the string
        for(int i=str.length()-1; i>=0; i--){
            rev = rev + str.charAt(i);
        }

        //check if rev==str
        if(str.equalsIgnoreCase(rev))
            dos.writeUTF("palindrome");
        else
            dos.writeUTF("Not a palindrome");

        dos.close();
        dis.close();
        s.close();
        ss.close();


    }
}
