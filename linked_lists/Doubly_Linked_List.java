package linked_lists;
import java.util.ArrayList;

/**
 * Doubly linked list
 * @author Ankit Patel
 *
 */
public class Doubly_Linked_List {

	Node head, tail;
	int numItems;
	
	
	public Doubly_Linked_List(int data) {
		head = new Node(data);
		tail = head;
		numItems = 1;
	}
	
	private boolean remove(Node toDelete){
		//special case: removing head
		if(toDelete.equals(head)){
			head = head.next;
			numItems--;
			System.out.println("Removed " + toDelete.data + " from the LinkedList - - " + this.toString());
			return true;
		}
		
		//special case: removing tail
		if(toDelete.equals(tail)){
			tail = tail.prev;
			tail.next = null;
			numItems--;
			System.out.println("Removed " + toDelete.data + " from the LinkedList - - " + this.toString());
			return true;
		}
		
		//general case: element in the middle
		Node elemToDelete = head.next;
		while(!(elemToDelete==null) && !(elemToDelete.equals(toDelete))){
			elemToDelete = elemToDelete.next;
		}
		
		if(elemToDelete == null){
			System.out.println("Could not find " + toDelete.data + " in LinkedList");
			return false;
		}else{
			elemToDelete.prev.next = elemToDelete.next;
			elemToDelete.next.prev = elemToDelete.prev;
			elemToDelete.next = null;
			elemToDelete.prev = null;
			numItems--;
			System.out.println("Removed " + toDelete.data + " from the LinkedList - - " + this.toString());
			return true;
		}
			
	}

	
	/**
	 * Add's a node with data: dataToAdd after Node nodeBefore
	 */
	private boolean add(Node nodeBefore,  int dataToAdd) {
		
		Node toAdd = new Node(dataToAdd);
		
		//special case: adding before head
		if(nodeBefore == null){
			head.prev = toAdd;
			toAdd.prev = null;
			toAdd.next = head;
			head = toAdd;
			numItems++;
			System.out.println("Added " + dataToAdd + " into the stack - - " + this.toString());
			return true;
		}
		
		//special case: adding after tail
		if(nodeBefore.equals(tail)){
			tail.next = toAdd;
			toAdd.prev = tail;
			tail = toAdd;
			numItems++;
			System.out.println("Added " + dataToAdd + " into the stack - - " + this.toString());
			return true;
		}
		
		//general case: element in the middle
		Node elemBefore = head.next;
		while(!(elemBefore==null) && !(elemBefore.equals(nodeBefore))){
			elemBefore = elemBefore.next;
		}
		
		if(elemBefore == null){
			System.out.println("Could not find " + nodeBefore.data + " in LinkedList");
			return false;
		}else{
			toAdd.prev = elemBefore;
			toAdd.next = elemBefore.next;
			elemBefore.next.prev = toAdd;
			elemBefore.next = toAdd;
			numItems++;
			System.out.println("Added " + dataToAdd + " into the stack - - " + this.toString());
			return true;
		}
		
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
	
	public void test1(){
		this.add(new Node(6), 7);
		this.add(new Node(3), 10);
		
		this.remove(new Node(1));
		this.remove(new Node(7));
		this.remove(new Node(10));
		
		this.remove(new Node(11));
		
		this.add(new Node(15), 1);
		
		
		
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Doubly_Linked_List linkedList = new Doubly_Linked_List(6);
		
		linkedList.add(null, 5);
		linkedList.add(null, 4);
		linkedList.add(null, 3);
		linkedList.add(null, 2);
		linkedList.add(null, 1);
		linkedList.test1();
		
		

	}
	
	class Node{
		Node next;
		Node prev;
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
