#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct RFolha
{
    struct RFolha *esq, *dir;
    int valor;
};

typedef struct RFolha TFolha;

void Imprimir(TFolha *);
void Destruir(TFolha *);
TFolha *AcharPai(TFolha *, int);

int main()
{
    TFolha *raiz, *aux, *pai;
    int C, N, i, valor;

    scanf("%d", &C);

    for (i = 1; i <= C; i++)
    {
        raiz = NULL;

        scanf("%d", &N);

        while (N--)
        {
            scanf("%d", &valor);

            aux = (TFolha *)malloc(sizeof(TFolha));
            aux->valor = valor;
            aux->esq = NULL;
            aux->dir = NULL;

            pai = AcharPai(raiz, valor);

            if (pai == NULL)
                raiz = aux;
            else if (valor <= pai->valor)
                pai->esq = aux;
            else
                pai->dir = aux;
        }

        printf("Case %d:\n", i);
        Imprimir(raiz);
        Destruir(raiz);
    }

    return 0;
}

void Imprimir(TFolha *raiz)
{
    TFolha *fila[500];
    int i = -1, n = 0;

    if (raiz == NULL)
        return;

    fila[++i] = raiz;

    while (n <= i)
    {
        if (fila[n]->esq != NULL)
            fila[++i] = fila[n]->esq;

        if (fila[n]->dir != NULL)
            fila[++i] = fila[n]->dir;

        if (n > 0)
            printf(" ");

        printf("%d", fila[n++]->valor);
    }

    printf("\n\n");
}

void Destruir(TFolha *raiz)
{
    if (raiz != NULL)
    {
        Destruir(raiz->esq);
        Destruir(raiz->dir);
        free(raiz);
    }
}

TFolha *AcharPai(TFolha *raiz, int valor)
{
    if (raiz == NULL)
        return NULL;
    else if (valor <= raiz->valor)
        if (raiz->esq == NULL)
            return raiz;
        else
            return AcharPai(raiz->esq, valor);
    else if (raiz->dir == NULL)
        return raiz;
    else
        return AcharPai(raiz->dir, valor);
}