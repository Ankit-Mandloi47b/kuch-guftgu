import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class client {
 private static DataInputStream in;
 private static DataOutputStream out;
 private static final String ip="localhost";
 private static final int  port=5080;
 public static void main(String []args)throws IOException{
     
    Socket socket=new Socket(ip,port);
    Scanner sc=new Scanner(System.in);
        System.out.println("Enter the data to be sent in format of message # client's name");
        in=new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        out=new DataOutputStream(socket.getOutputStream());
        Thread readMessage=new Thread(new Runnable(){
          public void run(){
            while(true){
                try {
                    String message=in.readUTF();
                    System.out.println(message);
                
                } catch (Exception e) {
                    
                }
            }
            
            
          }
        });
        Thread sendMessage=new Thread(new Runnable(){
            public void run(){
                while(true){
                    try {
                        String  userdata=sc.nextLine();
                        out.writeUTF(userdata);
                     } catch (Exception e) {
                         
                     }
                }             
              
            }
          });
          readMessage.start();
          sendMessage.start();
 }
 
    

}
