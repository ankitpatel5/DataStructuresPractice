package trees_and_graphs;

import java.util.LinkedList;
import java.util.Queue;


/**
 * Implementation of a binary search tree.
 * BST has two special properties:
 * 1) Each node has 0,1, or 2 children
 * 2) Left child is less than the parent node; right child is greater than the parent node
 * 
 * @author Ankit Patel
 *
 */
public class BST {

	Node head;

	public BST(Node head){
		this.head = head;
	}

	public BST(){
		this.head = null;
	}

	public Node findNode(Node root, int value){
		if(root == null){
			return null;
		}

		//if current root is not the data of interest
		//compare the value and go left or right of the BST accordingly
		while(root.data != value){
			if(root.data < value){
				root = root.right;
			}else{
				root = root.left;
			}
		}

		return root;
	}

	/**
	 * Find a given value in the BST given the pointer to the root
	 */
	public Node findNodeWithRecursive(Node root, int value){
		if(root == null){
			return null;
		}

		//base case
		if(root.data == value){
			return root;
		}else{
			//recursive case			
			if(root.data < value){
				return findNodeWithRecursive(root.right, value);
			}else{
				return findNodeWithRecursive(root.left, value);
			}
		}
	}

	/**
	 * preorder = root, left, right
	 * Traversal without recursion using stack
	 */
	public void preOrderTraversal(Node root){
		if(root == null){
			return;
		}

		Stack stack = new Stack();
		stack.push(root);

		while(stack.numItems != 0){
			Node poppedNode = stack.pop();
			System.out.print(poppedNode.data + " ");
			if(poppedNode.right != null){
				stack.push(poppedNode.right);
			}
			if(poppedNode.left != null){
				stack.push(poppedNode.left);
			}			
		}	
	}

	/**
	 * preorder = root, left, right
	 * Traversal using recursion
	 */
	public void preOrderTraversalWithRecursive(Node root){
		if(root == null){
			return;
		}

		//print root
		System.out.print(root.data + " ");
		//print left
		preOrderTraversalWithRecursive(root.left);
		//print right
		preOrderTraversalWithRecursive(root.right);

	}

	/**
	 * inorder = left, root, right
	 * Traversal without recursion using stack
	 */
	public void inOrderTraversal(Node root){
		if(root == null){
			return;
		}
		
		Stack stack = new Stack();
		stack.push(root);
		Node currNode = root;
		//push left childs until no left childs left
		pushLeftChilds(currNode, stack);
		
		//pop and print and go right and push and repeat pushing of left childs until nothing left in stack
		while(stack.numItems != 0){
			Node poppedNode = stack.pop();
			System.out.print(poppedNode.data + " ");
			
			if(poppedNode.right !=null){
				stack.push(poppedNode.right);
				pushLeftChilds(poppedNode.right, stack);
			}
		}
	}
	
	/**
	 * Helper method to push the left child iteratively into the stack
	 */
	private void pushLeftChilds(Node currNode, Stack currStack){
		Node toPush = currNode;
		while(toPush.left != null){
			currStack.push(toPush.left);
			toPush = toPush.left;
		}	
	}
	
	/**
	 * inorder = left, root, right
	 * Traversal using recursion
	 */
	public void inOrderTraversalWithRecursive(Node root){
		if(root == null){
			return;
		}

		//print left
		inOrderTraversalWithRecursive(root.left);
		//print root
		System.out.print(root.data + " ");
		//print right
		inOrderTraversalWithRecursive(root.right);

	}

	/**
	 * postorder = left, right, root
	 * Traversal without recursion using two stacks
	 * 
	 * If we observe the reserve order of the post order traversal, we notice its a modification of a pre-order traversal
	 * where instead of traversing (left,root,right), it traverses (right,root,left). We can use one stack to traverse
	 * this way and store the results into a stack, so that when we remove the nodes from the stack in the end, it
	 * will give us the reserve order that we are trying to achieve, which will result in a post order traversal.
	 * 
	 */
	public void postOrderTraversalWithTwoStacks(Node root){
		if(root == null){
			return;
		}

		Stack stackProcess = new Stack();
		Stack stackResult = new Stack();
		
		stackProcess.push(root);

		//Process stack by doing a reverse pre-order traversal (right,root,left) and put the results into the result stack
		while(stackProcess.numItems != 0){
			Node poppedNode = stackProcess.pop();
			stackResult.push(poppedNode);
			
			if(poppedNode.left !=null){
				stackProcess.push(poppedNode.left);
			}
			if(poppedNode.right !=null){
				stackProcess.push(poppedNode.right);
			}
		}
		
		//Now result stack is filled with the nodes in a post-order manner so we just pop them
		while(stackResult.numItems != 0){
			System.out.print(stackResult.pop().data + " ");
		}
	}
	
	/**
	 * postorder = left, right, root
	 * Traversal without recursion using one stack
	 * 
	 * 1. 
	 * In order to achieve postorder with just one stack, we start at the root node and traverse to the left childs,
	 * and along the way, if the root has a right child, we add the right child to the stack, and then the root.
	 * Then we move to root's left child and repeat the process until no left children are left.
	 * 
	 * 2.
	 * Now until the stack is empty, we pop a node (say node1) and check if node1 has a right child. If it doesn't, we print
	 * that node's data, and move on. If it does have a right child, we first check if it's already been added to the
	 * stack. So we pop the next node from the stack (say node2) and if its the right child of node1, we push back node1 into
	 * the stack and repeat step 1 with node2 as the root of the subtree. If node2 is not the right child of node1, then we 
	 * stick back node2 into the stack and print node1. When the last element in the stack is popped, we just print that
	 * and we are done
	 * 
	 */
	public void postOrderTraversalWithOneStack(Node root){
		if(root == null){
			return;
		}

		Stack stack = new Stack();
		
		loadUpStack(root, stack); //traverses to the left childs and adds the right child and parent node along the way
		
		while(stack.numItems != 0){
			Node poppedNode = stack.pop();
						
			if(poppedNode.right == null || stack.numItems == 0){
				System.out.print(poppedNode.data + " ");
				continue;
			}else{ 	//right child exists
				//check if the child has already been pushed to the stack
				//if so, traverse down the right child and load up stack
				Node nextInStack = stack.pop();
				if(poppedNode.right.equals(nextInStack)){
					stack.push(poppedNode);
					loadUpStack(nextInStack, stack);
				}else{
					//the next in stack is not a child so push it back
					stack.push(nextInStack);
					System.out.print(poppedNode.data + " ");
				}
				
			}
		}
	}
	
	/**
	 * Helper method to add the right child and parent to the stack while travering to the left children
	 */
	private void loadUpStack(Node root, Stack stack) {
		Node currNode = root;
		while(currNode !=null){
			if(currNode.right !=null){
				stack.push(currNode.right);
			}
			stack.push(currNode);
			currNode = currNode.left;
		}
		//System.out.println(stack.toString());
	}

	/**
	 * postorder = left, right, root
	 * Traversal using recursion
	 */
	public void postOrderTraversalWithRecursive(Node root){
		if(root == null){
			return;
		}

		//print left
		postOrderTraversalWithRecursive(root.left);
		//print right
		postOrderTraversalWithRecursive(root.right);
		//print root
		System.out.print(root.data + " ");

	}

	/**
	 * Breadth first traversal traverses top level to bottom level, from left to right in each level
	 */
	public void breadthFirstTraversal(Node root){
		if(root == null){
			return;
		}

		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		
		while(!queue.isEmpty()){
			Node currNode = queue.poll();
			System.out.print(currNode.data + " ");
			
			if(currNode.left !=null){
				queue.add(currNode.left);
			}
			
			if(currNode.right !=null){
				queue.add(currNode.right);
			}
		}

	}
	
	public void findLowestCommonAncestor(Node root, int val1, int val2){
		
		while(root!=null){
			int value = root.data;
			if(value > val1 && value > val2){
				root = root.left;
			}else if(value < val1 && value < val2){
				root = root.right;
			}
			System.out.println("Lowest common ancestor of " + val1 + " and " + val2 + " is: " + root.data);
			break;
		}
	}


	/**
	 * Node class
	 */
	class Node{
		Node left;
		Node right;
		int data;

		Node next;
		Node prev;

		Node(int data){
			this.data = data;
		}

		@Override
		public boolean equals(Object toCompare){

			if(!(toCompare instanceof Node)){
				return false;
			}
			return this.data == ((Node)toCompare).data;

		}
	}


	public class Stack {

		public Node head, tail;
		public int numItems;

		public Stack() {
			head = null;	
			tail = null;
			numItems = 0;
		}

		public Node pop() throws NullPointerException {

			Node retVal = tail;
			numItems--;
			
			if(numItems == 0){
				head = null;
				tail = null;
			}else{
				tail = tail.prev;
				tail.next = null;
			}
			
			//System.out.println("Popped " + retVal.data + " from the stack - - " + this.toString());
			return retVal;
		}


		public void push(Node toAdd) {

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
			//System.out.println("Pushed " + toAdd.data + " into the stack - - " + this.toString());

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
	}

	/**
	 * Execute test class
	 * @param args
	 */
	public static void main(String[] args) {
		BST bst = new BST();
		TestableBST testBST =bst.new TestableBST();
		testBST.runTest();

	}




	/**
	 * Test class
	 */
	class TestableBST{

		public Node createBST_A(){
			/**
			 *            10
			 *        8       15
			 *      5   9   13  20
			 *
			 */
			Node a = new Node(10);
			Node b = new Node(8);
			Node c = new Node(15);
			Node d = new Node(5);
			Node e = new Node(9);
			Node f = new Node(13);
			Node g = new Node(20);

			a.left = b;
			a.right = c;
			b.left = d;
			b.right = e;
			c.left = f;
			c.right = g;
			return a;
		}

		public Node createBST_B(){
			/**
			 *            100
			 *        50       150
			 *      25  75   125  175
			 *             110 
			 */
			Node a = new Node(100);
			Node b = new Node(50);
			Node c = new Node(150);
			Node d = new Node(25);
			Node e = new Node(75);
			Node f = new Node(125);
			Node g = new Node(175);
			Node h = new Node(110);


			a.left = b;
			a.right = c;
			b.left = d;
			b.right = e;
			c.left = f;
			c.right = g;
			f.left = h;
			return a;
		}

		public void runTest(){

			Node a = createBST_A();
			BST myBST = new BST(a);
			Node retVal = myBST.findNode(a, 13);
			System.out.println("Trying to find 13: Found " + retVal.data); 
			retVal = myBST.findNode(a, 9);
			System.out.println("Trying to find 9: Found " + retVal.data); 
			retVal = myBST.findNodeWithRecursive(a, 13);
			System.out.println("Trying to find 13 recursively: Found " + retVal.data); 
			retVal = myBST.findNodeWithRecursive(a, 9);
			System.out.println("Trying to find 9 recursively: Found " + retVal.data); 
			System.out.println();
			
			Node b = createBST_B();

			System.out.println("   ------TREE------");
			System.out.println("         100");
			System.out.println("   50          150");
			System.out.println("25    75    125   175");
			System.out.println("         110");
			System.out.println();
			
			System.out.print("PRE ORDER w/ recursive: ");
			preOrderTraversalWithRecursive(b);
			System.out.println();
			
			System.out.print("PRE ORDER no recursive: ");
			preOrderTraversal(b);
			System.out.println();			
			
			
			System.out.print("IN ORDER w/ recursive: ");
			inOrderTraversalWithRecursive(b);
			System.out.println();
			
			System.out.print("IN ORDER no recursive: ");
			inOrderTraversal(b);
			System.out.println();
			
			System.out.print("POST ORDER w/ recursive: ");
			postOrderTraversalWithRecursive(b);
			System.out.println();			
			
			System.out.print("POST ORDER no recursive (using two stacks): ");
			postOrderTraversalWithTwoStacks(b);
			System.out.println();	
			
			System.out.print("POST ORDER no recursive (using one stacks): ");
			postOrderTraversalWithOneStack(b);
			System.out.println();	
			
			System.out.print("Breadth First Traversal no recursive: ");
			breadthFirstTraversal(b);
			System.out.println();			
			
			System.out.print("LCA Test -->  ");
			findLowestCommonAncestor(b, 25, 75);	
			System.out.print("LCA Test -->  ");
			findLowestCommonAncestor(b, 110, 175);
			System.out.println();	
			
		}

	}
}
