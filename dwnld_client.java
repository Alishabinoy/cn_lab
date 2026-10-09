import java.io.*;
import java.net.*;
import java.util.Scanner;
public class dwnld_client {
    public static void main(String[] args)throws Exception {
        Socket s = new Socket("localhost",5000);
        
        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter file to send: ");
        String fileName = sc.nextLine();

        dos.writeUTF(fileName);

        long fileSize = dis.readLong();

        if(fileSize ==-1)
            System.out.println("File not found");
        else{
            FileOutputStream fos = new FileOutputStream("client_"+fileName);
            byte[] buffer =  new byte[4096];
            int bytesRead;
            while((bytesRead = dis.read(buffer))!=-1){
                fos.write(buffer,0,bytesRead);
            }
            fos.close();

            BufferedReader br = new BufferedReader(new FileReader("client_"+fileName));
            System.out.println("downloaded file");
            
            String line;
            while((line = br.readLine())!=null){
                System.out.println(line);
            }
            br.close();
        }
        dis.close();
        dos.close();
    s.close();
    sc.close();
    }
}
