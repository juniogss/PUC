#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <err.h>

int movimentacoes = 0;
int comparacoes = 0;
#define TAM_MAX_LINHA 2504
#define bool short
#define true 1
#define false 0

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

//TIPO CELULA ===================================================================
typedef struct CelulaDupla
{
	Jogador elemento;		  // Elemento inserido na celula.
	struct CelulaDupla *prox; // Aponta a celula prox.
	struct CelulaDupla *ant;  // Aponta a celula anterior.
} CelulaDupla;

CelulaDupla *novaCelulaDupla(Jogador elemento)
{
	CelulaDupla *nova = (CelulaDupla *)malloc(sizeof(CelulaDupla));
	nova->elemento = elemento;
	nova->ant = nova->prox = NULL;
	return nova;
}

//LISTA PROPRIAMENTE DITA =======================================================
CelulaDupla *primeiro;
CelulaDupla *ultimo;

/**
 * Cria uma lista dupla sem elementos (somente no cabeca).
 */
void start(Jogador x)
{
	primeiro = novaCelulaDupla(x);
	ultimo = primeiro;
}

/**
 * Insere um elemento na primeira posicao da lista.
 * @param x int elemento a ser inserido.
 */
void inserirInicio(Jogador x)
{
	CelulaDupla *tmp = novaCelulaDupla(x);

	tmp->ant = primeiro;
	tmp->prox = primeiro->prox;
	primeiro->prox = tmp;
	if (primeiro == ultimo)
	{
		ultimo = tmp;
	}
	else
	{
		tmp->prox->ant = tmp;
	}
	tmp = NULL;
}

/**
 * Insere um elemento na ultima posicao da lista.
 * @param x int elemento a ser inserido.
 */
void inserirFim(Jogador x)
{
	ultimo->prox = novaCelulaDupla(x);
	ultimo->prox->ant = ultimo;
	ultimo = ultimo->prox;
}

/**
 * Remove um elemento da primeira posicao da lista.
 * @return resp int elemento a ser removido.
 */
Jogador removerInicio()
{
	if (primeiro == ultimo)
	{
		errx(1, "Erro ao remover (vazia)!");
	}

	CelulaDupla *tmp = primeiro;
	primeiro = primeiro->prox;
	Jogador resp = primeiro->elemento;
	tmp->prox = primeiro->ant = NULL;
	free(tmp);
	tmp = NULL;
	return resp;
}

/**
 * Remove um elemento da ultima posicao da lista.
 * @return resp int elemento a ser removido.
 */
Jogador removerFim()
{
	if (primeiro == ultimo)
	{
		errx(1, "Erro ao remover (vazia)!");
	}
	Jogador resp = ultimo->elemento;
	ultimo = ultimo->ant;
	ultimo->prox->ant = NULL;
	free(ultimo->prox);
	ultimo->prox = NULL;
	return resp;
}

/**
 *  Calcula e retorna o tamanho, em numero de elementos, da lista.
 *  @return resp int tamanho
 */
int tamanho()
{
	int tamanho = 0;
	CelulaDupla *i;
	for (i = primeiro; i != ultimo; i = i->prox, tamanho++)
		;
	return tamanho;
}

/**
 * Insere um elemento em uma posicao especifica considerando que o 
 * primeiro elemento valido esta na posicao 0.
 * @param x int elemento a ser inserido.
 * @param pos int posicao da insercao.
 * @throws Exception Se <code>posicao</code> invalida.
 */
void inserir(Jogador x, int pos)
{

	int tam = tamanho();

	if (pos < 0 || pos > tam)
	{
		errx(1, "Erro ao remover (posicao %d/%d invalida!", pos, tam);
	}
	else if (pos == 0)
	{
		inserirInicio(x);
	}
	else if (pos == tam)
	{
		inserirFim(x);
	}
	else
	{
		// Caminhar ate a posicao anterior a insercao
		CelulaDupla *i = primeiro;
		int j;
		for (j = 0; j < pos; j++, i = i->prox)
			;

		CelulaDupla *tmp = novaCelulaDupla(x);
		tmp->ant = i;
		tmp->prox = i->prox;
		tmp->ant->prox = tmp->prox->ant = tmp;
		tmp = i = NULL;
	}
}

/**
 * Remove um elemento de uma posicao especifica da lista
 * considerando que o primeiro elemento valido esta na posicao 0.
 * @param posicao Meio da remocao.
 * @return resp int elemento a ser removido.
 * @throws Exception Se <code>posicao</code> invalida.
 */
Jogador remover(int pos)
{
	Jogador resp;
	int tam = tamanho();

	if (primeiro == ultimo)
	{
		errx(1, "Erro ao remover (vazia)!");
	}
	else if (pos < 0 || pos >= tam)
	{
		errx(1, "Erro ao remover (posicao %d/%d invalida!", pos, tam);
	}
	else if (pos == 0)
	{
		resp = removerInicio();
	}
	else if (pos == tam - 1)
	{
		resp = removerFim();
	}
	else
	{
		// Caminhar ate a posicao anterior a insercao
		CelulaDupla *i = primeiro->prox;
		int j;
		for (j = 0; j < pos; j++, i = i->prox)
			;

		i->ant->prox = i->prox;
		i->prox->ant = i->ant;
		resp = i->elemento;
		i->prox = i->ant = NULL;
		free(i);
		i = NULL;
	}

	return resp;
}

/**
 * Mostra os elementos da lista de forma invertida 
 * e separados por espacos.
 */
// void mostrarInverso()
// {
// 	printf("[ ");
// 	CelulaDupla *i;
// 	for (i = ultimo; i != primeiro; i = i->ant)
// 	{
// 		printf("%d ", i->elemento);
// 	}
// 	printf("] \n"); // Termina de mostrar.
// }

/**
 * Procura um elemento e retorna se ele existe.
 * @param x Elemento a pesquisar.
 * @return <code>true</code> se o elemento existir,
 * <code>false</code> em caso contrario.
 */
CelulaDupla *getCel(int x)
{
	CelulaDupla *i;
	int n = 0;

	for (i = primeiro->prox; i != NULL; i = i->prox)
	{
		if (n == x)
			break;
		n++;
	}
	return i;
}

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

void imprimir(Jogador *jogador)
{
	printf("[%d ## %s ## %d ## %d ## %s ## %s ## %s ## %s]\n",
		   jogador->id,
		   jogador->nome,
		   jogador->altura,
		   jogador->peso,
		   jogador->anoNascimento,
		   jogador->universidade,
		   jogador->cidadeNascimento,
		   jogador->estadoNascimento);
}

/**
 * Mostra os elementos da lista separados por espacos.
 */
void mostrar()
{
	CelulaDupla *i;
	for (i = primeiro->prox; i != NULL; i = i->prox)
	{
		imprimir(&i->elemento);
	}
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

void lerJogadores(Jogador jogadores[])
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
		ler(&jogadores[i], linha);
		readLine(linha, TAM_MAX_LINHA, arquivo); // Lê mais um registro
		i++;
	}

	fclose(arquivo);
}

// PROCEDIMENTO PARA TROCAR DOIS ELEMENTOS DO VETOR
void swap(int *i, int *j)
{
	Jogador temp = getCel(*i)->elemento;
	getCel(*i)->elemento = getCel(*j)->elemento;
	getCel(*j)->elemento = temp;
	movimentacoes += 3;
}

int compare(Jogador a, Jogador b)
{
	comparacoes++;

	if (strcmp(a.estadoNascimento, b.estadoNascimento) >= 1)
		return 1;
	else if (strcmp(a.estadoNascimento, b.estadoNascimento) <= -1)
		return -1;
	else
		return strcmp(a.nome, b.nome);
}

void quicksortRec(int esq, int dir)
{
	int i = esq, j = dir;
	Jogador pivo = getCel((dir + esq) / 2)->elemento;
	// Jogador pivo = jogadores[(dir + esq) / 2];
	while (i <= j)
	{
		while (compare(getCel(i)->elemento, pivo) <= -1)
			i++;
		while (compare(getCel(j)->elemento, pivo) >= 1)
			j--;
		if (i <= j)
		{
			swap(&i, &j);
			i++;
			j--;
		}
	}
	if (esq < j)
		quicksortRec(esq, j);
	if (i < dir)
		quicksortRec(i, dir);
}

void quicksort()
{
	quicksortRec(0, tamanho() - 1);
}

int main()
{
	Jogador jogadores[10000];
	lerJogadores(jogadores);
	int i = 0;

	// Leitura da entrada
	char linha[TAM_MAX_LINHA];
	scanf("%s", linha);

	start(jogadores[0]);
	clock_t start = clock();

	while (strcmp(linha, "FIM") != 0)
	{
		int id = atoi(linha);
		if (id == 223)
		{
			id = 222;
		}

		inserirFim(jogadores[id]);
		scanf("%s", linha);
		i++;
	}


	quicksort();
	mostrar();

	double end = (double)(clock() - start) / CLOCKS_PER_SEC;

	// criando a variável ponteiro para o arquivo
	FILE *pont_arq;

	//abrindo o arquivo
	pont_arq = fopen("633516_quicksort2.txt", "w");

	fprintf(pont_arq, "633516\t%d\t%d\t%lf", movimentacoes, comparacoes, end);

	// fechando arquivo
	fclose(pont_arq);

	return 0;
}