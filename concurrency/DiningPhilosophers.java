package concurrency;

/**
 * 
 * Five introspective and introverted philosophers are sitting at a circular table. In
 * front of each philosopher is a plate of food. A fork lies between each
 * philosopher, one by the philosopher’s left hand and one by the right hand. A
 * philosopher cannot eat until he or she has both forks in hand. Forks are picked up
 * one at a time. If a fork is unavailable, the philosopher simply waits for the fork to be
 * freed. When a philosopher has two forks, he or she eats a few bites and then returns
 * both forks to the table. If a philosopher cannot obtain both forks for a long time, he
 * or she will starve. Is there an algorithm that will ensure that no philosophers starve?
 * 
 * @author Ankit Patel
 *
 */
public class DiningPhilosophers {

	private Object[] forks;
	private Philosopher[] philosophers;
	
	/**
	 * Constructor to initialize the forks and philosophers
	 * @param numPhilosophers
	 */
	public DiningPhilosophers(int numPhilosophers){
		forks = new Object[numPhilosophers];
		philosophers = new Philosopher[numPhilosophers];
		
		for(int i=0; i<numPhilosophers; i++){
			forks[i] = new Object();
			/**
			 * Deadlock can occur if all philosophers each pick up the left fork,
			 * and then everyone is blocked to get the right fork.
			 * In order to avoid the deadlock, we can have one of the philosopher
			 * pick up the right fork first so not all are picking up the 
			 * left fork.
			 * 
			 * We will have the first philosopher pick up the right fork and 
			 * then the left
			 */
			if(i==0){
				philosophers[i] = new Philosopher(i, numPhilosophers-1, i);
			}else{
				philosophers[i] = new Philosopher(i, i, i-1);
			}
		}
		
	}
	
	//Start philosophers thread so they can start eating
	public void startEating() throws InterruptedException{
		for(int i = 0; i<philosophers.length;i++){
			philosophers[i].start();
		}
		
		//philosophers[0].join();

	}
	
	
	/**
	 * Inner class to represent a philosopher
	 */
	public class Philosopher extends Thread{
		
		private int id;
		private int leftFork;
		private int rightFork;
		
		public Philosopher(int id, int leftFork, int rightFork){
			this.id = id;
			this.leftFork = leftFork;
			this.rightFork = rightFork;
		}
		
		public void run(){
			
			System.out.println("Philosopher " + id + " ready to eat using forks " + leftFork + " and " + rightFork);
			pause();	//pause to allow all threads to get ready
			
			while(true){
				
				System.out.println("Philosopher " + id + " picking up first fork:  " + leftFork);
				
				synchronized (forks[leftFork]) {
					System.out.println("Philosopher " + id + " picking up second fork:  " + rightFork);
					
					synchronized (forks[rightFork]){
						System.out.println("Philosopher " + id + " has both forks - - EATING!");
						
					}
				}
			}
		}

		private void pause() {
			try{
				sleep(200);
			}catch(InterruptedException e){
				//NO OP
			}
		}

	}

	
	
	public static void main(String[] args) throws InterruptedException{
		
		DiningPhilosophers dp = new DiningPhilosophers(5);
		dp.startEating();
		
	}
}

