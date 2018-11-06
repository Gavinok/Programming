/*
 * @CreateTime: Jan 20, 2018 2:10 PM
 * @Author: Gavin Jaeger-Freeborn
 * @Contact: gavinfre@uvic.ca
 * @Last Modified By: Gavin Jaeger-Freeborn
 * @Last Modified Time: Jan 20, 2018 9:01 PM
 * @Student Number:T00611983
 * @ COMP 2231 Assignment 1 Question 2
 * @Description: Numbers is simply an object that contains a number that 
 *               can be compared to another Numbers object.
 */

public class Numbers implements Comparable<Numbers>
{
   private int Number;

   //-----------------------------------------------------------------
   //  Sets up this contact with the specified information.
   //-----------------------------------------------------------------
   public Numbers (int newNumber)
   {
      Number = newNumber;
      
   }

   public Numbers ()
   {
      
   }

   public void setNumber(int newNumber)
   {
       Number = newNumber;
   }

   //-----------------------------------------------------------------
   //  Returns a string representation of this contact.
   //-----------------------------------------------------------------
   public String toString ()
   {
      return Integer.toString(Number);
   }

   //-----------------------------------------------------------------
   //  Uses both last and first names to determine lexical ordering.
   //-----------------------------------------------------------------
   public int compareTo (Numbers other)
   {
        int result;
        if(Number == other.Number)
            return 0;
        if(Number > other.Number)
            return 1;
        else
            return -1;
   }
}
