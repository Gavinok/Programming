#include <ncurses.h>

int main()
{
	initscr();
	raw();
	int derp = 4;
	//prints just like printf
	printw("this is %d", derp);
	//places a char at the current position
	addch('a');
	//moves the curser to that position [y,x]
	move(12,13);
	//prints at a specified location [y,x]
	mvprintw(15,20, "Movement");
	//prints char at a specified location
	mvaddch(12,50,'@');

	getch();
	endwin();

	return 0;
}
