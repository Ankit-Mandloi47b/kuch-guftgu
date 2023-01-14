import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class Server {
    public static final int port=5080;
    public static Vector<clientHandler>clientsList=new Vector<>();
    public static int Clientcnt=0;
    public static void main(String []args) throws IOException{

        ServerSocket listener=new ServerSocket(port);
           
        while(true){
        System.out.println("waiting for client request");   
        Socket client=listener.accept();
        Clientcnt++;
        System.out.println("request accepted");
        DataInputStream in=new DataInputStream(new BufferedInputStream(client.getInputStream()));
        DataOutputStream out=new DataOutputStream(client.getOutputStream());
        clientHandler thread=new clientHandler(client,"client" + Clientcnt,in, out);    
        clientsList.add(thread);
        thread.start();
        }
        

    }
}
