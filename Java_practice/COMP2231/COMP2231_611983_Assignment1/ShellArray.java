/*
 * @CreateTime: Jan 19, 2018 2:08 PM
 * @Author: Gavin Jaeger-Freeborn
 * @Contact: gavinfre@uvic.ca
 * @Last Modified By: Gavin Jaeger-Freeborn
 * @Last Modified Time: Jan 20, 2018 8:56 PM
 * @Student Number:T00611983
 * @ COMP 2231 Assignment 1 Question 1
 * @Description: ShellArray creats and populats an array of
 *               random integers based on the size supplied 
 *               to the constructor. 
 */
import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;
public class ShellArray {
  
    private int[] array;
    //this constructor generates an array filled with randome values based on a predetermined size
    public ShellArray(int sizeofarray)
    {
        array = new int[sizeofarray];
        Random randvalue = new Random();
        for(int index = 0; index < sizeofarray; index++)
        {
           array[index] = randvalue.nextInt();
        }
    }
    public int[] getArray()
    {
        return array;
    }

    // The ShellSort methed uses the Sell sorting algarithem to sorting an array in accending order.
    public int[] ShellSort () 
    {
        //establish the initial gap between elements of the array
        int gap = (array.length/2);
        
        //System.out.println(array.length);
        while(gap >= 1)
        {
            boolean swapflag = true;
            //if the array remains unchanged after a full pass it will halve the gap
            while(swapflag == true)
            {
                swapflag = false;
                //swap all of place elements across the gap
                for(int index = 0, temp; (index < array.length - gap); index++)
                {
                    //if the two elements of the array are out of place swap them 
                    if(array[index] > array[index + gap])
                    {

                        swapflag = true;
                        temp = array[index];
                        array[index] = array[index + gap];
                        array[index + gap] = temp;
                    }
                }
            }
            gap = gap/2;
        }   
        return array;
    } 

    public String toString()
    {
        return Arrays.toString(this.array);
    }
}

