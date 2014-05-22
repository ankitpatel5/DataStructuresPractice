package linked_lists;
import java.util.ArrayList;

/**
 * Stack (Last In First Out) implementation using Singly linked list
 * @author Ankit Patel
 *
 */
public class Stack_Impl_Using_Linked_List {

	public Node head, tail;
	public int numItems;
	
	public Stack_Impl_Using_Linked_List() {
		head = null;	
		tail = null;
		numItems = 0;
	}
	
	public int pop() throws NullPointerException {
		
		int retVal = tail.data;
		tail = tail.prev;
		tail.next = null;
		numItems--;
		System.out.println("Popped " + retVal + " from the stack - - " + this.toString());
		return retVal;
	}


	public void push(int i) {
		Node toAdd = new Node(i);
		toAdd.next = null;

		//first element in stack
		if(numItems == 0){
			head = toAdd;
			head.next = null;
			tail = toAdd;
		}else{
			toAdd.prev = tail;
			tail.next = toAdd;
			tail = toAdd;
		}
		numItems++;
		System.out.println("Pushed " + i + " into the stack - - " + this.toString());
		
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
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Stack_Impl_Using_Linked_List stackImpl = new Stack_Impl_Using_Linked_List();
		stackImpl.push(1);
		stackImpl.push(2);
		stackImpl.push(3);
		stackImpl.push(4);
		stackImpl.push(5);
		stackImpl.pop();
		stackImpl.pop();
		stackImpl.pop();
		stackImpl.push(6);
		stackImpl.pop();
		stackImpl.push(7);
		stackImpl.pop();
		stackImpl.pop();
		
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
