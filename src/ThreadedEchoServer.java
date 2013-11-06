
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class ThreadedEchoServer implements Runnable{

	private Socket storedSocket;

	public ThreadedEchoServer(Socket clientSocket){
		System.out.println("got here");
		storedSocket = clientSocket;	
	}
	public static void main(String[] args) throws InterruptedException {
		try {
			ServerSocket sock = new ServerSocket(6013);
			while (true) {
				try {
					Socket client = sock.accept();
					Thread clientThread = new Thread(new ThreadedEchoServer(client));
					clientThread.start();
					//clientThread.join();
					
				} catch(SocketException e) {
					System.err.println(e);
					continue;
				}
			}
		} catch (IOException ioe) {
			System.err.println(ioe);
			ioe.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			processData(storedSocket);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	private void processData(Socket clientSocket) throws IOException{
		InputStream isr = clientSocket.getInputStream();
		OutputStream outt = clientSocket.getOutputStream();
		int input;
		while ((input = isr.read()) != -1) {
			outt.write(input);
			outt.flush();
		}
		//isr.close();
		System.out.println(clientSocket);
		System.out.println("got here too");
		clientSocket.shutdownOutput();
		isr.close();
		clientSocket.close();

	}

}