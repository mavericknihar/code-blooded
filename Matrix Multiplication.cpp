//Program to show the multiplication of matrices in CPP
#include<iostream>
using namespace std;

int main() {
    int a[3][3], b[3][3], c[3][3], i, j, k, sum;

    cout << "\nEnter Nine Elements For Matrix A: ";
    for (i = 0; i < 3; i++) {
        for (j = 0; j < 3; j++)
            cin >> a[i][j];
    }

    cout << "\nEnter Nine Elements For Matrix B: ";
    for (i = 0; i < 3; i++) {
        for (j = 0; j < 3; j++)
            cin >> b[i][j];
    }

    for (i = 0; i < 3; i++)
        for (j = 0; j < 3; j++) {
            sum = 0;
            for (k = 0; k < 3; k++)
                sum = sum + a[i][k] * b[k][j];
            c[i][j] = sum;
        }

    cout << "\nMatrix A * Matrix B = Matrix C \n";
    cout << "\n----------------------------------------\n";
    for (i = 0; i < 3; i++) {
        for (j = 0; j < 3; j++)
            cout << " " << a[i][j];
        cout << "\t\t";

        for (j = 0; j < 3; j++)  // Corrected the loop variable from k to j
            cout << " " << b[i][j];
        
        cout << "\t\t";

        for (j = 0; j < 3; j++)  // Display matrix C
            cout << " " << c[i][j];

        cout << "\n";
    }

    return 0;
}
