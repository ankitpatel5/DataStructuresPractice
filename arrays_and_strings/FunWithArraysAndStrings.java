package arrays_and_strings;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.NoSuchElementException;

/**
 * 
 * @author Ankit Patel
 *
 */
public class FunWithArraysAndStrings {

	
	/**
	 * Returns the first character in the string that is not repeated
	 * For example, "total" will return "o"
	 * 				""teeter" will return "r"
	 *
	 *We can do a O(n^2) implementation by scanning each character in the string and then comparing with
	 *each of the other characters in the string and if we don't find a match, then we return that character
	 *
	 *However, we can get a more efficient implementation, O(n), if we use a hash table. The storing and
	 *lookup in a hash table is O(1) so we can go through each of the characters in the string and place
	 *it into the hash table where the key will be the character and the value will be the number of 
	 *occurances of that character. At the end, we just go through the hashmap and find the first key
	 *whose value is one and return that. If we don't find any, we throw an exception.
	 *
	 * @param str Input string
	 * @return the first character that is non-repeated. Throws an exception otherwise
	 */
	public char firstNonRepeatedCharInString(String str){
		
		//Create the hash table
		LinkedHashMap<Character, Integer> charOccurances =  new LinkedHashMap<Character, Integer>();
		
		for(int i=0; i<str.length(); i++){
			char c = str.charAt(i);
			boolean alreadyExists = charOccurances.containsKey(c);
			
			if(alreadyExists){
				charOccurances.put(c, charOccurances.get(c) + 1);
			}else{
				charOccurances.put(c, 1);
			}
		}
		
		
		//find the first key with value = =1
		Iterator<Character> iter = charOccurances.keySet().iterator();
		while(iter.hasNext()){
			char curr = iter.next();
			if(charOccurances.get(curr) == 1){
				return curr;
			}
		}
		
		//no non-repeated character was found
		throw new NoSuchElementException("No non-repeated character was found in string: " + str);
		
	}
	
	/**
	 * Removes a string of characters from another another string 
	 * @param fullString	The full string
	 * @param toRemove	String of characters to remove from the full string
	 * @return	A new string with the characters removed
	 */
	public String removeSpecifiedChars(String fullString, String toRemove){
		char[] str = fullString.toCharArray();
		char[] unwanted = toRemove.toCharArray();
		boolean[] toRemoveOrNot = new boolean[128];	//assume ascii
		
		//set array value to "true" if it needs to be removed
		for(int i=0; i<unwanted.length;i++){
			toRemoveOrNot[unwanted[i]] = true;		//e.g. if unwanted[i] = "a", it will translate to index 97 (Ascii)
		}
		
		int read;
		int write = 0;
		
		for(read=0; read<str.length;read++){	
			if(toRemoveOrNot[str[read]] == false){	//to keep
				if(!(read == write)){				//only need to write if read and write cursor not the same
					str[write] = str[read];
				}
				write++;
			}
		}
		
		return new String(str, 0, write);
	}
	
	/**
	 * Reverses the string (using space as delimiter).
	 * E.g. "Science is fun" will get returned as "fun is Science"
	 * @param orig	The input sentence
	 * @return	String sentence reversed
	 */
	public String reverseSentence(String orig){
		String[] words = orig.split(" ");
		StringBuffer sb = new StringBuffer();
		
		for(int i = words.length-1; i>=0; i--){
			sb.append(words[i]);
			sb.append(" ");
		}
		
		return sb.toString();
		
	}
	
	/**
	 * Converts a signed integer stored as an int back into a string without using any build-in functions
	 * @param number The number to convert
	 * @return The string representation of the number
	 */
	public String integerToString(int number){
		
		//special case
		if(number == 0){
			return "0";
		}

		int num = number;
		int num_nearest_tenth, num_tenth;
		boolean isNegative = false;
		
		StringBuffer sb = new StringBuffer();
		
		//set the negative flag if necessary
		if(num < 0){
			isNegative = true;
			num = num * -1; 	//we are going to work with positive value to do the next step
		}
		
		//Continuously figure out the last digit by using division and multiplication
		//and then truncate the last digit
		while(num>0){
			num_tenth = num/10;
			num_nearest_tenth = num_tenth * 10;
			int lastDigit = num - num_nearest_tenth;
			sb.append(lastDigit);
			num = num_tenth;
		}
		
		//if its a negative number, we need to add the sign at the beginning
		if(isNegative){
			sb.append("-");
		}
		
		//the string buffer is in reverse order, so we need to reverse it again
		sb.reverse();
		return sb.toString();
	}
	
	/**
	 * Converts a string representation of a signed integer (within the range of an int)
	 * back into a int primitive type without using any build-in functions
	 * @param strInt String representation of a signed integer
	 * @return The converted int value
	 */
	public int stringToInt(String strInt){
		
		int retVal = 0;
		
		char[] intCharArr = strInt.toCharArray();
		boolean isNegative = false;
		int index = 0;
		
		if('-' == intCharArr[0]){
			isNegative = true;
			index = 1;
		}
		
		//figure out the digit value of each element in the array and add accordingly to the running total
		char zero_ascii = '0';
		for(;index<intCharArr.length;index++){
			retVal*=10;
			int nextDigit = intCharArr[index] - zero_ascii;	//will give the difference in ASCII values
			retVal+=nextDigit;
		}
		
		//make it negative if necessary
		if(isNegative){
			retVal*=-1;
		}
		
		return retVal;
	}
	
	public static void main(String[] args) {
		FunWithArraysAndStrings test = new FunWithArraysAndStrings();
		System.out.println("First non-repeted character in TOTAL: " + test.firstNonRepeatedCharInString("TOTAL"));
		System.out.println("First non-repeted character in TEETER: " + test.firstNonRepeatedCharInString("TEETER"));
		
		//The action below should throw an exception
		//System.out.println("First non-repeted character in MAMA: " + test.firstNonRepeatedCharInString("MAMA"));

		String before = "Battle of the Vowels: Hawaii vs Grozny";
		String result = test.removeSpecifiedChars(before, "aeiou");
		System.out.println(before + " with the following characters removed: aeiou, equals ---> " + result);
		
		String toReverse = "Hi, hello! How are you doing today?";
		String reversed = test.reverseSentence(toReverse);
		System.out.println(toReverse + " <--- reserved is ---> " + reversed);
		
		System.out.println("Integer to String: Input: 12345, Output: " + test.integerToString(12345));
		System.out.println("Integer to String: Input: -101, Output: " + test.integerToString(-101));
		System.out.println("Integer to String: Input: -1, Output: " + test.integerToString(-1));
		System.out.println("Integer to String: Input: 0, Output: " + test.integerToString(0));
		System.out.println("Integer to String: Input: 1, Output: " + test.integerToString(1));
		
		System.out.println("String to Integer: Input: 12345, Output: " + test.stringToInt("12345"));
		System.out.println("String to Integer: Input: -101, Output: " + test.stringToInt("-101"));
		System.out.println("String to Integer: Input: -1, Output: " + test.stringToInt("-1"));
		System.out.println("String to Integer: Input: 0, Output: " + test.stringToInt("0"));
		System.out.println("String to Integer: Input: 1, Output: " + test.stringToInt("1"));
		
	}

}
