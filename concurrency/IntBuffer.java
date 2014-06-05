package concurrency;

/**
 * Integer buffer of size 10 to be used by producers and consumers.
 * Thread safe via use of monitor (Synchronized)
 * 
 * @author Ankit Patel
 *
 */
public class IntBuffer {
	
	private int[] buffer = new int[10];
	private int currIndex = 0;
	
	/**
	 * Add a number to the buffer
	 * @param numToAdd Number to add
	 */
	public synchronized void add(int numToAdd){
		while(currIndex == buffer.length){
			
			try{
				wait();	//wait for signal when a spot is open
			}catch(InterruptedException e){
				System.err.println("ERROR OCCURED: " + e.toString());
			}
		}
		buffer[currIndex] = numToAdd;
		currIndex++;
		System.out.println(this);
		notifyAll();	//notify all waiting threads
		return;
	}
	
	/**
	 * Returns the last added item in the buffer
	 * @return	Last element that was added to the buffer
	 */
	public synchronized int remove(){
		while(currIndex == 0){
		
			try{
				wait();	//wait for signal when there is something new to process
			}catch(InterruptedException e){
				System.err.println("ERROR OCCURED: " + e.toString());
			}
		}

		currIndex--;
		System.out.println(this);
		notifyAll();	//notify all waiting threads
		return buffer[currIndex];
	}
	
	/**
	 * Human-readable, string-representation of the buffer
	 */
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for(int i=0; i<buffer.length;i++){
			sb.append(buffer[i]);
			sb.append(",");
		}
		sb.setLength(sb.length()-1);
		sb.append("]");
		return sb.toString();
	}

}
