package concurrency;

/**
 * Executes the producer and consumer threads
 * @author Ankit Patel
 *
 */
public class ProducerComsumerTest {


	public static void main(String[] args) {
		
		IntBuffer buffer = new IntBuffer();
		Producer p = new Producer(buffer);
		Consumer c = new Consumer(buffer);
		p.start();	//start producer thread
		c.start();	//start consumer thread
	}

}
