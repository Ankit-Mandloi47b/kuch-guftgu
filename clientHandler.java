import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;

public class clientHandler  extends Thread
{
    private Socket socket;
    final DataInputStream in;
    final DataOutputStream out;
    boolean islogin;
    private String name;
    public clientHandler(Socket socket,String name ,DataInputStream in,DataOutputStream out){
       this.socket=socket;
       this.in=in;
       this.out=out;
       this.name=name;
       this.islogin=true;
    }
    public void run()
    {
       
        try {
          while(true){
            String recieved=in.readUTF();
            if(recieved.equalsIgnoreCase("logout"))
            {
                islogin=false;
                socket.close();
                in.close();
                out.close();
                break;
            }
            int index=recieved.indexOf("#");
            String recipient= recieved.substring(index+1);
            String MsgToSend=recieved.substring(0,index);
            for(clientHandler ch:Server.clientsList)
            {
                   if(ch.name.equals(recipient) && ch.islogin== true){
                        
                    ch.out.writeUTF(this.name+":"+MsgToSend);  
                    break;
                   }else if(ch.name.equals(recipient) && ch.islogin==false){
                      out.writeUTF(ch.name+" is offline");
                   }
                
            }
            
          }
        } catch (IOException e) {
            System.out.println("this is exception in clientHandler");
        }


    }
    

}