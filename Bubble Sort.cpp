// Code for Bubble Sort in CPP.
#include<iostream>
using namespace std;

int main() {
    int n, *p;
    int i, j, t;

    cout << "Enter the size of array: ";
    cin >> n;

    p = new int[n];

    cout << "Enter " << n << " elements: ";
    for (i = 0; i < n; i++)
        cin >> p[i];

    cout << "\nElements before bubble sorting\n";
    for (i = 0; i < n; i++)
        cout << p[i] << "\t";

    for (i = 0; i < n - 1; i++) {
        for (j = n - 1; j >= i + 1; j--) {
            if (p[j - 1] > p[j]) {
                t = p[j];
                p[j] = p[j - 1];
                p[j - 1] = t;
            }
        }
    }

    cout << "\nEnter after bubble sorting\n";
    for (i = 0; i < n; i++)
        cout << p[i] << "\t";

    delete[] p; // Don't forget to free the allocated memory
    return 0;
}
