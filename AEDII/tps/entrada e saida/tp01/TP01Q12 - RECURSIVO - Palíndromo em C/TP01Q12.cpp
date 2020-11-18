#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <string.h>

//testar se e a palavra fim
int compareTo(char *teste)
{
    int resposta = 0;
    if ((teste[0] == 'F') &&
        (teste[1] == 'I') &&
        (teste[2] == 'M'))
    {
        resposta = 1;
    }

    return (resposta);
}

//testar se e palindromo
int Palindromo(char *testar, int x)
{
    int tamanho = strlen(testar);
    int resp = 1;
    //rodar a string procurando se tem alguma letra diferente
    if (x < (tamanho / 2))
    {
        resp = resp && (testar[x] == testar[tamanho - x - 1]) && Palindromo(testar, x + 1);
    }
    //retornar resposta
    return (resp);
}

int palindromo2(char *testar)
{
    return Palindromo(testar, 0);
}

int main()
{
    //variavel de alocacao de entrada
    char entrada[1000];
    scanf(" %[^\n]%*c", entrada);
    //loop para testar cada palavra de entrada
    while (compareTo(entrada) == 0)
    {
        if (palindromo2(entrada) == 1)
        {
            printf("SIM\n");
        }
        else
        {
            printf("NAO\n");
        }
        //nova leitura e re loop
        scanf("%[^\n]%*c", entrada);
    }
}
