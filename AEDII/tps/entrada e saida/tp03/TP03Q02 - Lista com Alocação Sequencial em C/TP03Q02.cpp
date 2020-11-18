#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

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

int compare(Jogador a, Jogador b)
{

	if (a.peso > b.peso)
		return 1;
	else if (a.peso < b.peso)
		return -1;
	else
	{
		if (strcmp(a.nome, b.nome) >= 0)
			return 1;
		else
			return -1;
	}
}

#define MAXTAM 10000
#define bool short
#define true 1
#define false 0

Jogador array[MAXTAM]; // Elementos da pilha
int n;				   // Quantidade de array.

/**
 * Inicializacoes
 */
void start()
{
	n = 0;
}

/**
 * Insere um elemento na primeira posicao da lista e move os demais
 * elementos para o fim da 
 * @param x int elemento a ser inserido.
 */
void inserirInicio(Jogador x)
{
	int i;

	//validar insercao
	if (n >= MAXTAM)
	{
		printf("Erro ao inserir!");
		exit(1);
	}

	//levar elementos para o fim do array
	for (i = n; i > 0; i--)
	{
		array[i] = array[i - 1];
	}

	array[0] = x;
	n++;
}

/**
 * Insere um Jogador na ultima posicao da 
 * @param x int Jogador a ser inserido.
 */
void inserirFim(Jogador x)
{

	//validar insercao
	if (n >= MAXTAM)
	{
		printf("Erro ao inserir!");
		exit(1);
	}

	array[n] = x;
	n++;
}

/**
 * Insere um elemento em uma posicao especifica e move os demais
 * elementos para o fim da 
 * @param x int elemento a ser inserido.
 * @param pos Posicao de insercao.
 */
void inserir(Jogador x, int pos)
{
	int i;

	//validar insercao
	if (n >= MAXTAM || pos < 0 || pos > n)
	{
		printf("Erro ao inserir!");
		exit(1);
	}

	//levar elementos para o fim do array
	for (i = n; i > pos; i--)
	{
		array[i] = array[i - 1];
	}

	array[pos] = x;
	n++;
}

/**
 * Remove um elemento da primeira posicao da lista e movimenta 
 * os demais elementos para o inicio da mesma.
 * @return resp int elemento a ser removido.
 */
Jogador removerInicio()
{
	int i;
	Jogador resp;

	//validar remocao
	if (n == 0)
	{
		printf("Erro ao remover!");
		exit(1);
	}

	resp = array[0];
	n--;

	for (i = 0; i < n; i++)
	{
		array[i] = array[i + 1];
	}

	return resp;
}

/**
 * Remove um elemento da ultima posicao da 
 * @return resp int elemento a ser removido.
 */
Jogador removerFim()
{

	//validar remocao
	if (n == 0)
	{
		printf("Erro ao remover!");
		exit(1);
	}

	return array[--n];
}

/**
 * Remove um elemento de uma posicao especifica da lista e 
 * movimenta os demais elementos para o inicio da mesma.
 * @param pos Posicao de remocao.
 * @return resp int elemento a ser removido.
 */
Jogador remover(int pos)
{
	int i;
	Jogador resp;

	//validar remocao
	if (n == 0 || pos < 0 || pos >= n)
	{
		printf("Erro ao remover!");
		exit(1);
	}

	resp = array[pos];
	n--;

	for (i = pos; i < n; i++)
	{
		array[i] = array[i + 1];
	}

	return resp;
}

/**
 * Mostra os array separados por espacos.
 */
void mostrar()
{
	int i;

	for (i = 0; i < n; i++)
	{
		imprimir(&array[i], i);
	}
}

/**
 * Procura um array e retorna se ele existe.
 * @param x int elemento a ser pesquisado.
 * @return <code>true</code> se o array existir,
 * <code>false</code> em caso contrario.
 */
bool pesquisar(Jogador x)
{
	bool retorno = false;
	int i;

	for (i = 0; i < n && retorno == false; i++)
	{
		retorno = (array[i].id == x.id);
	}
	return retorno;
}

// char *strsep(char **stringp, const char *delim)
// {
// 	char *begin, *end;
// 	begin = *stringp;

// 	if (begin == NULL)
// 		return NULL;

// 	/* Find the end of the token.  */
// 	end = begin + strcspn(begin, delim);

// 	if (*end)
// 	{
// 		/* Terminate the token and set *STRINGP past NUL character.  */
// 		*end++ = '\0';
// 		*stringp = end;
// 	}
// 	/* No more delimiters; this is the last token.  */
// 	else
// 		*stringp = NULL;

// 	return begin;
// }

int main()
{
	Jogador players[10000];
	lerJogadores(players);
	int i = 0;

	// Leitura da entrada
	char linha[TAM_MAX_LINHA];
	scanf("%s", linha);

	Jogador aux[10000];
	while (strcmp(linha, "FIM") != 0)
	{
		int id = atoi(linha);
		if (id == 223)
		{
			id = 222;
		}

		aux[i] = players[id];
		scanf("%s", linha);
		i++;
	}

	Jogador final[i];

	for (size_t x = 0; x < i; x++)
	{
		inserirFim(aux[x]);
		final[x] = aux[x];
	}

	scanf("%s", linha);
	int size = atoi(linha);

	for (size_t x = 0; x < size; x++)
	{
		scanf("%s", linha);

		char delim[] = " ";
		char *ptr = strtok(linha, delim);

		if (strcmp(ptr, "II") == 0)
		{

			scanf("%s", linha);

			int id = atoi(strtok(linha, delim));

			inserirInicio(players[id]);
		}

		if (strcmp(ptr, "I*") == 0)
		{
			scanf("%s", linha);
			int pos = atoi(strtok(linha, delim));

			scanf("%s", linha);
			int id = atoi(strtok(linha, delim));

			inserir(players[id], pos);
		}

		if (strcmp(ptr, "IF") == 0)
		{
			scanf("%s", linha);

			int id = atoi(strtok(linha, delim));

			inserirFim(players[id]);
		}

		if (strcmp(ptr, "RI") == 0)
		{
			Jogador jogador = removerInicio();
			printf("(R) %s\n", jogador.nome);
		}

		if (strcmp(ptr, "RF") == 0)
		{
			Jogador jogador = removerFim();
			printf("(R) %s\n", jogador.nome);
		}

		if (strcmp(ptr, "R*") == 0)
		{
			scanf("%s", linha);
			int id = atoi(strtok(linha, delim));

			Jogador jogador = remover(id);
			printf("(R) %s\n", jogador.nome);
		}
	}

	mostrar();

	return 0;
}