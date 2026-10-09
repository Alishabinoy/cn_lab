
import java.io.*;
import java.net.*;

public class palindrServer {
    public static void main(String[] args) throws Exception {

        ServerSocket ss = new ServerSocket(5000);
        System.out.println("Server waiting...");

        Socket s = ss.accept();
        System.out.println("Client connected");

        DataInputStream dis =
            new DataInputStream(s.getInputStream());

        DataOutputStream dos =
            new DataOutputStream(s.getOutputStream());

        // RECEIVE FILE SIZE
        long size = dis.readLong();


        byte[] data = new byte[(int) size];
        dis.readFully(data);

        String content = new String(data);
        BufferedReader br = new BufferedReader(
            new StringReader(content)
        );

        String str;

        // CHECK EACH LINE
        while ((str = br.readLine()) != null) {

            str = str.trim();

            if (str.isEmpty())
                continue;

            String rev = "";

            for (int i = str.length() - 1; i >= 0; i--) {
                rev = rev + str.charAt(i);
            }

            if (str.equalsIgnoreCase(rev)) {
                dos.writeUTF(str + " is a palindrome");
            } else {
                dos.writeUTF(str + " is not a palindrome");
            }
        }

        // INDICATE END OF RESULTS
        dos.writeUTF("END");
        dos.flush();

        br.close();
        dis.close();
        dos.close();
        s.close();
        ss.close();
    }
}
