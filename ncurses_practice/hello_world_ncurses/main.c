#include <ncurses.h>

int main()
{
	initscr();			// Start curses mode
	raw();
	printw("Hello World !!!");	// Print Hello World
	refresh();			// Print it on to the real screen
	getch();			// Wait for user input
	endwin();			// End curses mode
	return 0;
}
