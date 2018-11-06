import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
public class ScrabbleWord {
    public ScrabbleWord(String newword){
        this.setword(newword);
    }
    public ScrabbleWord(){
    }
    public static void main (String[] args) 
    {
        ScrabbleWord tree = new ScrabbleWord("stupid");
        //tree.setword("stupid");
        tree.getword();
        tree.printword();
        System.out.println(tree.getscore());
        System.out.println(tree.getletterat(1));
        System.out.println(tree.getscoreat(1));
    }
    //returns the characture at a specified index which can be anything
    // from 0 to the length of the given word
    public char getletterat(int IndexOfLetter)
    {
        return letterlist.get(IndexOfLetter).getletter();
    }
    public int getscoreat(int IndexOfLetter)
    {
        return letterlist.get(IndexOfLetter).getscore();
    }
    //setword() is used to set up a chosen word as a list of letters
     public void setword(String inputstring)
    {
        for(int i = 0; i < inputstring.length(); i++)
        {
            letterlist.add(new Scrabbleletter(inputstring.charAt(i)));
        }
        setscore();
    }
    //getword() gets the letterlist and returns it as a string
    public String getword()
    {
       String tempword = new String("");
       Iterator<Scrabbleletter> worditerator = letterlist.iterator();
       while(worditerator.hasNext())
           tempword =  tempword + (worditerator.next().getletter());
        
        return tempword;
    }
   
    //printword() prints the list of scrabble letter objects to the console
    public void printword(){
        System.out.println(getword());
    }

    //getscore() returns the score associated to the current letterlist
    public int getscore()
    {        
        return score;
    }

    //setscore() uses the current letterlist to set the score based off of each letter
    private void setscore()
    {
        Iterator<Scrabbleletter> worditerator = letterlist.iterator();
        while(worditerator.hasNext())
        {            
            score =  score + (worditerator.next().getscore());
        }
    }

    //score is simply the score associated with the letterlist (aka the word in scrabble)
    private int score = 0;

    //letterlist is an arraylist of ScrabbleLetters that acts as a word is scrabble
    private ArrayList<Scrabbleletter> letterlist = new ArrayList<Scrabbleletter>();
}