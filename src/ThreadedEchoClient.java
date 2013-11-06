
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;


public class ThreadedEchoClient{
    public static void main(String[] args) throws InterruptedException {
        try {
            Socket socket = new Socket((args.length > 0) ? args[0]:"localhost", 6013);
            OutputStream output = socket.getOutputStream();
            Thread inputThread = new Thread(new InputThreading(socket.getInputStream()));
            Thread outputThread = new Thread(new OutputThreading(output));
            outputThread.start();
            inputThread.start();
            outputThread.join();
            socket.shutdownOutput();
            //output.flush();
            inputThread.join();
            //System.out.flush();
            socket.shutdownInput();
            System.out.flush();
            socket.close();
        } catch (IOException ioe) {
            System.out.println("We caught an exception");
            System.err.println(ioe);
        }
        
        
    }
    
}