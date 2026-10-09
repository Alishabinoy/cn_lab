
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class oddClient {
    public static void main(String[] args) throws Exception {

        Socket s = new Socket("localhost", 5000);

        DataInputStream dis =
            new DataInputStream(s.getInputStream());

        DataOutputStream dos =
            new DataOutputStream(s.getOutputStream());

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter file name:");
        String fileName = sc.nextLine();

        File file = new File(fileName);

        // SEND FILE SIZE
        dos.writeLong(file.length());

        // SEND FILE CONTENTS
        FileInputStream fis = new FileInputStream(file);

        byte[] data = new byte[(int) file.length()];
        int bytesRead = 0;

        while (bytesRead < data.length) {
            int n = fis.read(
                data, bytesRead, data.length - bytesRead
            );

            if (n == -1)
                throw new EOFException("Unexpected end of file");

            bytesRead += n;
        }

        dos.write(data);
        dos.flush();

        fis.close();

        // RECEIVE ODD NUMBERS
        System.out.println("Odd numbers:");

        String result;

        while (true) {
            result = dis.readUTF();

            if (result.equals("END"))
                break;

            System.out.println(result);
        }

        sc.close();
        dis.close();
        dos.close();
        s.close();
    }
}