/*
* Aim : Singly Circular Link List
* Author : Atul R. Raut
* Date   : Sunday, July 04 2010, 07:05 PM
* File   : list.c function defination.
***/

#include <stdio.h>
#include <stdlib.h>
#include "list.h"

void init(NODE *p1) {
	p1->data = 0;
	p1->next = NULL;
	p1->prev = NULL;
}

NODE* create (int data) {
	NODE *nn = NULL;
	nn = (NODE *) malloc (sizeof(NODE));
	if (nn == NULL)
		return;
	else {
		nn->data = data;
		nn->next = NULL;
		nn->prev = NULL;
	}
	return nn;
}

void addfirst(LIST *p1, int data) {
	NODE *nn = NULL;
	if (p1->head == NULL) {
		p1 = nn;
		p1->next = p1->prev;	// Circular ll starts here.
	}
	else {
		nn = create(data);
		nn->prev = p1->head->prev;
		nn->next = p1->head;
	}
}

void insert(LIST *, int data, int pos) {}

void addlast(LIST *p1, int data) {}
void delfirst(LIST *p1) {}
void deletenode(LIST *p1, int data) {}
void dellast(LIST *p1) {}
void display(LIST *p1) {}
void reverse(LIST *p1) {}
void printrev(LIST *p1, LIST *p2) {}
void freelist(LIST *p1) {}

