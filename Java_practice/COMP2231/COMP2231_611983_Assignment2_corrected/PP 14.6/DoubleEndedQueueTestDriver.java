/*
 * @CreateTime: Feb 12, 2018 12:51 PM
 * @Author: Gavin Jaeger-Freeborn
 * @Contact: gavinfre@uvic.ca
 * @Last Modified By: Gavin Jaeger-Freeborn
 * @Last Modified Time: Feb 13, 2018 3:51 PM
 * @Student Number:T00611983
 * @COMP 2231 Assignment 2 Question 3
 * @Description: DoubleEndedQueueTestDriver tests the DoubleEndedQueue class
 */
import java.util.*;
public class DoubleEndedQueueTestDriver
{
    public static void main(String[] args)
    {
        //create an empty stack
        DoubleEndedQueue<Integer> stack = new DoubleEndedQueue<Integer>();

        //fill the stack with the following numbers: 1, 15 3 100, and 2 in that order
        stack.enqueueToFront(new Integer(15));
        System.out.println("15 has been enqueued to the front of the queue");
        System.out.println("This is the queue now: " + stack.toString());
        stack.enqueueToRear(new Integer(1));
        System.out.println("1 has been enqueued to the rear of the queue");
        System.out.println("This is the queue now: " + stack.toString());
        stack.enqueueToRear(new Integer(3));
        System.out.println("3 has been enqueued to the rear of the queue");
        System.out.println("This is the queue now: " + stack.toString());
        stack.enqueueToRear(new Integer(2));
        System.out.println("2 has been enqueued to the rear of the queue");
        System.out.println("This is the queue now: " + stack.toString());
        stack.enqueueToFront(new Integer(100));
        System.out.println("100 has been enqueued to the front of the queue");
        System.out.println("This is the queue now: " + stack.toString());
        System.out.println();
        //print the first element of the queue 
        System.out.println("The first element of the queue is: " + stack.first());
        System.out.println();
        //print the end element of the queue 
        System.out.println("The end element of the queue is: " + stack.end());
        System.out.println();
        //print the que from first to last
        System.out.println("This is the queue now: " + stack.toString());
        System.out.println();
        //print the size of the queue
        System.out.println("The current size of the queue is: " + stack.size());
        System.out.println();
        //print if the queue is empty
        System.out.println("Is the stack empty?: " + stack.isEmpty());
        System.out.println();
        //enqueur and dequeue each element of the stack one at a time
        System.out.println(stack.dequeueFromFront() + " has been dequeued from the front of the queue");
        System.out.println();
        System.out.println(stack.dequeueFromRear() + " has been dequeued from the end of the queue");
        System.out.println();
        System.out.println(stack.dequeueFromFront() + " has been dequeued from the front of the queue");
        System.out.println();
        System.out.println(stack.dequeueFromRear() + " has been dequeued from the end of the queue");
        System.out.println();
        System.out.println(stack.dequeueFromFront() + " has been dequeued from the front of the queue");
        System.out.println();
        //print if the queue is empty
        System.out.println("Is the stack empty?: " + stack.isEmpty());
        System.out.println();
        //prove that the exeptions are found
        stack.dequeueFromRear();
    
    
    }
}