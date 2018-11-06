/*
 * @CreateTime: Feb 12, 2018 12:51 PM
 * @Author: Gavin Jaeger-Freeborn
 * @Contact: gavinfre@uvic.ca
 * @Last Modified By: Gavin Jaeger-Freeborn
 * @Last Modified Time: Feb 13, 2018 4:12 PM
 * @Student Number:T00611983
 * @COMP 2231 Assignment 3 Question 2
 * @Description: StackTestDriver tests the following methosd in the StackUsingArrayList class:
 * 1. push()
 * 2. peek()
 * 3. toString()
 * 4. size()
 * 5. isEmpty()
 * 6. pop()
 * finally the driver tests to see if the exceptions can be identified.
 */
import java.util.*;
public class StackTestDriver
{
    public static void main(String[] args)
    {
        //create an empty stack
        StackUsingArrayList<Integer> stack = new StackUsingArrayList<Integer>();

        //fill the stack with the following numbers: 1, 15 3 100, and 2 in that order
        stack.push(new Integer(1));
        System.out.println("1 has been pushed");
        stack.push(new Integer(15));
        System.out.println("15 has been pushed");
        stack.push(new Integer(3));
        System.out.println("3 has been pushed");
        stack.push(new Integer(100));
        System.out.println("100 has been pushed");
        stack.push(new Integer(2));
        System.out.println("2 has been pushed");
        System.out.println();
        //print the top of the stack 
        System.out.println("The top element of the stack is: " + stack.peek());
        System.out.println();
        //print the stack from top to bottom
        System.out.println("This is the stack now: " + stack.toString());
        System.out.println();
        //print the size of the stack
        System.out.println("The current size of the stack is: " + stack.size());
        System.out.println();
        //print if the stack is empty
        System.out.println("Is the stack empty?: " + stack.isEmpty());
        System.out.println();
        //pop and print each element of the stack one at a time
        System.out.println(stack.pop() + " has been popped");
        System.out.println();
        System.out.println(stack.pop() + " has been popped");
        System.out.println();
        System.out.println(stack.pop() + " has been popped");
        System.out.println();
        System.out.println(stack.pop() + " has been popped");
        System.out.println();
        System.out.println(stack.pop() + " has been popped");
        System.out.println();
        //prove that the exeptions are found
        stack.pop();
    
    }
}