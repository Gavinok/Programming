/*
 * @CreateTime: Feb 12, 2018 12:51 PM
 * @Author: Gavin Jaeger-Freeborn
 * @Contact: gavinfre@uvic.ca
 * @Last Modified By: Gavin Jaeger-Freeborn
 * @Last Modified Time: Feb 12, 2018 4:05 PM
 * @Student Number:T00611983
 * @COMP 2231 Assignment 3 Question 3
 * @Description: QueueUsingLinkedList implemments a Queue using a LinkedList.
 */
import java.util.LinkedList;
/**
 *
 * @author Gavin Jaeger-Freeborn
 * @version 1.0
 */
public class QueueUsingLinkedList<T> {
    private LinkedList<T> Queue;

    public QueueUsingLinkedList()
    {
        Queue = new LinkedList<T>();
    }
    /**  
	 * Adds the specified element to the end of this Queue. 
	 * @param element element to be added onto theend of the Queue
	 */
    public void enqueue(T element)
    {
        Queue.add(element);
    }
    /**  
	 * Removes and returns the top element from this Queue. 
	 * @return the element removed from the Queue
	 */
    public T dequeue() throws EmptyCollectionException
    {

        if (isEmpty())
            throw new EmptyCollectionException("Queue");
            
        return Queue.remove(0);
    }
    /**  
	 * Returns without removing the first element of this Queue. 
	 * @return the first element of the Queue
	 */
    public T front() throws EmptyCollectionException
    {
        if (isEmpty())
            throw new EmptyCollectionException("Queue");   
        
        return Queue.get(0);
    }
    /**  
	 * Returns true if this Queue contains no elements. 
	 * @return true if the Queue is empty
	 */
    public boolean isEmpty()
    {
        return (Queue.size() == 0);
    }
    /** 
	 * Returns the number of elements in this Queue. 
	 * @return the number of elements in the Queue
	 */
    public int size()
    {
        return Queue.size();
    }
    /**  
	 * Returns a string representation of this Queue. 
	 * @return a string representation of the Queue
	 */
    public String toString()
    {
        String QueueString = "";
        for(int scan = (Queue.size() - 1); scan >= 0; scan--) {
            QueueString = QueueString  + Queue.get(scan) + ", ";
        }
        return QueueString;
    }
    public static void main(String[] args)
    {
        QueueUsingLinkedList<Integer> queue = new QueueUsingLinkedList<Integer>();
        queue.enqueue(new Integer(1));
        queue.enqueue(new Integer(15));
        queue.enqueue(new Integer(3));
        queue.enqueue(new Integer(100));
        queue.enqueue(new Integer(2));
        System.out.println(queue.dequeue());
        System.out.println();
        System.out.println(queue.front());
        System.out.println();
        System.out.println(queue.size());
        System.out.println();
        System.out.println(queue.isEmpty());
        System.out.println();
        System.out.println(queue.toString());
        System.out.println(queue.dequeue());
        System.out.println();
        System.out.println(queue.dequeue());
        System.out.println();
        System.out.println(queue.dequeue());
        System.out.println();
        System.out.println(queue.dequeue());
        System.out.println();
        System.out.println(queue.dequeue());

    }
}