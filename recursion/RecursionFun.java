package recursion;

import org.omg.CORBA.UserException;

/**
 * 
 * @author Ankit Patel
 *
 */
public class RecursionFun {

	/**
	 * Finds the index of a value of interest within a sorted integer array using
	 * binary search in a recursive manner.
	 * 
	 * @param arr The given sorted integer array
	 * @param start The index to start the search from (inclusive)
	 * @param end The index to end the search at (inclusive)
	 * @param value	The value to be searched
	 * @return The index of the value in the array
	 * @throws Exception 
	 */
	public int binarySearch(int[] arr, int start, int end, int value) throws Exception{
		
		/**
		 * Error checking
		 */
		if(start>end){
			throw new Exception("Start paramenter cannot be higher than the end parameter");
		}
		
				
		/**
		 * Base case: search the middle element
		 */
		int midElement = start + ((end - start) / 2);
		if (arr[midElement] == value){
			return midElement;
		}else if(arr.length == 1){
			//value not in the array
			throw new Exception("Value " + value + " not found in array");
		}
		
		/**
		 * Recursive case:
		 * If value>midElement, look at the right subset of the array
		 * If value<midElement, look at the left subset of the array
		 */
		if(value > arr[midElement]){
			return binarySearch(arr, midElement+1, end, value);
		}else{
			return binarySearch(arr, start, midElement-1, value);
		}
			
	}
	
	/**
	 * Prints out all the permutations of a word
	 * 
	 * E.g. "ABC" would give us "ABC, ACB, BAC, BCA, CAB, CBA"
	 * 
	 * @param word Word to permute
	 */
	public void printWordPermutations(String word){
		int length = word.length();
		boolean[] visited = new boolean[length];
		int currentIndex = 0;
		char[] wordArr = word.toCharArray();
		StringBuffer sb = new StringBuffer();
		
		permuteWord(sb, length, visited, currentIndex, wordArr);
		

	}
	
	private void permuteWord(StringBuffer sb, int length, boolean[] visited,
			int currentIndex, char[] wordArr) {
		
		/**
		 * Base case.
		 */
		if(currentIndex == length){
			System.out.println(sb);
		}
		
		/**
		 * Recursive case
		 */
		for(int i = 0; i<length; i++){
			//check to see if the character at index i has already been used
			if( visited[i]){
				continue;
			}
			
			//if not, add and recurse
			sb.append(wordArr[i]);
			visited[i] = true;
			permuteWord(sb, length, visited, currentIndex + 1, wordArr);
			
			//now that we have found the word, we need to reset the flags for the next set of permutation
			visited[i] = false;
			//also need to trim lenght of string by one to allow it to be replaced by another permutation
			sb.setLength(sb.length() -1);
		}
	}
	
	/**
	 * Prints all possible combinations of the characters in a string.
	 * These combinations range in length from one to the length of the string.
	 * 
	 * Two combinations that differ only in ordering of their characters are the same combinations.
	 * (e.g. "12" and "31" are different combinations from the input string "123", but "12" is the same as "21"
	 * 
	 * E.g. "ABC" would give "A AB, ABC, AC, B, BC, C"
	 * 
	 * @param word
	 */
	public void printWordCombination(String word){
		
		if("".equals(word)){
			System.out.println("");
		}
		
		char[] wordArr = word.toCharArray();
		StringBuilder sb = new StringBuilder();
		combineWord(wordArr, sb, 0);		
	}

	private void combineWord(char[] wordArr, StringBuilder sb, int i) {
		for(int currPos = i; currPos<wordArr.length; currPos++){
			sb.append(String.valueOf(wordArr[currPos]));
			System.out.println(sb.toString());
			
			if(currPos+1 < wordArr.length){
				combineWord(wordArr, sb, currPos + 1);
			}
			sb.setLength(sb.length() -1);
		}
	}

	/**
	 * Playground for testing
	 */
	public static void main(String[] args)throws Exception {

		RecursionFun recursiveTest = new RecursionFun();
		System.out.println("Finding 8 in [1,2,3,4,5,6,7,8,9,10]: Index " +  recursiveTest.binarySearch(new int[]{1,2,3,4,5,6,7,8,9,10}, 0, 9, 8));
		System.out.println("Finding 2 in [1,2,3,4,5,6,7,8,9]: Index " +  recursiveTest.binarySearch(new int[]{1,2,3,4,5,6,7,8,9}, 0, 8, 2));
		System.out.println("Finding 40 in [5,10,40,70,100,5000,60000,1000000]: Index " +  recursiveTest.binarySearch(new int[]{5,10,40,70,100,5000,60000,1000000}, 0, 7, 40));
		System.out.println("Finding 1 in [1]: Index " +  recursiveTest.binarySearch(new int[]{1}, 0, 0, 1));
		
		System.out.println("All permutations of abc: ");
		recursiveTest.printWordPermutations("abc");
		
		System.out.println("\nAll permutations of ABCD: ");
		recursiveTest.printWordPermutations("ABCD");
		
		System.out.println("\nAll combinations of abc: ");
		recursiveTest.printWordCombination("wxyz");
		
		
		
	
	}

}