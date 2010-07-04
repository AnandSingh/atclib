/*
* Aim : Create Singly Circular Link List Using head tail mechanism.
* Author : Atul Ramesh Raut
* Date   : Sunday, July 04 2010, 02:44 PM
* File   : list.h, function prototype.
***/

#include <stdio.h>
#include <stdlib.h>

typedef struct node_t {
	int data;	
	struct node_t *next;
	struct node_t *prev;
} NODE;

typedef struct list_t {
	NODE *head;
} LIST;

void init(NODE *p1);
NODE* create (int data);
void addfirst(LIST *p1, int data); 
void insert(LIST *, int data, int pos); 
void addlast(LIST *p1, int data); 
void delfirst(LIST *p1);
void deletenode(LIST *p1, int data); 
void dellast(LIST *p1); 
void display(LIST *p1); 
void reverse(LIST *p1);
void printrev(LIST *p1, LIST *p2); 
void freelist(LIST *p1); 

