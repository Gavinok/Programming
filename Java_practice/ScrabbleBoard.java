import java.util.Scanner;
public class ScrabbleBoard {

    public static void main (String[] args) 
    {
        ScrabbleBoard Mainboard = new ScrabbleBoard();
        System.out.println( Mainboard.setTiles("cat", 0, 0, 3, 0 ));

    }
    //checks the multiplier at the points x,y and returns the score receved at that point
    private int checkMultiplier(String word, int index, int x, int y, boolean TotalisZero)
    {
        ScrabbleWord multiplyScrabbleWord = new ScrabbleWord(word);
        if((x < 0 )|| (y < 0))
        {
            return -1000;
        }
        if(TotalisZero){
            return multiplyScrabbleWord.getscore();
        }
        switch(board[y][x])
        {
            case 1:
            //2 times the letter score
            return 2*multiplyScrabbleWord.getscoreat(index);
            case 2:
            //3 times the letter score
            return 3*multiplyScrabbleWord.getscoreat(index);
            case 3:
            //2 times the word score
            return 2*multiplyScrabbleWord.getscore();
            case 4:
            //3 times the word score
            return 3*multiplyScrabbleWord.getscore();
            case 0:
            return 0;
        }
        return -1000;
    }
    
    public int setTiles(String word, int firstx, int firsty, int finalx, int finaly)
    {
       int score = 0;
        //checks to see if the the inputs are valid
       int lengthSquard = (((finalx - firstx)*(finalx - firstx)) 
                             + ((finaly - (firsty)*(finaly - firsty))));
        System.out.println(word.length()*word.length());
        System.out.println(lengthSquard);
       if(lengthSquard != (word.length()*word.length()))
       {
           System.out.print("invalid input to setTiles");
           return 0;
       }
       //if the input is valid
       else
       {
            setFirstx(firstx);
            setFirsty(firsty);
            setLastx(finalx);
            setLasty(finaly);

                //moves in x direction                
                System.out.println(getFirstx());
                System.out.println(getLastx());
                for(int i = 0; i < getLastx()-getFirstx(); i++)
                {
                    System.out.println(getFirstx()  + i);
                    System.out.println(getFirsty());
                    score = score +  checkMultiplier(word, i, (getFirstx()  + i),getFirsty(), false);
                }
                //moves in y direction
                System.out.println(getFirsty());
                System.out.println(getLasty());
                for(int i = 0; i < getLasty()-getFirsty(); i++)
                {
                    System.out.println(checkMultiplier(word, i, getFirstx(), (getFirsty() - i), false));
                    score = score + checkMultiplier(word, i, getFirstx(), (getFirsty() - i), false);
                }
                if(score == 0)
                    score = score + checkMultiplier(word, 0, 0, 0, false);

        }
        return score;
    }
    private int getFirstx(){
        return Firstxvalue;
    }
    private void setFirstx(int x){
        Firstxvalue = x;
    }
    private int getFirsty(){
        return Firstyvalue;
    }
    private void setFirsty(int y){
        Firstyvalue = y;
    }
    private int getLastx(){
        return Lastxvalue;
    }
    private void setLastx(int x){
        Lastxvalue = x;
    }
    private int getLasty(){
        return Lastyvalue;
    }
    private void setLasty(int y){
        Lastyvalue = y;
    }
    private int Firstxvalue;
    private int Firstyvalue;
    private int Lastxvalue;
    private int Lastyvalue;
    private final int[][] board = {{4, 0, 0, 1, 0, 0, 0, 4, 0, 0, 0, 1, 0, 0, 4},
                                    {0, 3, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 3, 0},
                                    {0, 0, 3, 0, 0, 0, 1, 0, 1, 0, 0, 0, 3, 0, 0},
                                    {1, 0, 0, 3, 0, 0, 0, 1, 0, 0, 0, 3, 0, 0, 1},
                                    {0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0},
                                    {0, 2, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 2, 0},
                                    {0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0},
                                    {4, 0, 0, 1, 0, 0, 0, 3, 0, 0, 0, 1, 0, 0, 4},
                                    {0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0},
                                    {0, 2, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 2, 0},
                                    {0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0},
                                    {1, 0, 0, 3, 0, 0, 0, 1, 0, 0, 0, 3, 0, 0, 1},
                                    {0, 0, 3, 0, 0, 0, 1, 0, 1, 0, 0, 0, 3, 0, 0},
                                    {0, 3, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 3, 0},
                                    {4, 0, 0, 1, 0, 0, 0, 4, 0, 0, 0, 1, 0, 0, 4} };    
        }

