/*
 * @CreateTime: Jan 19, 2018 2:10 PM
 * @Author: Gavin Jaeger-Freeborn
 * @Contact: gavinfre@uvic.ca
 * @Last Modified By: Gavin Jaeger-Freeborn
 * @Last Modified Time: Jan 20, 2018 9:01 PM
 * @Student Number:T00611983
 * @ COMP 2231 Assignment 1 Question 1
 * @Description: ShellArrayDriver creats and sorts several arrays
 */
import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;
public class ShellArrayDriver
{
   //main acts as the driver 
   public static void main (String[] args) 
   {
       //create and print a set of ShellArrays where each value is the size of the array eg. size = 0, 1, 2, 3
       ShellArray TestingArray = new ShellArray(0);
       System.out.println("Array before being sorted" + TestingArray.toString());
       TestingArray.ShellSort();
       System.out.println("Array after being sorted" + TestingArray.toString());
       System.out.println();
       TestingArray = new ShellArray(1);
       System.out.println("Array before being sorted" + TestingArray.toString());
       TestingArray.ShellSort();
       System.out.println("Array after being sorted" + TestingArray.toString());
       System.out.println();
       TestingArray = new ShellArray(2);
       System.out.println("Array before being sorted" + TestingArray.toString());
       TestingArray.ShellSort();
       System.out.println("Array after being sorted" + TestingArray.toString());
       System.out.println();
       TestingArray = new ShellArray(3);
       System.out.println("Array before being sorted" + TestingArray.toString());
       TestingArray.ShellSort();
       System.out.println("Array after being sorted" + TestingArray.toString());
       System.out.println();
   }
}