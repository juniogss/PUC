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
void swap(int *i, int *j, Jogador jogadores[])
{
	Jogador temp = jogadores[*i];
	jogadores[*i] = jogadores[*j];
	jogadores[*j] = temp;
	movimentacoes += 3;
}

int compare(Jogador a, Jogador b)
{
	comparacoes++;

	if (atoi(a.anoNascimento) > atoi(b.anoNascimento))
		return 1;
	else if (atoi(a.anoNascimento) < atoi(b.anoNascimento))
		return -1;
	else
	{
		if (strcmp(a.nome, b.nome) > 0)
			return 1;
		else if (strcmp(a.nome, b.nome) < 0)
			return -1;
		else
			return 0;
	}
}

void bolha(Jogador jogadores[], int n)
{
	int i, j;
	for (i = (n - 1); i > 0; i--)
	{
		for (j = 0; j < i; j++)
		{
			if (compare(jogadores[j], jogadores[j + 1]) > 0)
			{
				swap(&j, &j + 1, jogadores);
			}
		}
	}
}

int main()
{
	Jogador jogadores[10000];
	lerJogadores(jogadores);
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

		aux[i] = jogadores[id];
		scanf("%s", linha);
		i++;
	}

	Jogador final[i];

	for (size_t x = 0; x < i; x++)
	{
		final[x] = aux[x];
	}

	clock_t start = clock();

	bolha(final, i);

	for (size_t x = 0; x < i; x++)
	{
		imprimir(&final[x]);
	}

	int end = clock() - start;

	// criando a variável ponteiro para o arquivo
	FILE *pont_arq;

	//abrindo o arquivo
	pont_arq = fopen("633516_bolha.txt", "w");

	fprintf(pont_arq, "633516\t%d\t%d\t%d", movimentacoes, comparacoes, end);

	// fechando arquivo
	fclose(pont_arq);

	return 0;
}