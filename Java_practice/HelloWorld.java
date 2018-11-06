import java.util.Scanner;
import java.text.DecimalFormat;
public class HelloWorld {

   
    public static void main (String[] args) 
    {
        int fly[] = {1, 2, 3};
        for(int tree :  fly){
            System.out.printf("%d \n", tree);
           // System.out.print(fly[0]);
        }
        System.out.printf("%d", test());

    }
    protected static int test()
    {
        return 2;
    }
}