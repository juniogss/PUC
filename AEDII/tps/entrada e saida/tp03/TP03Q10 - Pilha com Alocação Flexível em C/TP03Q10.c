#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <math.h>
#include <err.h>

int movimentacoes = 0;
int comparacoes = 0;
#define TAM_MAX_LINHA 2504

typedef struct
{
	int id;
	int peso;
	int altura;
	char nome[70];
	char universidade[70];
	char anoNascimento[7];
	char cidadeNascimento[70];
	char estadoNascimento[70];
} Jogador;

/**
 * Se linha = "65,Joe Graboski,201,88,,1930,,"
 * então
 * novaLinha = "65,Joe Graboski,201,88,nao informado,1930,nao informado,nao informado"
 * 
 * @param linha String com a linha do CSV.
 * @param novaLinha String que receberá uma nova linha com os campos vazios
 * preenchidos.
 */
void inserirNaoInformado(char *linha, char *novaLinha)
{
	int tam = strlen(linha);
	for (int i = 0; i <= tam; i++, linha++)
	{
		*novaLinha++ = *linha;
		if (*linha == ',' && (*(linha + 1) == ',' || *(linha + 1) == '\0'))
		{
			strcpy(novaLinha, "nao informado");
			novaLinha += strlen("nao informado");
		}
	}
}

void tirarQuebraDeLinha(char linha[])
{
	int tam = strlen(linha);

	if (linha[tam - 2] == '\r' && linha[tam - 1] == '\n') // Linha do Windows
		linha[tam - 2] = '\0';							  // Apaga a linha

	else if (linha[tam - 1] == '\r' || linha[tam - 1] == '\n') // Mac ou Linux
		linha[tam - 1] = '\0';								   // Apaga a linha
}

/**
 * @param jogador Ponteiro para o jogador a receber os dados
 * @param linha Linha do CSV. Ex.: "65,Joe Graboski,201,88,,1930,,"
 */
void ler(Jogador *jogador, char linha[])
{
	char novaLinha[TAM_MAX_LINHA];
	tirarQuebraDeLinha(linha);
	inserirNaoInformado(linha, novaLinha);

	jogador->id = atoi(strtok(novaLinha, ","));
	strcpy(jogador->nome, strtok(NULL, ","));
	jogador->altura = atoi(strtok(NULL, ","));
	jogador->peso = atoi(strtok(NULL, ","));
	strcpy(jogador->universidade, strtok(NULL, ","));
	strcpy(jogador->anoNascimento, strtok(NULL, ","));
	strcpy(jogador->cidadeNascimento, strtok(NULL, ","));
	strcpy(jogador->estadoNascimento, strtok(NULL, ","));
}

void imprimir(Jogador *jogador, int pos)
{
	printf("[%d] ## %s ## %d ## %d ## %s ## %s ## %s ## %s ##\n",
		   pos,
		   jogador->nome,
		   jogador->altura,
		   jogador->peso,
		   jogador->anoNascimento,
		   jogador->universidade,
		   jogador->cidadeNascimento,
		   jogador->estadoNascimento);
}

Jogador clone(Jogador *jogador)
{
	Jogador retorno;

	retorno.id = jogador->id;
	strcpy(retorno.nome, jogador->nome);
	retorno.altura = jogador->altura;
	retorno.peso = jogador->peso;
	strcpy(retorno.anoNascimento, jogador->anoNascimento);
	strcpy(retorno.universidade, jogador->universidade);
	strcpy(retorno.cidadeNascimento, jogador->cidadeNascimento);
	strcpy(retorno.estadoNascimento, jogador->estadoNascimento);

	return retorno;
}

// Lê uma linha e remove a quebra de linha do final dela.
// É necessário fazer isso pois o fgets deixa uma quebra de linha no final da string.
void readLine(char linha[], int tamMaxLinha, FILE *arquivo)
{
	fgets(linha, tamMaxLinha, arquivo);

	tirarQuebraDeLinha(linha);
}

void lerJogadores(Jogador players[])
{
	char linha[TAM_MAX_LINHA];
	FILE *arquivo = fopen("/tmp/players.csv", "r");
	int i = 0;

	if (arquivo == NULL)
	{
		perror("Não foi possível abrir o arquivo players.csv");
		exit(1);
	}

	// Lê os cabeçalhos do CSV "id,Player,height,weight,collage,born,birth_city,birth_state"
	fgets(linha, TAM_MAX_LINHA, arquivo);
	readLine(linha, TAM_MAX_LINHA, arquivo); // Lê o primeiro registro

	while (!feof(arquivo))
	{
		ler(&players[i], linha);
		readLine(linha, TAM_MAX_LINHA, arquivo); // Lê mais um registro
		i++;
	}

	fclose(arquivo);
}

//TIPO CELULA ===================================================================
typedef struct Celula
{
	Jogador elemento;	 // Elemento inserido na celula.
	struct Celula *prox; // Aponta a celula prox.
} Celula;

Celula *novaCelula(Jogador elemento)
{
	Celula *nova = (Celula *)malloc(sizeof(Celula));
	nova->elemento = elemento;
	nova->prox = NULL;
	return nova;
}

//PILHA PROPRIAMENTE DITA =======================================================
Celula *topo; // Sem celula cabeca.

/**
 * Cria uma fila sem elementos.
 */
void start()
{
	topo = NULL;
}

/**
 * Insere elemento na pilha (politica FILO).
 * @param x int elemento a inserir.
 */
void inserir(Jogador x)
{
	Celula *tmp = novaCelula(x);
	tmp->prox = topo;
	topo = tmp;
	tmp = NULL;
}

/**
 * Remove elemento da pilha (politica FILO).
 * @return Elemento removido.
 */
Jogador remover()
{
	if (topo == NULL)
	{
		errx(1, "Erro ao remover!");
	}

	Jogador resp = topo->elemento;
	Celula *tmp = topo;
	topo = topo->prox;
	tmp->prox = NULL;
	free(tmp);
	tmp = NULL;
	return resp;
}

/**
 * Mostra os elementos separados por espacos.
 */
void mostrar()
{
	Celula *i;
	int count = 0;
	for (i = topo; i != NULL; i = i->prox)
	{
		imprimir(&i->elemento, count);
		count++;
	}
}

void mostrar(Celula *cel, int pos)
{
	if (cel)
	{
		mostrar(cel->prox, --pos);
		imprimir(&cel->elemento, pos);
	}
}
// int funcao(int a, int b, int *c)
// {
// 	*c = a - b;
// 	return a * (b + 1);
// }

// #define R 10
// #define C 20

void fun(int *p){
	int q = 10;
	p = &q;
}

int main()
{
	int r = 20;
	int *p = &r;
	fun(p);
	printf("%d", *p);

	// int *p;
	// int x = 5;
	// p = &x;

	// int a = 5, *b, c = 10;
	// b = &a;
	// scanf("%d", b);
	// printf("%d %d", a, c);

	// int *p;
	// int v[] = {10, 7, 2, 6, 3};
	// p = v;

	// printf("%d", p[4]);

	// int x = 1, y = 2, w = 3;
	// int z = funcao(x, y, &w);
	// printf("%d %d\n", w, z);

	// int (*p)[R][C];
	// printf("%d", (int)sizeof(*p));

	return 0;

}

