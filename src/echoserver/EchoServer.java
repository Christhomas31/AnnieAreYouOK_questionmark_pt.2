package echoserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class EchoServer {
    
    public static void main(String[] args) {
        try {
            ServerSocket sock = new ServerSocket(6013);
            
            while (true) {
                try {
                Socket client = sock.accept();
                InputStream isr = client.getInputStream();
                OutputStream out = client.getOutputStream();
                int input;
                while ((input = isr.read()) != -1) {
                    //System.out.println("Server input: " + input);
                    
                    out.write(input);
                    out.flush();
                    //System.out.println("Input written");
                }
                isr.close();
                client.close();
                }catch(SocketException e) {
                    System.err.println(e);
                    continue;
                }
            }
        } catch (IOException ioe) {
            System.err.println(ioe);
            ioe.printStackTrace();
        }
    }
}