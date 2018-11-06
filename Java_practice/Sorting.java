//********************************************************************
//  Sorting.java       Java Foundations
//
//  Contains various sort algorithms that operate on an array of
//  int objects.
//
//  Modified bubbleSort for PP 18.1 to introduce a swapflag
//  to halt the algorithm if no swaps occur --> list is in order
//********************************************************************

public class Sorting
{
      public static void printArray(int[] array) {
            for (int i = 0; i < array.length; i++) {
               System.out.print(array[i] + " ");
            }
            System.out.println();
         }
   //-----------------------------------------------------------------
   //  Sorts the specified array of integers using the selection
   //  sort algorithm.
   //-----------------------------------------------------------------
  /* public static void selectionSort (int[] data)
   {
      int min;

      for (int index = 0; index < data.length-1; index++)
      {
         min = index;
         for (int scan = index+1; scan < data.length; scan++)
            if (data[scan].compareTo(data[min]) < 0)
               min = scan;

         swap (data, min, index);
      }
   }*/

   //-----------------------------------------------------------------
   //  Swaps two elements in the specified array.
   //-----------------------------------------------------------------
   private static void swap (int[] data, int index1, int index2)
   {
       int temp = data[index1];
       data[index1] = data[index2];
       data[index2] = temp;
   }

   //-----------------------------------------------------------------
   //  Sorts the specified array of objects using an insertion
   //  sort algorithm.
   //-----------------------------------------------------------------
   public static void insertionSort (int[] data)
   {
      for (int index = 1; index < data.length; index++)
      {
         int key = data[index];
         int position = index;
         printArray(data);
         // Shift larger values to the right
         while (position > 0 && (data[position-1] > key ))
         {
            data[position] = data[position-1];
            position--;
         }
         
         data[position] = key;
      }
   }

   //-----------------------------------------------------------------
   //  Sorts the specified array of objects using a bubble sort
   //  algorithm.
   //
   //  Modified to have the swapflag
   //-----------------------------------------------------------------
  public static void bubbleSort (int[] data)
   {
      int position, scan;
      boolean swapflag = true;
      position = data.length - 1;

      while (swapflag)		// code mod using swap flag
      {
         swapflag = false;
         for (scan = 0; scan <= position - 1; scan++)
            if (data[scan].compareTo(data[scan+1]) > 0)
            {
            	swap (data, scan, scan+1);
            	swapflag = true;
            }
         position--;
         System.out.println("End of pass");
         for (int d : data)
            System.out.println (d);
      }
   }

   //-----------------------------------------------------------------
   //  Sorts the specified array of objects using the quick sort
   //  algorithm.
   //-----------------------------------------------------------------
   public static void quickSort (int[] data, int min, int max)
   {
      int pivot;

      if (min < max)
      {
            
         pivot = partition (data, min, max);  // make partitions
         quickSort(data, min, pivot-1);  // sort left partition
         quickSort(data, pivot+1, max);  // sort right partition
      }
      printArray(data);
   }

   //-----------------------------------------------------------------
   //  Creates thse partitions needed for quick sort.
   //-----------------------------------------------------------------
   private static int partition (int[] data, int min, int max)
   {
      // Use first element as the partition value
      int partitionValue = data[min];

      int left = min;
      int right = max;

      while (left < right)
      {
         // Search for an element that is > the partition element
         while ((data[left] > (partitionValue)) && left < right)
            left++;

         // Search for an element that is < the partitionelement
         while (data[right] < (partitionValue))
            right--;

         if (left < right)
            swap(data, left, right);
           // printArray(data);
      }

      // Move the partition element to its final position
      swap (data, min, right);
     
      return right;
   }

   //-----------------------------------------------------------------
   //  Sorts the specified array of objects using the merge sort
   //  algorithm.
   //-----------------------------------------------------------------
  /* public static void mergeSort (int[] data, int min, int max)
   {
      if (min < max)
      {
         int mid = (min + max) / 2;
         mergeSort (data, min, mid);
         mergeSort (data, mid+1, max);
         merge (data, min, mid, max);
      }
   }*/

   //-----------------------------------------------------------------
   //  Sorts the specified array of objects using the merge sort
   //  algorithm.
   //-----------------------------------------------------------------
  /* public static void merge (int[] data, int first, int mid,
      int last)
   {
      int[] temp = new int[data.length];

      int first1 = first, last1 = mid;  // endpoints of first subarray
      int first2 = mid+1, last2 = last;  // endpoints of second subarray
      int index = first1;  // next index open in temp array

      //  Copy smaller item from each subarray into temp until one
      //  of the subarrays is exhausted
      while (first1 <= last1 && first2 <= last2)
      {
         if (data[first1].compareTo(data[first2]) < 0)
         {
            temp[index] = data[first1];
            first1++;
         }
         else
         {
            temp[index] = data[first2];
            first2++;
         }
         index++;
      }

      //  Copy remaining elements from first subarray, if any
      while (first1 <= last1)
      {
         temp[index] = data[first1];
         first1++;
         index++;
      }

      //  Copy remaining elements from second subarray, if any
      while (first2 <= last2)
      {
         temp[index] = data[first2];
         first2++;
         index++;
      }

      //  Copy merged data into original array
      for (index = first; index <= last; index++)
         data[index] = temp[index];
   }*/
}
