/*
 * @CreateTime: Feb 10, 2018 3:56 PM
 * @Author: Gavin Jaeger-Freeborn
 * @Contact: gavinfre@uvic.ca
 * @Last Modified By: Gavin Jaeger-Freeborn
 * @Last Modified Time: Feb 10, 2018 5:25 PM
 * @Student Number:T00611983
 * @COMP 2231 Assignment 2 Question 3
 * @Description: An deque implementation of a deque in which you can insert or remove 
 * 				 from either end of the deque.
 */

/**
 * deque represents an array implementation of a deque in 
 * which the indexes for the front and rear of the deque can 
 * be added and removed.
 * 
 * @author Java Foundations
 * @version 4.0
 */
import java.util.*;
public class DoubleEndedQueue<T> implements DequeADT<T>
{
	private final static int DEFAULT_CAPACITY = 5;
	private int front, rear, count;
	private T[] deque; 

	/**
	 * Creates an empty deque using the specified capacity.
	 * @param initialCapacity the initial size of the circular array deque
	 */
	public DoubleEndedQueue(int initialCapacity)
	{
		front = rear = count = 0;
		
		deque = (T[]) (new Object[initialCapacity]);
	}

	/**
	 * Creates an empty deque using the default capacity.
	 */
	public DoubleEndedQueue()
	{
		this(DEFAULT_CAPACITY);
	}    

	/**
	 * Adds the specified element to the rear of this deque, expanding
	 * the capacity of the deque array if necessary.
	 * @param element the element to add to the rear of the deque
	 */
	public void enqueueToRear(T element)
	{
		if (size() == deque.length) 
			expandCapacity();

		/*if both the rear and front are the same and front has 
		already been added to place the element in the next avalable space*/
		if((rear == front) && !(deque[rear] == null))
		{
			rear++;
		}
		deque[rear] = element;
		rear = (rear + 1);

		count++;
	}
	/**
	 * Adds the specified element to the front of this deque, expanding
	 * the capacity of the deque array if necessary.
	 * @param element the element to add to the rear of the deque
	 */
	public void enqueueToFront(T element)
	{
		if (this.size() == deque.length - 1) 
			expandCapacity();
		//if this is the first element place it at 0 position in the array
		if(size() == 0)
		{
			deque[0] = element;
		}
		//if this is not the vary first element move all other elements to the right to make room
		else{
			T[] temp = Arrays.copyOf(deque, deque.length);
		for (int scan = front; scan <= rear; scan++) {
			deque[scan + 1] = temp[scan];
		}
		deque[front] = element;
		}
		count++;
	}

	/**
	 * Creates a new array to store the contents of this deque with
	 * twice the capacity of the old one.
	 */
	private void expandCapacity()
	{
		T[] larger = (T[]) (new Object[deque.length * 2]);

		for (int scan = 0; scan < count; scan++)
		{
			larger[scan] = deque[front];
			front = (front + 1) % deque.length;
		}

		front = 0;
		rear = count;
		deque = larger;
	}

	/**
	 * Removes the element at the front of this deque and returns a
	 * reference to it. 
	 * @return the element removed from the front of the deque
	 * @throws EmptyCollectionException  if the deque is empty
	 */
	public T dequeueFromFront() throws EmptyCollectionException
	{
		if (isEmpty())
			throw new EmptyCollectionException("deque");

		T result = deque[front];
		deque[front] = null;
		front = (front + 1);

		count--;

		return result;
	}

	/**
	 * Removes the element at the rear of this deque and returns a
	 * reference to it. 
	 * @return the element removed from the rear of the deque
	 * @throws EmptyCollectionException  if the deque is empty
	 */
	public T dequeueFromRear() throws EmptyCollectionException
	{
		if (isEmpty())
			throw new EmptyCollectionException("deque");

		T result = deque[rear];
		deque[rear] = null;
		rear = (rear - 1);
		count--;

		return result;
	}

	/** 
	 * Returns a reference to the element at the front of this deque.
	 * The element is not removed from the deque.  
	 * @return the first element in the deque
	 * @throws EmptyCollectionException if the deque is empty
	 */
	public T first() throws EmptyCollectionException
	{
		if (isEmpty())
			throw new EmptyCollectionException("deque");
		return deque[front];
	}

	public T end() throws EmptyCollectionException
	{
		if (isEmpty())
			throw new EmptyCollectionException("deque");
		return deque[rear];
	}

	/**
	 * Returns true if this deque is empty and false otherwise.
	 * @return true if this deque is empty 
	 */
	public boolean isEmpty()
	{
		return (count == 0);
	}

	/**
	 * Returns the number of elements currently in this deque.
	 * @return the size of the deque
	 */
	public int size()
	{
		return count;
	}

	/**
	 * Returns a string representation of this deque. 
	 * @return the string representation of the deque
	 */
	public String toString()
	{
		if (isEmpty())
			throw new EmptyCollectionException("deque");
		//add all the elements from the front of the deque to the end of the deque
		String result = "";
		for(int i = 0; (i <= rear) && (deque[i] != null); i++)
			result = result + deque[i] + " ";

		return result;  // temp
	}

}
