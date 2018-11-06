/*
 * @CreateTime: Jan 19, 2018 2:10 PM
 * @Author: Gavin Jaeger-Freeborn
 * @Contact: gavinfre@uvic.ca
 * @Last Modified By: Gavin Jaeger-Freeborn
 * @Last Modified Time: Jan 20, 2018 9:01 PM
 * @Student Number:T00611983
 * @ COMP 2231 Assignment 1 Question 2
 * @Description: Sorting2 is simply an adapted version of the original sorting class 
 * 				 that adds a timer to each of the classes and a counter for each comparison.
 */

/**
 * Sorting demonstrates sorting and searching on an array 
 * of objects.
 *
 * @author Java Foundations
 * @version 4.0 
 */
public class Sorting2 
{
	/**
	 * Sorts the specified array of integers using the selection
	 * sort algorithm.
	 *
	 * @param data the array to be sorted
	 */
	private static int TotalNumberofCompairisons;
	private static long StartTime;
	private static long TotalExicutionTime; 

	public static int getTotalNumberofCompairisons()
	{
		return TotalNumberofCompairisons;
	}
	public long getTotalExicutionTime()
	{
		return TotalExicutionTime;
	}

	public  <T extends Comparable<T>> 
	void selectionSort(T[] data)
	{
		TotalNumberofCompairisons = 0;
		StartTime = System.nanoTime();
		int min;
		T temp;

		for (int index = 0; index < data.length - 1; index++)
		{
			min = index;
			for (int scan = index + 1; scan < data.length; scan++)
			{
				TotalNumberofCompairisons++;
				if (data[scan].compareTo(data[min]) < 0)
					min = scan;
			}
		
			swap(data, min, index);
		}
		TotalExicutionTime = System.nanoTime() - StartTime;
	}

	/**
	 * Swaps to elements in an array. Used by various sorting algorithms.
	 * 
	 * @param data   the array in which the elements are swapped
	 * @param index1 the index of the first element to be swapped
	 * @param index2 the index of the second element to be swapped
	 */
	private static <T extends Comparable<T>> 
	void swap(T[] data, int index1, int index2)
	{
		T temp = data[index1];
		data[index1] = data[index2];
		data[index2] = temp;
	}

	/**
	 * Sorts the specified array of objects using an insertion
	 * sort algorithm.
	 *
	 * @param data the array to be sorted
	 */
	public static <T extends Comparable<T>> 
	void insertionSort(T[] data)
	{
		TotalNumberofCompairisons = 0;
		StartTime = System.nanoTime();
		
		for (int index = 1; index < data.length; index++)
		{
			T key = data[index];
			int position = index;
			TotalNumberofCompairisons++;
			// shift larger values to the right 
			while (position > 0 && data[position-1].compareTo(key) > 0)
			{
				data[position] = data[position - 1];
				position--;
				System.out.println(TotalNumberofCompairisons);
			}

			data[position] = key;
		}
		TotalExicutionTime = System.nanoTime() - StartTime;
	}

	/**
	 * Sorts the specified array of objects using a bubble sort
	 * algorithm.
	 *
	 * @param data the array to be sorted
	 */
	public static <T extends Comparable<T>> 
	void bubbleSort(T[] data)
	{
		TotalNumberofCompairisons = 0;
		StartTime = System.nanoTime();

		int position, scan;

		for (position =  data.length - 1; position >= 0; position--)
		{
			for (scan = 0; scan <= position - 1; scan++)
			{
				TotalNumberofCompairisons++;
				if (data[scan].compareTo(data[scan + 1]) > 0)
					swap(data, scan, scan + 1);
			}
		}
		TotalExicutionTime = System.nanoTime() - StartTime;
	}

	/**
	 * Sorts the specified array of objects using the quick sort algorithm.
	 * 
	 * @param data the array to be sorted
	 */
	public static <T extends Comparable<T>> 
	void quickSort(T[] data)
	{
		quickSort(data, 0, data.length - 1, true);
	}

	/**
	 * Recursively sorts a range of objects in the specified array using the
	 * quick sort algorithm. 
	 * 
	 * @param data the array to be sorted
	 * @param min  the minimum index in the range to be sorted
	 * @param max  the maximum index in the range to be sorted
	 */
	private static <T extends Comparable<T>> 
	void quickSort(T[] data, int min, int max, boolean firstPass)
	{
		if(firstPass){
			TotalNumberofCompairisons = 0;
			StartTime = System.nanoTime();
		}
		TotalNumberofCompairisons++;

		if (min < max)
		{
			// create partitions
			int indexofpartition = partition(data, min, max);

			// sort the left partition (lower values)
			quickSort(data, min, indexofpartition - 1, false);

			// sort the right partition (higher values)
			quickSort(data, indexofpartition + 1, max, false);
		}
		TotalExicutionTime = System.nanoTime() - StartTime;
	}

	/**
	 * Used by the quick sort algorithm to find the partition.
	 * 
	 * @param data the array to be sorted
	 * @param min  the minimum index in the range to be sorted
	 * @param max  the maximum index in the range to be sorted
	 */
	private static <T extends Comparable<T>> 
	int partition(T[] data, int min, int max)
	{
		T partitionelement;
		int left, right;
		int middle = (min + max) / 2;

		// use the middle data value as the partition element
		partitionelement = data[middle];
		
		// move it out of the way for now
		swap(data, middle, min);

		left = min;
		right = max;

		while (left < right)
		{
			TotalNumberofCompairisons++;
			// search for an element that is > the partition element
			while (left < right && data[left].compareTo(partitionelement) <= 0)
				left++;
			
			TotalNumberofCompairisons++;
			// search for an element that is < the partition element
			while (data[right].compareTo(partitionelement) > 0)
				right--;
			
			// swap the elements
			if (left < right)
				swap(data, left, right);
		}

		// move the partition element into place
		swap(data, min, right);

		return right;
	}
	
	/**
	 * Sorts the specified array of objects using the merge sort
	 * algorithm.
	 *
	 * @param data the array to be sorted
	 */
	public static <T extends Comparable<T>>
	void mergeSort(T[] data)
	{
		mergeSort(data, 0, data.length - 1, true);
	}

	/**
	 * Recursively sorts a range of objects in the specified array using the
	 * merge sort algorithm.
	 *
	 * @param data the array to be sorted
	 * @param min  the index of the first element 
	 * @param max  the index of the last element
	 */
	private static <T extends Comparable<T>>
	void mergeSort(T[] data, int min, int max, boolean firstPass)
	{
		if(firstPass){
			TotalNumberofCompairisons = 0;
			StartTime = System.nanoTime();
		}
		if (min < max)
		{
			int mid = (min + max) / 2;
			mergeSort(data, min, mid, false);
			mergeSort(data, mid+1, max, false);
			merge(data, min, mid, max);
		}
		TotalExicutionTime = System.nanoTime() - StartTime;
	}

	/**
	 * Merges two sorted sub arrays of the specified array.
	 *
	 * @param data the array to be sorted
	 * @param first the beginning index of the first sub-array 
	 * @param mid the ending index for the first sub-array
	 * @param last the ending index of the second sub-array
	 */
	@SuppressWarnings("unchecked")
	private static <T extends Comparable<T>>
	void merge(T[] data, int first, int mid, int last)
	{
		T[] temp = (T[])(new Comparable[data.length]);

		int first1 = first, last1 = mid;  // endpoints of first sub-array
		int first2 = mid + 1, last2 = last;  // endpoints of second sub-array
		int index = first1;  // next index open in temp array

		//  Copy smaller item from each sub-array into temp until one
		//  of the sub-arrays is exhausted
		while (first1 <= last1 && first2 <= last2)
		{
			TotalNumberofCompairisons++;
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

		//  Copy remaining elements from first sub-array, if any
		while (first1 <= last1)
		{
			temp[index] = data[first1];
			first1++;
			index++;
		}

		//  Copy remaining elements from second sub-array, if any
		while (first2 <= last2)
		{
			temp[index] = data[first2];
			first2++;
			index++;
		}

		//  Copy merged data into original array
		for (index = first; index <= last; index++)
			data[index] = temp[index];
	}
	
}

