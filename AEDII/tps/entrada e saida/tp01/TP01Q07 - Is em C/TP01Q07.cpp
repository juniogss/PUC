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

bool IsVowel(char *word, int x)
{
    return  word[x] == 'a' || word[x] == 'e' || word[x] == 'i' || word[x] == 'o' || word[x] == 'u' || word[x] == 'A' || word[x] == 'E' || word[x] == 'I' || word[x] == 'O' || word[x] == 'U';
}

bool IsInteger(char *word, int x)
{
    return word[x] >= '0' && word[x] <= '9';
}

/* Verifica se o texto só tem vogais */
bool Vowel(char *testar)
{
    bool resp = true;

    for (int x = 0; x < strlen(testar) - 1; x++)
        if (IsVowel(testar, x) == false)
            resp = false;

    return (resp);
}

/* Verifica se o texto só tem consoantes */
bool Consonant(char *testar)
{
    bool resp = true;

    for (int x = 0; x < strlen(testar) - 1; x++)
        if (IsVowel(testar, x))
            resp = false;
        else if (IsInteger(testar, x))
            resp = false;

    return (resp);
}

/* Verifica se o texto só tem inteiros */
bool Integer(char *testar)
{
    bool resp = true;

    for (int x = 0; x < strlen(testar) - 1; x++)
        if (!IsInteger(testar, x))
            resp = false;

    return (resp);
}

/* Verifica se o texto só numeros reais */
bool Real(char *testar)
{
    int dot = 0, comma = 0;
    bool resp = true;

    for (int x = 0; x < strlen(testar) - 1; x++)
        if (!IsInteger(testar, x))
        {
            if (testar[x] == '.')
                dot++;
            else
            {
                if (testar[x] == ',')
                    comma++;
                else
                    resp = false;
            }
        }

    if (resp)
        if ((dot == 1 && comma == 0) || (dot == 0 && comma == 1) || (dot == 0 && comma == 0))
            return true;
        else
            return false;
    else
        return false;
}

int main()
{
    //variavel de alocacao de entrada
    char entrada[1000];

    scanf(" %[^\n]%*c", entrada);

    while (!isFim(entrada))
    {
        if (Vowel(entrada))
            printf("SIM ");
        else
            printf("NAO ");

        if (Consonant(entrada))
            printf("SIM ");
        else
            printf("NAO ");

        if (Integer(entrada))
            printf("SIM ");
        else
            printf("NAO ");

        if (Real(entrada))
            printf("SIM\n");
        else
            printf("NAO\n");

        scanf("%[^\n]%*c", entrada);
    }
}
