#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <iostream>

// void troca(double& x, double& y){
//          double aux = x;
//          x = y;
//          y = x;
// }
// using namespace std;

// int main(){
//           double x = 7.0;
//           double y = 5.0;
//           troca(x, y);
//           cout << "X: " << x;
//           cout << "Y: " << y;
// }

void f(int val, int &ref)
{
    val++;
    ref++;
}

int main()
{
    int i = 1, j = 1;
    f(i, j);
    printf("%i -- %i", i, j);
}