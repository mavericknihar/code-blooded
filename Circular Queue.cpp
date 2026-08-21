//Program to demonstrate Circular Queue in CPP
#include<iostream>
#include<stdlib.h>
using namespace std;

class node {
public:
    int data;
    node *next;
};

class cirqueue {
public:
    node *rear;
};

void addq(cirqueue *q, int n) {
    node *ptr = new (nothrow) node;
    if (ptr == nullptr) {
        cout << "\nMemory allocation failed";
        exit(EXIT_FAILURE);
    }
    ptr->data = n;
    if (q->rear == NULL) {
        q->rear = ptr;
        ptr->next = ptr;
    }
    else {
        ptr->next = q->rear->next;
        q->rear->next = ptr;
        q->rear = ptr;
    }
}

int deleteq(cirqueue *q) {
    node *ptr;
    int n;
    if (q->rear == NULL) {
        cout << "\nQueue Empty";
        return -1;
    }
    else {
        ptr = q->rear->next;
        n = ptr->data;
    }
    if (ptr == q->rear) {
        q->rear = NULL;
        delete ptr;
    }
    else {
        q->rear->next = ptr->next;
        delete ptr;
    }
    return n;
}

void display(const cirqueue &q) {
    node *p, *r;
    r = p = q.rear->next;
    if (p == NULL) {
        cout << "\nCircular Queue Is Empty";
    }
    else {
        do {
            cout << "\n" << p->data;
            p = p->next;
        } while (p != r);
    }
}

int main() {
    cirqueue q1;
    int n, c;
    q1.rear = NULL;
    do {
        cout << "\n 1. Add an element";
        cout << "\n 2. Delete an element";
        cout << "\n 3. Display";
        cout << "\n 4. Exit";
        cout << "\n";
        cin >> c;
        switch (c) {
        case 1:
            cout << "\nEnter the element to be inserted: ";
            cin >> n;
            addq(&q1, n);
            cout << "\nCircular queue after addition: ";
            display(q1);
            break;
        case 2:
            n = deleteq(&q1);
            if (n >= 0)
                cout << "\nElement deleted: " << n;
            else
                cout << "\nDeletion unsuccessful";
            break;
        case 3:
            cout << "\nQueue is ";
            display(q1);
            break;
        default:
            exit(0);
        }
    } while (c != 4);
    return 0;
}
