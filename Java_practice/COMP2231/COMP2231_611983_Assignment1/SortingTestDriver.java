/*
 * @CreateTime: Jan 20, 2018 2:10 PM
 * @Author: Gavin Jaeger-Freeborn
 * @Contact: gavinfre@uvic.ca
 * @Last Modified By: Gavin Jaeger-Freeborn
 * @Last Modified Time: Jan 20, 2018 9:01 PM
 * @Student Number:T00611983
 * @ COMP 2231 Assignment 1 Question 2
 * @Description: SortingTestDriver is simply a class meant to drive the Sorting2 class.
 */
import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;

public class SortingTestDriver
{
    //the 5 different array sizes to be tested
    private static Numbers[] array1 = new Numbers[5];
    private static Numbers[] array2 = new Numbers[20];
    private static Numbers[] array3 = new Numbers[100];
    private static Numbers[] array4 = new Numbers[1000];
    //create an array for each of the sorting methods
    private static Numbers[] RandomizeArrays(Numbers[] array)
    {
        Random randomizer = new Random();
        int temp; //= //new Numbers();
        for(int i = 0; i < array.length; i++)
         {
             temp = randomizer.nextInt();
             array[i] = new Numbers(temp);
            // System.out.println(temp);
         }
         return array;

    }

    //uses the 5 sorting antilogarithms on the provided array
    private static void SortThisArray(Numbers[] array)
    {
        Numbers[] arraySelect = new Numbers[array.length];
        Numbers[] arrayInsert = new Numbers[array.length];
        Numbers[] arrayBubble = new Numbers[array.length];
        Numbers[] arrayQuick = new Numbers[array.length];
        Numbers[] arrayMerge = new Numbers[array.length];

        arraySelect = Arrays.copyOf(array);
        arrayInsert = Arrays.copyOf(array);
        arrayBubble = Arrays.copyOf(array);
        arrayQuick = Arrays.copyOf(array);
        arrayMerge = Arrays.copyOf(array);

        //print the arrays before sorting
      //  System.out.println(Arrays.toString(array));
 
        Sorting2 sorter = new Sorting2();
        //selectionSort
        sorter.selectionSort(arraySelect);
        System.out.println("selectionSort:");
        System.out.println("the total number of comparisons is " + sorter.getTotalNumberofCompairisons() + " and " + "the total execution time is " + sorter.getTotalExicutionTime());
      // System.out.println(Arrays.toString(arraySelect));

        //insertionSort
        System.out.println("insertionSort:");
        sorter.selectionSort(arrayInsert);
        System.out.println("the total number of comparisons is " + sorter.getTotalNumberofCompairisons() + " and " + "the total execution time is " + sorter.getTotalExicutionTime());
      //  System.out.println(Arrays.toString(arrayInsert));

        //bubbleSort
        System.out.println("bubbleSort:");
        sorter.selectionSort(arrayBubble);
        System.out.println("the total number of comparisons is " + sorter.getTotalNumberofCompairisons() + " and " + "the total execution time is " + sorter.getTotalExicutionTime());
      //  System.out.println(Arrays.toString(arrayBubble));

        //quickSort
        System.out.println("quickSort:");
        sorter.selectionSort(arrayQuick);
        System.out.println("the total number of comparisons is " + sorter.getTotalNumberofCompairisons() + " and " + "the total execution time is " + sorter.getTotalExicutionTime());
      //  System.out.println(Arrays.toString(arrayQuick));

        //mergeSort
        System.out.println("mergeSort:");
        sorter.selectionSort(arrayMerge);
        System.out.println("the total number of comparisons is " + sorter.getTotalNumberofCompairisons() + " and " + "the total execution time is " + sorter.getTotalExicutionTime());
       // System.out.println(Arrays.toString(arrayMerge));
    }

    //main tests each of Sorting2's methods on the different array sizes
    public static void main(String[] args)
    { 
       
        //first random sort
        SortThisArray(RandomizeArrays(array1));

        //2nd random sort
        SortThisArray(RandomizeArrays(array2));

        //presorted test
        SortThisArray(array2);

        //3rd random sort
        SortThisArray(RandomizeArrays(array3));

        //4th random sort
        SortThisArray(RandomizeArrays(array4));
    }
}