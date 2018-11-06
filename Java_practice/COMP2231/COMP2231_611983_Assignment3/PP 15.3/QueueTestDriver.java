/*
 * @CreateTime: Feb 12, 2018 12:51 PM
 * @Author: Gavin Jaeger-Freeborn
 * @Contact: gavinfre@uvic.ca
 * @Last Modified By: Gavin Jaeger-Freeborn
 * @Last Modified Time: Feb 13, 2018 4:45 PM
 * @Student Number:T00611983
 * @COMP 2231 Assignment 3 Question 3
 * @Description: QueueTestDriver tests the following methosd in the QueueUsingLinkedList class:
 * 1. enqueue()
 * 2. front()
 * 3. toString()
 * 4. size()
 * 5. isEmpty()
 * 6. dequeue()
 * finally the driver tests to see if the exceptions can be identified.
 */
import java.util.*;
public class QueueTestDriver
{
    public static void main(String[] args)
    {
        //create an empty queue
        QueueUsingLinkedList<Integer> queue = new QueueUsingLinkedList<Integer>();

        //fill the que with the following numbers: 1, 15 3 100, and 2 in that order
        queue.enqueue(new Integer(1));
        System.out.println("1 has been enqueue");
        queue.enqueue(new Integer(15));
        System.out.println("15 has been enqueue");
        queue.enqueue(new Integer(3));
        System.out.println("3 has been enqueue");
        queue.enqueue(new Integer(100));
        System.out.println("100 has been enqueue");
        queue.enqueue(new Integer(2));
        System.out.println("2 has been enqueue");
        System.out.println();

        //print the front of the queue 
        System.out.println("The front element of the queue is: " + queue.front());
        System.out.println();
        //print the queue
        System.out.println("this is the queue now: " + queue.toString());
        System.out.println();
        //print the size of the queue
        System.out.println("the current size of the queue is: " + queue.size());
        System.out.println();
        //print wither or not it is empty
        System.out.println("is the queue empty?: " + queue.isEmpty());
        System.out.println();
        //remove and print each element of the queue one at a time
        System.out.println(queue.dequeue() + " has been dequeue");
        System.out.println();
        System.out.println(queue.dequeue() + " has been dequeue");
        System.out.println();
        System.out.println(queue.dequeue() + " has been dequeue");
        System.out.println();
        System.out.println(queue.dequeue() + " has been dequeue");
        System.out.println();
        System.out.println(queue.dequeue() + " has been dequeue");
        System.out.println();
        //prove that the exeptions are found
        
        queue.dequeue();
    
    }
}