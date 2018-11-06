/*
 * @CreateTime: Feb 10, 2018 9:41 PM
 * @Author: Gavin Jaeger-Freeborn
 * @Contact: gavinfre@uvic.ca
 * @Last Modified By: Gavin Jaeger-Freeborn
 * @Last Modified Time: Feb 10, 2018 9:50 PM
 * @Student Number:T00611983
 * @COMP 2231 Assignment 2 Question 4
 * @Description: PalindromeTester takes a word and determins 
 * if it is a palindrome.
 */


import java.util.*;
import java.lang.Character;

public class PalindromeTester
{
    private ArrayStack<String> TestingStack = new ArrayStack<String>();
    private LinkedQueue<String> TestingQueue = new LinkedQueue<String>();
    /**
	 * Creates an PalindromeTester object and prints wither 
     * or not the input is a palindrome.
	 * @param WordToBeTested the string that will be tested
	 */
    public PalindromeTester(String WordToBeTested)
    {
        String charHolder = new String();
        for (int iter = 0; iter < WordToBeTested.length(); iter++) {
            charHolder = String.valueOf(WordToBeTested.charAt(iter));
            TestingStack.push(charHolder);
            TestingQueue.enqueue(charHolder);
        }
        if(isPalindrome())
        {
            System.out.println("The word " + WordToBeTested + " is a palindrome");
        } 
        else
        {
            System.out.println("The word " + WordToBeTested + " is not a palindrome");
        }
    }
     /**
	 * Tests the word to see if it is a palindrome.
     */
    public boolean isPalindrome() throws EmptyCollectionException
    {
        if (TestingStack.isEmpty())
            throw new EmptyCollectionException("queue");

        boolean result = true;
        for(int iter = 0; iter < TestingStack.size(); iter++ )
        {
           if(!(TestingStack.peek().equalsIgnoreCase(TestingQueue.first())))
           {
                return false;
           }
        }
        return true;
    }
     /**
	 * scans for a word from the user then tests to see if it is
     * a palindrome.
     */
    public static void main(String[] args)
    {
        System.out.print("please type the word you would like to test: ");
        Scanner scanner = new Scanner(System.in);
        String WordToBeTested = scanner.next();
        PalindromeTester testTistWord = new PalindromeTester(WordToBeTested);
    }

}