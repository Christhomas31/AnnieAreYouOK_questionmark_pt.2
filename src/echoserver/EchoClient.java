package echoserver;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;


public class EchoClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket((args.length > 0) ? args[0]:"localhost", 6013);
            int input;
            OutputStream output = socket.getOutputStream();
            
            
            while ((input = System.in.read()) != -1) {
                //System.out.println("input: " + input);
                output.write(input);
                output.flush();
                int outputVal = socket.getInputStream().read();
                System.out.write(outputVal);
                System.out.flush();
            }
            output.close();
            socket.close();
        } catch (IOException ioe) {
            System.out.println("We caught an exception");
            System.err.println(ioe);
        }
    }
}