import java.io.*;
import java.net.*;
import java.util.Scanner;

public class PalindromeClient{
    public static void main(String[] args) throws Exception {
        //create server socket
        Socket s = new Socket("localhost",5000);

        //create input and output streams
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
        DataInputStream dis = new DataInputStream(s.getInputStream());

        //create scanner obj
        Scanner sc = new Scanner(System.in);

        //take user input
        System.out.println("Enter a palindrome: ");
        String word = sc.nextLine();

        //send to client
        dos.writeUTF(word);

        //recieve result and print
        System.out.println(dis.readUTF());

        dis.close();
        dos.close();
        s.close();
        sc.close();

    }
}