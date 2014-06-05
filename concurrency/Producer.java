package concurrency;

import java.util.Random;

/**
 * Producer thread to continuously produce a integer and push to the buffer
 * 
 * @author Ankit Patel
 *
 */
public class Producer extends Thread {
	
	private IntBuffer buffer;
	
	public Producer(IntBuffer buffer){
		this.buffer = buffer;
	}
	
	public void run(){
		Random r = new Random();
		while(true){
			int toProduce = r.nextInt();
			buffer.add(toProduce);
			System.out.println("Produced: " + toProduce);
		} 
	}

}
