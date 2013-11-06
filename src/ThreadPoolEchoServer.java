import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolEchoServer implements Runnable{

	private Socket storedSocket = new Socket();

	public ThreadPoolEchoServer(Socket clientSocket){
		storedSocket = clientSocket;	
	}
	public static void main(String[] args) {
		try {
			ServerSocket sock = new ServerSocket(6013);

			while (true) {
				try {
					Socket client = sock.accept();
					ExecutorService pool = Executors.newFixedThreadPool(4);
					pool.execute(new ThreadPoolEchoServer(client));
					//Thread clientThread = new Thread(new ThreadedEchoServer(client));
					//clientThread.start();
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
		OutputStream out = clientSocket.getOutputStream();
		int input;
		while ((input = isr.read()) != -1) {
			out.write(input);
			out.flush();
		}
		isr.close();
		clientSocket.close();

	}

}