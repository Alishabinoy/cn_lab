import java.io.*;
import java.net.*;
public class dwnld_server {
    public static void main(String[] args) throws Exception{
        ServerSocket ss = new ServerSocket(5000);
        System.out.println("Server Started!");
        System.out.println("Waiting for client...");

        Socket s = ss.accept();
        System.out.println("Connected!");

        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());

        String name = dis.readUTF();
        File file = new File(name);

        if(!file.exists()||!file.isFile()){
            dos.writeLong(-1);
        }
        else{
           FileInputStream fis = new FileInputStream(file);
           byte[] buffer = new byte[4096];
           int bytesRead;

           while((bytesRead = fis.read(buffer))!=-1){
            dos.write(buffer,0,bytesRead);
           }

           fis.close();
           
        }

        dis.close();
        dos.close();
        ss.close();
        s.close();
    }
    
}
