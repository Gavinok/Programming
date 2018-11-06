public class Pracy {
    // generic method printArray
    public static < E > void printArray( E[] inputArray ) {
       // Display array elements
       for(E element : inputArray) {
          System.out.printf("%s ", element);
       }
       System.out.println();
    }
    public static void main(String args[]) {
        int[] data = {90,   8,  7,   56, 123, 235, 9, 1, 653};
        Sorting.quickSort(data, 0, 8);
    }
 }