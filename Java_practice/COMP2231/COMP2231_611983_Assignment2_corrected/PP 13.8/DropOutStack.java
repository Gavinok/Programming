/*
 * @CreateTime: Feb 10, 2018 3:56 PM
 * @Author: Gavin Jaeger-Freeborn
 * @Contact: gavinfre@uvic.ca
 * @Last Modified By: Gavin Jaeger-Freeborn
 * @Last Modified Time: Feb 10, 2018 5:25 PM
 * @Student Number:T00611983
 * @COMP 2231 Assignment 2 Question 2
 * @Description: An implementation of a stack in which the last node
 *               of the stack is removed once a limited size is reached.
 */

import java.util.Iterator;

/**
 * Represents a linked implementation of a stack.
 *
 * @author Java Foundations
 * @version 4.0
 * NOTE: This version contains the implementation of several
 * methods left as a programming exercise.
 */
public class DropOutStack<T> implements StackADT<T>
{
    //used as the default SizeLimit
    private final static int DEFAULT_CAPACITY = 3;
    private int count;
    private LinearNode<T> top;

    /*once the number of nodes reaches this limit the last 
    node of the stack will be removed*/
    private final int SizeLimit;

    /**
    * Creates an empty stack with the limit being the DEFAULT_CAPACITY
    */
    public DropOutStack()
    {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Creates an empty stack.
     * @param Limit limits the size of the drop-out stack 
     */
    public DropOutStack(int Limit)
    {
        SizeLimit = Limit;
        count = 0;
        top = null;
    }

    /**
     * Adds the specified element to the top of this stack. If the number of nodes is equil 
     * to the limit the last node will be removed.
     * @param element element to be pushed on stack
     */
    public void push(T element)
    {
        LinearNode<T> temp = new LinearNode<T>(element);

        temp.setNext(top);
        top = temp;
        if(count == SizeLimit)
        {            
            for(int i = 0; i < count; i++)
            {
                temp = temp.getNext();
            }
            System.out.println("\n" + temp.getElement() + " has been dropped from the stack");
            temp.setNext(null);
            System.out.println();
            count--;
        }
        count++;
    }

    /**
     * Removes the element at the top of this stack and returns a
     * reference to it.
     * @return element from top of stack
     * @throws EmptyCollectionException if the stack is empty
     */
    public T pop() throws EmptyCollectionException
    {
        if (isEmpty())
            throw new EmptyCollectionException("stack");

        T result = top.getElement();
        top = top.getNext();
        count--;

        return result;
    }

    /**
     * Returns a reference to the element at the top of this stack.
     * The element is not removed from the stack.
     * @return element on top of stack
     * @throws EmptyCollectionException if the stack is empty
     */
    public T peek() throws EmptyCollectionException
    {
        if (isEmpty())
            throw new EmptyCollectionException("stack");

        return top.getElement();
    }

    /**
     * Returns true if this stack is empty and false otherwise.
     * @return true if stack is empty
     */
    public boolean isEmpty()
    {
        return (count == 0);
    }

    /**
     * Returns the number of elements in this stack.
     * @return number of elements in the stack
     */
    public int size()
    {
        return count;
    }

    /**
     * Returns a string representation of this stack.
     * @return string representation of the stack
     */
    public String toString()
    {
        String result = "\n";
        LinearNode current = top;

        while (current != null)
        {
            result = result + current.getElement() + "\n";
            current = current.getNext();
        }

        return result;
    }
}
