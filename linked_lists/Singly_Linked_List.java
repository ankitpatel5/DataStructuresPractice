package linked_lists;

/**
 * 
 * @author Ankit Patel
 *
 */
public class Singly_Linked_List {

	Node head, tail;
	int numItems;
	
	public Singly_Linked_List(int data){
		head = new Node(data);
		tail = head;
		numItems = 1;
	}
	
	/**
	 * Assumes its adding data to the end
	 */
	public boolean add(int data){
		Node toAdd = new Node(data);
		toAdd.next = null;
		
		tail.next = toAdd;
		tail = toAdd;
		numItems++;
		System.out.println("Added " + data + " into the stack - - " + this.toString());
		return true;
	}
	
	public int getMthToLast(int mFromLast){
		if(mFromLast == 0){
			System.out.println(mFromLast + " from last = " + tail.data);
			return tail.data;
		}
		
		int nFromBeginning = numItems - mFromLast -1;
		
		if(mFromLast < 0 || nFromBeginning < 0 ){
			System.out.println("Index out of range");
			throw new IndexOutOfBoundsException();
		}

		Node nodeOfInterest = head;
		for(int i=1; i<=nFromBeginning; i++){
			nodeOfInterest = nodeOfInterest.next;
		}
		
		System.out.println(mFromLast + " from last = " + nodeOfInterest.data);
		return nodeOfInterest.data;
	}
	
	public String toString(){
		if(numItems == 0){
			return "{}";
		}
		
		Node tempHead = head;
		String retVal = "{" + tempHead.data + ",";
		for(int i = 1; i<numItems; i++){
			tempHead = tempHead.next;
			retVal += tempHead.data + ",";
		}
		retVal = retVal.substring(0,retVal.length()-1) + "}";
		return retVal;
	}
	
	public static void main(String[] args) {
		Singly_Linked_List linkedList = new Singly_Linked_List(1);
		linkedList.add(2);
		linkedList.add(3);
		linkedList.add(4);
		linkedList.add(5);
		linkedList.add(6);
		linkedList.add(7);
		linkedList.add(8);
		
		linkedList.getMthToLast(0);
		linkedList.getMthToLast(7);
		linkedList.getMthToLast(3);

	}
	
	class Node{
		Node next;
		int data;
		
		Node(int data){
			this.data = data;
		}
		
		Node(){
		}
		
		@Override
		public boolean equals(Object toCompare){

			if(!(toCompare instanceof Node)){
				return false;
			}
			return this.data == ((Node)toCompare).data;
			
		}
	}
}
