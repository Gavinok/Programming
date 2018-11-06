#include <ncurses.h>

int main()
{
	initscr();			// Start curses mode
	raw();
	attron(A_STANDOUT | A_UNDERLINE);//start stylizing the text
	mvprintw(12,40, "read this now");
	attroff(A_STANDOUT | A_UNDERLINE);//stop stylizing text
	refresh();			// Print it on to the real screen
	getch();			// Wait for user input
	endwin();			// End curses mode
	return 0;
}
