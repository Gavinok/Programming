#include <ncurses.h>

int main()
{
	initscr();			// Start curses mode
	raw();
	start_color();
	init_pair(1,COLOR_RED, COLOR_BLUE);//pair color scheme 1 with red text
						//and blue high lights
	attron(COLOR_PAIR(1));		//attach scheme 1 to all the following text
	printw("AHH my eye");		//to be printed
	attroff(COLOR_PAIR(1));		//stop using color scheme 1
	getch();			// Wait for user input
	endwin();			// End curses mode
	return 0;
}
