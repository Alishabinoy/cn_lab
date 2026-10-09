import java.io.*;
import java.net.*;

public class upload_client {

    public static void main(String[] args) throws Exception {

    //create Socket
    Socket s = new Socket("localhost",5000);

    //create data Output stream
    DataOutputStream dos = new DataOutputStream(s.getOutputStream());

    //get file
    File file = new File("upload_test.txt");

    //send file name
    dos.writeUTF(file.getName());

    //create file input stream
    FileInputStream fis = new FileInputStream(file);

    //create bytes array
    byte[] buffer = new byte[4096];

    int bytesRead;

    while((bytesRead = fis.read(buffer))!=-1){
        dos.write(buffer,0,bytesRead);
    }
    
    dos.close();
    fis.close();
    s.close();

    }
}
