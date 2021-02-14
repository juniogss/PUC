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

typedef struct Node
{
	Jogador data;		//element in Node
	int ht;				//height of this Node
	struct Node *left;	//left Node
	struct Node *right; // Right Node
} node;

int height(node *T)
{
	int lh, rh;
	if (T == NULL)
		return (0);
	if (T->left == NULL)
		lh = 0;
	else
		lh = 1 + T->left->ht;
	if (T->right == NULL)
		rh = 0;
	else
		rh = 1 + T->right->ht;
	if (lh > rh)
		return (lh);
	return (rh);
}

int BF(node *T)
{
	int lh, rh;
	if (T == NULL)
		return (0);
	if (T->left == NULL)
		lh = 0;
	else
		lh = 1 + T->left->ht;
	if (T->right == NULL)
		rh = 0;
	else
		rh = 1 + T->right->ht;
	return (lh - rh);
}

node *rotateright(node *x)
{
	node *y;
	y = x->left;
	x->left = y->right;
	y->right = x;
	x->ht = height(x);
	y->ht = height(y);
	return (y);
}

node *rotateleft(node *x)
{
	node *y;
	y = x->right;
	x->right = y->left;
	y->left = x;
	x->ht = height(x);
	y->ht = height(y);
	return (y);
}

node *RR(node *T)
{
	T = rotateleft(T);
	return (T);
}

node *LL(node *T)
{
	T = rotateright(T);
	return (T);
}

node *LR(node *T)
{
	T->left = rotateleft(T->left);
	T = rotateright(T);
	return (T);
}

node *RL(node *T)
{
	T->right = rotateright(T->right);
	T = rotateleft(T);
	return (T);
}

node *insert(node *T, Jogador x)
{
	if (T == NULL)
	{
		T = (node *)malloc(sizeof(node));
		T->data = x;
		T->left = NULL;
		T->right = NULL;
	}
	else if (strcmp(x.nome, T->data.nome) > 0) // insert in right subtree
	{
		T->right = insert(T->right, x);
		if (BF(T) == -2)
			if (strcmp(x.nome, T->right->data.nome) > 0)
				T = RR(T);
			else
				T = RL(T);
	}
	else if (strcmp(x.nome, T->data.nome) < 0) // insert in right subtree
	{
		T->left = insert(T->left, x);
		if (BF(T) == 2)
			if (strcmp(x.nome, T->left->data.nome) < 0)
				T = LL(T);
			else
				T = LR(T);
	}
	T->ht = height(T);
	return (T);
}

bool search(node *T, char *str)
{
	bool response = false;
	if (T != NULL)
	{
		int comparisonValue = strcmp(str, T->data.nome);
		if (comparisonValue == 0)
		{
			response = true;
		}
		else if (comparisonValue == -1)
		{
			response = search(T->left, str);
		}
		else if (comparisonValue == 1)
		{
			response = search(T->right, str);
		}
	}
	return response;
}

bool searchPrinting(node *T, char *str)
{
	bool response = false;
	if (T != NULL)
	{
		int comparisonValue = strcmp(str, T->data.nome);
		if (comparisonValue == 0)
		{
			response = true;
		}
		else if (comparisonValue < 0)
		{
			printf(" esq");
			response = searchPrinting(T->left, str);
		}
		else if (comparisonValue > 0)
		{
			printf(" dir");
			response = searchPrinting(T->right, str);
		}
	}
	return response;
}

int main()
{

	Jogador jogadores[10000];
	lerJogadores(jogadores);
	int i = 0;
	node *root = NULL;

	// Leitura da entrada
	char linha[TAM_MAX_LINHA];
	scanf("%s", linha);

	clock_t start = clock();

	while (strcmp(linha, "FIM") != 0)
	{
		int id = atoi(linha);
		if (id == 223)
		{
			id = 222;
		}

		root = insert(root, jogadores[id]);
		scanf("%s", linha);
		i++;
	}

	scanf(" %[^\n]s", linha);

	int x = 0;
	while (x < 100)
	{
		linha[strcspn(linha, "\r\n")] = 0;
		x++;
		printf("%s raiz", linha);
		bool val = searchPrinting(root, linha);
		if (val)
		{
			printf(" SIM");
		}
		else
		{
			printf(" NAO");
		}
		printf("\n");

		scanf(" %[^\n]s", linha);
	}
	return 0;
}
