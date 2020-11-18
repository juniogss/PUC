#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

bool isFim(char *teste)
{
    int resposta = false;
    if ((teste[0] == 'F') && (teste[1] == 'I') && (teste[2] == 'M'))
        resposta = true;

    return (resposta);
}

//testar se e palindromo
int Palindromo(char *testar)
{
    int tamanho = strlen(testar);
    int resp = 1;

    //rodar a string procurando se tem alguma letra diferente
    for (int x = 0; x < (tamanho / 2); x++)
        resp = resp && (testar[x] == testar[tamanho - x - 1]);

    return (resp);
}

int main()
{
    //variavel de alocacao de entrada
    char entrada[1000];

    scanf(" %[^\n]%*c", entrada);

    while (!isFim(entrada))
    {
        if (Palindromo(entrada) == 1)
            printf("SIM\n");
        else
            printf("NAO\n");

        scanf("%[^\n]%*c", entrada);
    }
}
