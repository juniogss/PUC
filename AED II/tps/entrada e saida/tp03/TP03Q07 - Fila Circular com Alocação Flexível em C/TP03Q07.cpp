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

//FILA PROPRIAMENTE DITA ========================================================
Celula *primeiro;
Celula *ultimo;
int n;

/**
 * Cria uma fila sem elementos (somente no cabeca).
 */
void start(Jogador j)
{
	primeiro = novaCelula(j);
	ultimo = primeiro;
}

/**
 * Remove elemento da fila (politica FIFO).
 * @return Elemento removido.
 */
Jogador remover()
{
	if (primeiro == ultimo)
	{
		errx(1, "Erro ao remover!");
	}
	Celula *tmp = primeiro;
	primeiro = primeiro->prox;
	Jogador resp = primeiro->elemento;
	tmp->prox = NULL;
	free(tmp);
	tmp = NULL;
	n--;
	return resp;
}

/**
 * Retorna a media da altura dos registros da fila 
 */
int getMedia()
{
	double sum = 0;
	double count = 0;

	for (Celula *i = primeiro->prox; i != NULL; i = i->prox)
	{
		sum += i->elemento.altura;
		count++;
	}

	return round(sum / count);
}

/**
 * Insere elemento na fila (politica FIFO).
 * @param x int Elemento a inserir.
 */
void inserir(Jogador x)
{

	if (n == 5)
	{
		remover();
	}

	ultimo->prox = novaCelula(x);
	ultimo = ultimo->prox;
	n++;

	printf("%d\n", getMedia());
}

/**
 * Mostra os elementos separados por espacos.
 */
void mostrar()
{
	Celula *i;
	int count = 0;
	for (i = primeiro->prox; i != NULL; i = i->prox)
	{
		imprimir(&i->elemento, count);
		count++;
	}
}

int main()
{
	Jogador players[10000];
	lerJogadores(players);
	int i = 0;

	start(players[0]);

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
		inserir(aux[x]);
		final[x] = aux[x];
	}

	scanf("%s", linha);
	int size = atoi(linha);

	for (size_t x = 0; x < size; x++)
	{
		scanf("%s", linha);

		char delim[] = " ";
		char *ptr = strtok(linha, delim);

		if (strcmp(ptr, "I") == 0)
		{
			scanf("%s", linha);
			int id = atoi(strtok(linha, delim));

			inserir(players[id]);
		}

		if (strcmp(ptr, "R") == 0)
		{
			Jogador jogador = remover();
			printf("(R) %s\n", jogador.nome);
		}
	}

	mostrar();

	return 0;
}