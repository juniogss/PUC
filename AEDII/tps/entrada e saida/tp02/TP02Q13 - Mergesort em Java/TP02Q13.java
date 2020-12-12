import java.io.*;
import java.util.Formatter;
import java.util.Scanner;
import java.io.File;
import java.nio.charset.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.time.*;
import java.net.*;
import java.time.format.*;
import java.util.*;

class Jogador {

    private int id;
    private String nome;
    private int altura;
    private double peso;
    private String universidade;
    private int anoNascimento;
    private String cidadeNascimento;
    private String estadoNascimento;

    public Jogador() {
    }

    public Jogador(int id, String nome, int altura, double peso, String universidade, int anoNascimento,
            String cidadeNascimento, String estadoNascimento) {
        this.id = id;
        this.nome = nome;
        this.altura = altura;
        this.peso = peso;
        this.universidade = universidade;
        this.anoNascimento = anoNascimento;
        this.cidadeNascimento = cidadeNascimento;
        this.estadoNascimento = estadoNascimento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getUniversidade() {
        return universidade;
    }

    public void setUniversidade(String universidade) {
        this.universidade = universidade;
    }

    public int getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public String getCidadeNascimento() {
        return cidadeNascimento;
    }

    public void setCidadeNascimento(String cidadeNascimento) {
        this.cidadeNascimento = cidadeNascimento;
    }

    public String getEstadoNascimento() {
        return estadoNascimento;
    }

    public void setEstadoNascimento(String estadoNascimento) {
        this.estadoNascimento = estadoNascimento;
    }

    /**
     * Imprimir linha com valores das chaves
     */
    public String imprimir() {
        String print = "[" + getId() + " ## " + getNome() + " ## " + getAltura() + " ## "
                + String.valueOf(getPeso()).replace(".0", "") + " ## " + getAnoNascimento() + " ## " + getUniversidade()
                + " ## " + getCidadeNascimento() + " ## " + getEstadoNascimento() + "]";

        return print;
    }

    public void ler(String frase) {
        frase = frase.replace(",,", ",nao informado,");
        if (frase.charAt(frase.length() - 1) == ',')
            frase += "nao informado";

        String[] resultados = frase.split(",");
        setId(Integer.parseInt(resultados[0]));
        setNome(resultados[1]);
        setAltura(Integer.parseInt(resultados[2]));
        setPeso(Integer.parseInt(resultados[3]));
        setUniversidade(resultados[4]);
        setAnoNascimento(Integer.parseInt(resultados[5]));
        setCidadeNascimento(resultados[6]);
        setEstadoNascimento(resultados[7]);
    }
}

class Merge {

    private Jogador[] array;
    int n;
    int comparacoes;
    int movimentacoes;

    /**
     * Construtor.
     *
     * @param tamanho do array de numeros inteiros.
     */
    public Merge(int tamanho) {
        array = new Jogador[tamanho];
        n = 0;
        comparacoes = 0;
        movimentacoes = 0;
    }

    public void inserir(Jogador pres) {
        array[n] = pres;
        n++;
    }

    public int getN() {
        return n;
    }

    public int getComparacoes() {
        return comparacoes;
    }

    public int getMovimentacoes() {
        return movimentacoes;
    }

    /**
     * Troca o conteudo de duas posicoes do array
     *
     * @param i int primeira posicao
     * @param j int segunda posicao
     */
    public void swap(int i, int j) {
        Jogador temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        movimentacoes = movimentacoes + 3;
    }

    public void imprimir(int x) {
        MyIO.println(array[x].imprimir());
    }

    public void merge(int l, int m, int r) {
        // setar as duas divisorias do merge
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Criar arrays temporarios */
        Jogador L[] = new Jogador[n1];
        Jogador R[] = new Jogador[n2];

        // copiar datas para arrays
        for (int i = 0; i < n1; ++i) {
            L[i] = array[l + i];
            movimentacoes++;
        }

        for (int j = 0; j < n2; ++j) {
            R[j] = array[m + 1 + j];
            movimentacoes++;
        }

        // iniciar primeiro e segundo array
        int i = 0, j = 0;

        // iniciar local 2 array
        int k = l;
        while (i < n1 && j < n2) {
            // metodo .isBefore para saber qual data e maior
            if (compareUniversity(L[i], R[j]) == -1) {
                array[k] = L[i];
                i++;
                movimentacoes++;
                comparacoes++;
            } else {
                array[k] = R[j];
                j++;
                movimentacoes++;
                comparacoes++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = L[i];
            i++;
            k++;
            movimentacoes++;
        }

        // copiar itens restantes
        while (j < n2) {
            array[k] = R[j];
            j++;
            k++;
            movimentacoes++;
        }
    }

    /**
     * Comparar strings em ordem alfabetica
     *
     * @param a   primeira string
     * @param b   segunda string
     * @param pos posicao do caracter
     * @return 1 menor, -1 maior
     */
    public int compareText(String a, String b) {

        int len;

        if (a.length() >= b.length())
            len = b.length();
        else
            len = a.length();

        for (int i = 0; i < len; i++) {
            if (a.charAt(i) > b.charAt(i))
                return 1;
            else if (a.charAt(i) < b.charAt(i))
                return -1;
        }

        if (a.length() > b.length())
            return 1;
        else if (a.length() < b.length())
            return -1;

        return 0;
    }

    private int compareUniversity(Jogador a, Jogador b) {
        return compareText(a.getUniversidade(), b.getUniversidade()) != 0 ? 
        compareText(a.getUniversidade(), b.getUniversidade()) :
        compareText(a.getNome(), b.getNome());
    }

    // merge principal
    public void sort(int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            sort(l, m);
            sort(m + 1, r);
            merge(l, m, r);
        }
    }
}

public class TP02Q13 {

    public static void main(String[] args) throws Exception {

        Jogador[] players = getPlayers();
        String entrada = MyIO.readLine();

        long comeco = new Date().getTime();
        Merge merge = new Merge(10000);

        while (!entrada.contains("FIM")) {
            merge.inserir(players[Integer.parseInt(entrada) == 223 ? 222 : Integer.parseInt(entrada)]);
            entrada = MyIO.readLine();
        }

        merge.sort(0, merge.getN() - 1);

        for (int x = 0; x < merge.getN(); x++)
            merge.imprimir(x);

        long fim = new Date().getTime();
        long tempo = fim - comeco;

        // criar arquivo com esse nome:
        File file = new File("633516_mergesort.txt");

        // Se o arquivo nao existir, ele gera
        if (!file.exists())
            file.createNewFile();

        // Prepara para escrever no arquivo
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);

        // Escreve e fecha arquivo
        String arquivo = "633516" + "\t" + merge.getComparacoes() + "\t" + merge.getMovimentacoes() + "\t" + tempo;
        bw.write(arquivo);
        bw.close();
    }

    private static Jogador[] getPlayers() {
        Arq.openRead("/tmp/players.csv");
        String linha = Arq.readLine();

        Jogador[] players = new Jogador[8000];

        while (Arq.hasNext()) {
            Jogador player = new Jogador();
            player.ler(Arq.readLine());
            players[player.getId()] = player;
        }

        Arq.close();

        return players;
    }
}