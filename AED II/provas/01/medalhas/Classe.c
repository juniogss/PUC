#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>

typedef struct
{
    char nome[41];
    int ouro, prata, bronze;
} pais;

int compara(const void *p1, const void *p2)
{
    pais *i = (pais *)p1, *j = (pais *)p2;
    if (i->ouro > j->ouro)
    {
        return -1;
    }
    else if (i->ouro < j->ouro)
    {
        return 1;
    }
    else
    {
        if (i->prata > j->prata)
        {
            return -1;
        }
        else if (i->prata < j->prata)
        {
            return 1;
        }
        else
        {
            if (i->bronze > j->bronze)
            {
                return -1;
            }
            else if (i->bronze < j->bronze)
            {
                return 1;
            }
            else
            {
                return strcmp(i->nome, j->nome);
            }
        }
    }
}

int main()
{

    int n, x;
    scanf("%d", &n);
    pais p[n];
    
    for (x = 0; x < n; x++)
    {
        scanf(" %s %d %d %d", p[x].nome, &p[x].ouro, &p[x].prata, &p[x].bronze);
    }

    qsort(p, n, sizeof(p[0]), compara);
    for (x = 0; x < n; x++)
    {
        printf("%s %d %d %d\n", p[x].nome, p[x].ouro, p[x].prata, p[x].bronze);
    }
    return 0;
}
