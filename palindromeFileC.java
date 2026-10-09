import java.io.*;
import java.net.*;
import java.util.Scanner;

public class palindromeFileC {
    public static void main(String[] args) throws Exception {

        Socket s = new Socket("localhost", 5000);

        DataInputStream dis =
            new DataInputStream(s.getInputStream());
        DataOutputStream dos =
            new DataOutputStream(s.getOutputStream());

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter file name:");
        String fileName = sc.nextLine();

        dos.writeUTF(fileName);

        // Receive file size
        long size = dis.readLong();

        if (size == -1) {
            System.out.println("File not found!");
        } else {
            // Read file contents
            byte[] data = new byte[(int) size];
            dis.readFully(data);

            String str = new String(data).trim();
            String rev = "";

            // Reverse the string
            for (int i = str.length() - 1; i >= 0; i--) {
                rev = rev + str.charAt(i);
            }

            // Check palindrome
            if (str.equalsIgnoreCase(rev)) {
                System.out.println("Palindrome");
            } else {
                System.out.println("Not a palindrome");
            }
        }

        sc.close();
        dis.close();
        dos.close();
        s.close();
    }
}