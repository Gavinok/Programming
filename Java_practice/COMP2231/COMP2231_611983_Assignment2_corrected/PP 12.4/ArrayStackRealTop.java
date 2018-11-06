/*
 * @CreateTime: Feb 10, 2018 3:56 PM
 * @Author: Gavin Jaeger-Freeborn
 * @Contact: gavinfre@uvic.ca
 * @Last Modified By: Gavin Jaeger-Freeborn
 * @Last Modified Time: Feb 10, 2018 9:51 PM
 * @Student Number:T00611983
 * @COMP 2231 Assignment 2 Question 1
 * @Description: An array implementation of a stack in which the original node 
 *               is the top of the stack.
 */

import java.util.*;

/**
 *
 * @author Java Foundations
 * @version 4.0
 * NOTE: This version contains the implementation of several
 * methods left as a programming exercise.
 */
public class ArrayStackRealTop<T> implements StackADT<T>
{
    private final static int DEFAULT_CAPACITY = 100;

    private int top;
    private T[] stack;
	private int size = 0;
    /**
     * Creates an empty stack using the default capacity.
     */
    public ArrayStackRealTop()
    {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Creates an empty stack using the specified capacity.
     * @param initialCapacity the initial size of the array
     */
    public ArrayStackRealTop(int initialCapacity)
    {
        top = 0;
        stack = (T[])(new Object[initialCapacity]);
    }

    /**
     * Adds the specified element to the top of this stack, expanding
     * the capacity of the array if necessary.
     * @param element generic element to be pushed onto stack
     */
    public void push(T element)
    {
        if (size() == stack.length)
            expandCapacity();
		
		if(!isEmpty())
			top++;
		
		stack[top] = element;
        
		size++;
    }

    /**
     * Creates a new array to store the contents of this stack with
     * twice the capacity of the old one.
     */
    private void expandCapacity()
    {
        stack = Arrays.copyOf(stack, stack.length * 2);
    }

    /**
     * Removes the element at the top of this stack and returns a
     * reference to it.
     * @return element removed from top of stack
     * @throws EmptyCollectionException if stack is empty
     */
    public T pop() throws EmptyCollectionException
    {
        if (isEmpty())
            throw new EmptyCollectionException("stack");

        
		// return the current element from the top
        T result = stack[top];
        stack[top] = null;
		//move down the stack
		top--;
		//decrease the size of the stack
		size--;

        return result;
    }

    /**
     * Returns a reference to the element at the top of this stack.
     * The element is not removed from the stack.
     * @return element on top of stack
     * @throws EmptyCollectionException if stack is empty
     */
    public T peek() throws EmptyCollectionException
    {
        if (isEmpty())
            throw new EmptyCollectionException("stack");

		//return the element at the top of the stack
        return stack[top];
    }

    /**
     * Returns true if this stack is empty and false otherwise.
     * @return true if this stack is empty
     */
    public boolean isEmpty()
    {
        //check if the size is equil to zero
        return (size == 0);
    }

    /**
     * Returns the number of elements in this stack.
     * @return the number of elements in the stack
     */
    public int size()
    {
        return size;
    }

    /**
     * Returns a string representation of this stack.
     * @return a string representation of the stack
     */
    public String toString()
    {

        String result = "";
        //extend the for loop now prints from the top to the bottom
        for (int scan= top; scan >= 0; scan--)
             result = result + stack[scan] + "\n";
        return result;
    }
	
}

