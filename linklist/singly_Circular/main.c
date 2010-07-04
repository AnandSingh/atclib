/*
*  Aim    : Singly Circular Link List
*  Date   : Saturday, July 03 2010 12:49 AM
*  Author : Atul R. Raut
*  File   : main.c
***/

#include <stdio.h>
#include <stdlib.h>
#include "list.h"

int main()
{
	int  ch, val,pos;
	LIST l1 , l2;
	init(&l1);
	init(&l2);
	printf("\nEnter to the world of Singly Circular Link List:");
	while(1)
	{
		printf("\n0 >Eixt\n1 >AddFirst \n2 >Insert \n3 >AddLast  \ 
			\n4 >DeleteFirst \n5 >DeleteNode \n6 >DeleteLast \
			\n7 >Display \n8 >Reverse \n9 >Print Reversly    \
			\n10 >Freelist \n");
		scanf("%d", &ch);
		switch(ch)
		{
			case 0:
					exit(0);
			case 1:
					printf("\nEnter the number : ");
					scanf("%d", &val);
					addfirst(&l1,val);
					break;
			case 2:
					printf("\nEnter the number and position :");
					scanf("%d%d", &val,&pos);
					insert(&l1,val,pos);
					break;
			case 3:
					printf("\nEnter the number : ");
					scanf("%d", &val);
					addlast(&l2,val);
					break;
			case 4:
					delfirst(&l1);
					break;
			case 5:
					printf("\nEnter the position :");
					scanf("%d",&pos);
					deletenode(&l1,pos);
					break;
			case 6:
					dellast(&l1);
					break;
			case 7:
					display(&l1);
					break;
			case 8:
					reverse(&l1);
					break;
			case 9:
					printrev(&l2,l2.head);
					break;
			case 10:
					freelist(&l1);
					break;
			default :
					printf("\nEnter the correct choice ");
		}
	}
	return 0;
}

