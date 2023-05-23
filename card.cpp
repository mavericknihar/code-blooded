#include <iostream>
#include <string>
using namespace std;

class Card {
    char suite;
    string value;
    static string validvalue[13];
    static char validsuite[4];

    static int CheckValue(string v) {
        for (int i = 0; i < 13; i++) {
            if (v == Card::validvalue[i]) {
                return 1;
            }
        }
        return 0;
    }

    static int CheckSuite(char s) {
        for (int i = 0; i < 4; i++) {
            if (s == Card::validsuite[i]) {
                return 1;
            }
        }
        return 0;
    }

public:
    void SetCard(string v, char s) {
        if (CheckValue(v) && CheckSuite(s)) {
            suite = s;
            value = v;
        }
        else {
            cout << "\nInvalid Data";
        }
    }

    static string PrintCard(Card t) {
        string ct;
        ct = t.value;
        ct.push_back(t.suite);
        return ct;
    }
};

string Card::validvalue[13] = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "K", "Q", "A" };
char Card::validsuite[4] = { 'C', 'H', 'D', 'S' };

int main() {
    Card c;
    c.SetCard("7", 'S');
    cout << "\n" << Card::PrintCard(c);
    return 0;
}
