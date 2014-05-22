package linked_lists;
import java.util.ArrayList;

/**
 * Stack (Last In First Out) implementation using dynamic arrays
 * @author Ankit Patel
 *
 */
public class Stack_Impl_Using_Dynamic_Array {
	
	ArrayList<Integer> stack;	//Arraylist is an dynamic array that grows automatically as needed
	
	public Stack_Impl_Using_Dynamic_Array(){
		stack = new ArrayList<Integer>();
	}
	
	private int pop() throws IndexOutOfBoundsException {
		int retVal  = stack.remove(stack.size() -1);
		System.out.println("Popped " + retVal + " from the stack - - " + stack.toString());
		return retVal;
	}


	private void push(int i) {
		stack.add(i);
		System.out.println("Pushed " + i + " into the stack - - " + stack.toString() );
		
	}

	/*
	 * Test code
	 */
	public static void main(String[] args) {
		Stack_Impl_Using_Dynamic_Array stackImpl = new Stack_Impl_Using_Dynamic_Array();
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
		stackImpl.pop();
		stackImpl.pop();	//throws IndexOutOfBoundsException
	}
}
