import java.io.*;
import java.net.*;

public class upload_server {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(5000);
        Socket s = ss.accept();

        DataInputStream dis = new DataInputStream(s.getInputStream());
        String fileName = dis.readUTF();

        FileOutputStream fos = new FileOutputStream("server_"+fileName);

        byte[] buffer = new byte[4096];
        int bytesRead;

        while((bytesRead = dis.read(buffer))!=-1){
            fos.write(buffer,0,bytesRead);
        }

        fos.close();
        ss.close();
        dis.close();
        s.close();

    }
}
