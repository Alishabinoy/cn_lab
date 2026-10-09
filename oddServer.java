
import java.io.*;
import java.net.*;

public class oddServer {
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

        // RECEIVE FILE CONTENTS
        byte[] data = new byte[(int) size];
        dis.readFully(data);

        String content = new String(data);

        BufferedReader br = new BufferedReader(
            new StringReader(content)
        );

        String line;

        // READ NUMBERS AND CHECK ODD NUMBERS
        while ((line = br.readLine()) != null) {

            line = line.trim();

            if (line.isEmpty())
                continue;

            int n = Integer.parseInt(line);

            if (n % 2 != 0) {
                dos.writeUTF(String.valueOf(n));
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

