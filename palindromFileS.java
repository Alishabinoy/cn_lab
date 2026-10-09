import java.io.*;
import java.net.*;

public class palindromFileS {
    public static void main(String[] args) throws Exception {

        ServerSocket ss = new ServerSocket(5000);
        System.out.println("Server started!");

        Socket s = ss.accept();

        DataInputStream dis =
            new DataInputStream(s.getInputStream());
        DataOutputStream dos =
            new DataOutputStream(s.getOutputStream());

        // Receive file name
        String fileName = dis.readUTF();
        File file = new File(fileName);

        if (!file.exists() || !file.isFile()) {
            dos.writeLong(-1);
        } else {
            FileInputStream fis = new FileInputStream(file);

            // Send file size
            dos.writeLong(file.length());

            // Send file contents
            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = fis.read(buffer)) != -1) {
                dos.write(buffer, 0, bytesRead);
            }

            fis.close();
        }

        dos.close();
        dis.close();
        s.close();
        ss.close();
    }
}