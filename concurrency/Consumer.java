package concurrency;

/**
 * Consumer thread to continuously consume and remove from the buffer
 * 
 * @author Ankit Patel
 *
 */
public class Consumer extends Thread{
	
	private IntBuffer buffer;
	
	public Consumer(IntBuffer buffer){
		this.buffer = buffer;
	}
	
	public void run(){
		while(true){
			int consumed = buffer.remove();
			System.out.println("Consumed: " + consumed);
		}
	}
}
