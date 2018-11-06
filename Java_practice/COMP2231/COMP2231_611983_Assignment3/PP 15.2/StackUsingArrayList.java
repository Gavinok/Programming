/*
 * @CreateTime: Feb 12, 2018 12:51 PM
 * @Author: Gavin Jaeger-Freeborn
 * @Contact: gavinfre@uvic.ca
 * @Last Modified By: Gavin Jaeger-Freeborn
 * @Last Modified Time: Feb 12, 2018 4:12 PM
 * @Student Number:T00611983
 * @COMP 2231 Assignment 3 Question 2
 * @Description: StackUsingArrayList implemments a stack using an ArrayList.
 */

import java.util.ArrayList;
/**
 *
 * @author Gavin Jaeger-Freeborn
 * @version 1.0
 */
public class StackUsingArrayList<T> {
    private ArrayList<T> Stack;

    public StackUsingArrayList()
    {
        Stack = new ArrayList<T>();
    }
    /**  
	 * Adds the specified element to the top of this stack. 
	 * @param element element to be pushed onto the stack
	 */
    public void push(T element)
    {
        Stack.add(0, element);
    }
    /**  
	 * Removes and returns the top element from this stack. 
	 * @return the element removed from the stack
	 */
    public T pop() throws EmptyCollectionException
    {

        if (isEmpty())
            throw new EmptyCollectionException("stack");
            
        return Stack.remove(0);
    }
    /**  
	 * Returns without removing the top element of this stack. 
	 * @return the element on top of the stack
	 */
    public T peek() throws EmptyCollectionException
    {
        if (isEmpty())
            throw new EmptyCollectionException("stack");   
        
        return Stack.get(0);
    }
    /**  
	 * Returns true if this stack contains no elements. 
	 * @return true if the stack is empty
	 */
    public boolean isEmpty()
    {
        return (Stack.size() == 0);
    }
    /** 
	 * Returns the number of elements in this stack. 
	 * @return the number of elements in the stack
	 */
    public int size()
    {
        return Stack.size();
    }
    /**  
	 * Returns a string representation of this stack. 
	 * @return a string representation of the stack
	 */
    public String toString()
    {
        String StackString = "";
        for(int scan = 0; scan < Stack.size(); scan++) {
            StackString = StackString + Stack.get(scan) + ", ";
        }
        return StackString;
    }
    public static void main(String[] args)
    {
        StackUsingArrayList<Integer> stack = new StackUsingArrayList<Integer>();
        stack.push(new Integer(1));
        stack.push(new Integer(15));
        stack.push(new Integer(3));
        stack.push(new Integer(100));
        stack.push(new Integer(2));
        System.out.println(stack.pop());
        System.out.println();
        System.out.println(stack.peek());
        System.out.println();
        System.out.println(stack.size());
        System.out.println();
        System.out.println(stack.isEmpty());
        System.out.println();
        System.out.println(stack.toString());
        System.out.println(stack.pop());
        System.out.println();
        System.out.println(stack.pop());
        System.out.println();
        System.out.println(stack.pop());
        System.out.println();
        System.out.println(stack.pop());
        System.out.println();
        System.out.println(stack.pop());

    }
}