// Program to Print a 2D Array in CPP.
#include<iostream>
using namespace std;

int main() {
    int a[3][3], i, j, k;

    cout << "Enter Nine Elements For Matrix A: ";
    for (i = 0; i < 3; i++) {
        for (j = 0; j < 3; j++)
            cin >> a[i][j];
    }

    cout << "\nMatrix A Transpose Of Matrix A \n";
    cout << "\n-------- ----------------------\n";

    for (i = 0; i < 3; i++) {
        for (j = 0; j < 3; j++)
            cout << " " << a[i][j];

        cout << "\t\t";

        for (k = 0; k < 3; k++)
            cout << " " << a[k][i];

        cout << "\n";
    }

    return 0;
}
