#include <stdio.h>
#include <stdlib.h>

int LerArquivos(int entrada)
{
    int controle;
    float entrar;
    // variável ponteiro para o arquivo
    FILE *text_arq;

    //abrir o arquivo
    text_arq = fopen("texto.txt", "w+");

    for (controle = 0; controle < entrada; controle++)
    {
        //ler do pub.in e escrever em texto.txt
        scanf("%f", &entrar);
        fprintf(text_arq, "%g\n", entrar);
    }

    // fechar arquivo
    fclose(text_arq);
    return 0;
}

void RelerEntrada(int numeroDeLinhasDoArquivo)
{
    int y;
    char tes[100];
    FILE *texto;

    texto = fopen("texto.txt", "r");
    for (int x = numeroDeLinhasDoArquivo - 1; x >= 0; x--)
    {
        fseek(texto, 0, SEEK_SET);
        for (y = 0; y < x; y++)
            fgets(tes, 100, texto);
        
        fgets(tes, 100, texto);
        printf("%s", tes);
    }
    
    fclose(texto);
}

int main()
{
    int entrada;
    scanf("%d", &entrada);
    LerArquivos(entrada);
    RelerEntrada(entrada);
    return 0;
}
