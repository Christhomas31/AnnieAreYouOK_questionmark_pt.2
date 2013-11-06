import java.io.IOException;
import java.io.OutputStream;


public class OutputThreading implements Runnable{
    private OutputStream storedOutputStream;
    public OutputThreading(OutputStream output) {
    	storedOutputStream = output;
    }
    
	@Override
	public void run() {
		try {
			writeToOutputStream(storedOutputStream);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public void writeToOutputStream(OutputStream output) throws IOException, InterruptedException{
		int input;
		while ((input = System.in.read()) != -1) {
            output.write(input);
            output.flush();
        }
		
	}
}
