//CPP Program to demonstrate the implementation of Stack.
#include<iostream>
#include<stdlib.h>
using namespace std;

void push();
void pop();
void display();

int a[5], top = -1, ch, n;

int main() {
    while (1) {
        cout << "\n 1. Push an element into stack";
        cout << "\n 2. Pop an element from stack";
        cout << "\n 3. Display the stack";
        cout << "\n 4. Exit";
        cout << "\nEnter your choice: \n";
        cin >> ch;

        switch (ch) {
            case 1:
                push();
                break;
            case 2:
                pop();
                break;
            case 3:
                display();
                break;
            case 4:
                exit(0);
            default:
                cout << "\nWrong option selected!";
        }
    }

    return 0;
}

void push() {
    top++;
    if (top < 5) {
        cout << "\nEnter a number: ";
        cin >> n;
        a[top] = n;
    } else {
        cout << "\nStack full";
        top = 4;
    }
}

void display() {
    int i;
    if (top < 0) {
        cout << "\nThere are no elements in the stack";
    } else {
        for (i = 0; i <= top; i++)
            cout << "\n" << a[i];
    }
}

void pop() {
    if (top < 0) {
        cout << "\nStack is empty";
    } else {
        n = a[top];
        top--;
        cout << "\nElement popped: " << n;
    }
}
