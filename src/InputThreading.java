import java.io.IOException;
import java.io.InputStream;

public class InputThreading implements Runnable {
	private InputStream storedInputStream;
    public InputThreading(InputStream input) {
    	storedInputStream = input;
    }
    
	@Override
	public void run() {
		try {
			writeToInputStream(storedInputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void writeToInputStream(InputStream input) throws IOException{
		int outputVal;
		while ((outputVal = input.read()) != -1) {
            System.out.write(outputVal);
            System.out.flush();
        }
	}
	
}
