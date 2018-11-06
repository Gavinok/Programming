import java.util.Scanner;
public class Scrabbleletter {
    public Scrabbleletter(char newletter){
        setletter(newletter);
    }
    public int getscore()
    {
        switch (letter)
        {
            case 'a':
            case 'A':
            return 1;
            case 'b':
            case 'B':
            return 3;
            case 'c':
            case 'C':
            return 3;
            case 'd':
            case 'D':
            return 2;
            case 'e':
            case 'E':
            return 1;
            case 'f':
            case 'F':
            return 4;
            case 'g':
            case 'G':
            return 2;
            case 'h':
            case 'H':
            return 4;
            case 'i':
            case 'I':
            return 1;
            case 'j':
            case 'J':
            return 8;
            case 'k':
            case 'K':
            return 5;
            case 'l':
            case 'L':
            return 1;
            case 'm':
            case 'M':
            return 3;
            case 'n':
            case 'N':
            return 1;
            case 'o':
            case 'O':
            return 1;
            case 'p':
            case 'P':
            return 3;
            case 'q':
            case 'Q':
            return 10;
            case 'r':
            case 'R':
            return 1;
            case 's':
            case 'S':
            return 1;
            case 't':
            case 'T':
            return 1;
            case 'u':
            case 'U':
            return 1;
            case 'v':
            case 'V':
            return 4;
            case 'w':
            case 'W':
            return 4;
            case 'x':
            case 'X':
            return 8;
            case 'y':
            case 'Y':
            return 4;
            case 'z':
            case 'Z':
            return 10;
            case ' ':
            return 0;
            default:
            System.out.print("invalit letter entry");
            return 11;
            }
    }
    public void setletter(char newletter)
    {
        letter = newletter;
    }
    public char getletter()
    {
        return letter;
    }
    private char letter;

}