/*
 * @CreateTime: Aug 12, 2018 5:38 PM
 * @Author: Gavin Jaeger-Freeborn
 * @Contact: gavinfre@uvic.ca
 * @Last Modified By: Gavin Jaeger-Freeborn
 * @Last Modified Time: Aug 12, 2018 5:42 PM
 * @Student Number:T00611983
 * @COMP 2231 Assignment 5 Question 1
 * @Description: Here I implement the heapsort class using an array implementation making it more 
 * 				 efficient since we donot need to place the array into an actual Heap.
 */

/**
 * HeapSort sorts a given array of Comparable objects using a heap.
 * 
 * @author Java Foundations
 * @version 4.0
 */

public class HeapSort<T>
{
	/**
	 * Sorts the specified array using a Heap
	 *
	 * @param data the data to be added to the heapsort
	 */
	/**
	 * sorts the inserted array using heapSort without actualy using an ADT.
	 */
	public static <T extends Comparable<T>> T[] heapSort(T[] array) {	
			int parent = (array.length - 2) / 2;
			//sort the heap
			for (int i = parent; i >= 0; i--) {
				heapify(array, array.length, i);
			}
			/*
			 * repeatedly shrink the size of the array, then swap the current index
			 * with the start of the array, then re arange the array with the reduced size.
			 */
			for (int i = array.length - 1; i > 0; i--) {
				//swap to the front of the array
				T tmp = array[0];
				array[0] = array[i];
				array[i] = tmp;
				//rearange the shrunken array
				heapify(array, i, 0);
			}
	
			return array;
		}
	/* 
	 * makes sure that the heep is still organized properly will remaining as an array 
	*/
	private static <T extends Comparable<T>> void heapify(T[] array, int heapSize, int Entry) {
		
		int length;
		Boolean HeapIncomplete = true;
		//determin the largest value untill we reach the root indicating that we have properly organized the array
		if(array.length < heapSize)
		{
			length = array.length - 1;
		}else{
			length = heapSize - 1;
		}
		while (HeapIncomplete == true) {
			int max = Entry;
			int LChild = (2*Entry) + 1;
			int rChild = (2*Entry) + 2;
			//if the left child is misplaced set it as the max index
			if (LChild <= length && array[max].compareTo(array[LChild]) < 0) 
			{
				max = LChild;
			}			
			////if the right child is misplaced set it as the max index
			if (rChild <= length && array[max].compareTo(array[rChild]) < 0)
			{
				max = rChild;
			}
			//if changes where made
			if (Entry != max) {
				//swap the placement of the maximum found and the originally entered value
				T tmp = array[Entry];
				array[Entry] = array[max];
				array[max] = tmp;
				Entry = max;
			} else {
				// if no changes had to be made we are done rearanging.
				// the root now contains the max value aka the heap is organized properly
				HeapIncomplete = false;
			}
		}
	}
}