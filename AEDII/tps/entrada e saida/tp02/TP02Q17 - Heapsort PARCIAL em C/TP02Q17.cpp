#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

#define TAM_MAX_LINHA 2504
int comp = 0;
int tam = 0;

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

void log(long cronometro)
{
	FILE *arq;
	arq = fopen("633516_heapsortParcial.txt", "w");
	fprintf(arq, "633516\t%ld\t%d", cronometro, comp);
	fclose(arq);
}

void swap(Jogador vet[], int pos1, int pos2)
{
	Jogador aux = vet[pos2];
	vet[pos2] = vet[pos1];
	vet[pos1] = aux;
}

int compare(int a, int b, Jogador jogadores[])
{

	if (jogadores[a].altura > jogadores[b].altura)
		return 1;
	else if (jogadores[a].altura < jogadores[b].altura)
		return -1;
	else
	{
		if (strcmp(jogadores[a].nome, jogadores[b].nome) > 0)
			return 1;
		else if (strcmp(jogadores[a].nome, jogadores[b].nome) < 0)
			return -1;
		else
			return 0;
	}
}

void add(Jogador vet[], int n, Jogador x)
{
	int pos = tam;
	vet[tam] = x;
	tam++;
	while (pos > 0)
	{
		if (compare(((pos - 1) / 2), pos, vet) < 0)
		{
			comp++;
			swap(vet, (pos - 1) / 2, pos);
			pos = (pos - 1) / 2;
		}
		else
		{
			pos = -1;
		}
	}
}

Jogador remove(int n, Jogador vet[])
{
	Jogador removido = vet[0];
	int i = 0;
	int pos;
	vet[0] = vet[--tam];

	while (i < ((n - 1) / 2))
	{
		if (compare(i, (2 * i + 1), vet) < 0 || compare(i, (2 * i + 2), vet) < 0)
		{
			comp += 2;

			pos = compare((2 * i + 1), (2 * i + 2), vet) > 0 ? 2 * i + 1 : 2 * i + 2;
			swap(vet, i, pos);
			i = pos;
		}
		else
		{
			i += n;
		}
	}
	return removido;
}

void heap(Jogador vet[], int n)
{
	Jogador jogador[n];

	for (int i = 0; i < n; i++)
		add(jogador, n, vet[i]);

	for (int i = n - 1; i >= 0; i--)
		vet[i] = remove(n--, jogador);
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

	long tempo = clock();

	heap(final, i);

	tempo = clock() - tempo;
	log(tempo);

	for (int i = (i - 1); i > 0; i--)
	{
		for (int j = 0; j < i; j++)
		{
			if ((final[j].altura > final[j + 1].altura) 
			|| ((strcmp(final[j].nome, final[j + 1].nome) > 0) && final[j].altura == final[j + 1].altura))
			{
				Jogador a = final[j];
				final[j] = final[j + 1];
				final[j + 1] = a;
			}
		}
	}

	for (size_t x = 0; x < 10; x++)
	{
		imprimir(&final[x]);
	}

	return 0;
}