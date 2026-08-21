#Program to multiply two matrices using Nested Loops
X = [[3,3,2],
     [4,1,5],
     [7,2,7]]

Y = [[5,3,1,2],
     [1,7,3,0],
     [2,5,2,1]]

MUL = [[0,0,0,0],
        [0,0,0,0],
        [0,0,0,0]]

print("Multiplication of the tow matrices is ; ")
for i in range(len(X)):
    for j in range(len(Y[0])):
        for k in range(len(Y)):\
        MUL[i][j] += X[i][k] * Y[k][j]

for r in MUL:
    print(r)
