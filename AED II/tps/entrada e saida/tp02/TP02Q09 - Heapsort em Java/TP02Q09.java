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

class Heapsort {

    private Jogador[] array;
    int n;
    int comparacoes;
    int movimentacoes;

    /**
     * Construtor.
     *
     * @param tamanho do array de numeros inteiros.
     */
    public Heapsort(int tamanho) {
        array = new Jogador[tamanho];
        n = 0;
        comparacoes = 0;
        movimentacoes = 0;
    }

    public void inserir(Jogador jogador) {
        array[n] = jogador;
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

    public void imprimir(int x) {
        MyIO.println(array[x].imprimir());
    }

    public int getAltura(int x) {
        return array[x].getAltura();
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
        movimentacoes += 3;
    }

    public void constroi(int tamHeap) {
        for (int i = tamHeap; i > 1 && compare(i, i / 2) == 1; i /= 2) {
            swap(i, i / 2);
        }
    }

    private int compare(int a, int b) {
        if (getAltura(a) > getAltura(b))
            return 1;
        else if (getAltura(a) < getAltura(b))
            return -1;
        else
            return compareNome(array[a].getNome(), array[b].getNome());
    }

    public void reconstroi(int tamHeap) {
        int i = 1, filho;
        while (i <= (tamHeap / 2)) {

            if (compare(2 * i, 2 * i + 1) == 1 || 2 * i == tamHeap) {
                filho = 2 * i;
            } else {
                filho = 2 * i + 1;
            }

            if (compare(filho, i) == 1) {
                swap(i, filho);
                i = filho;
            } else {
                i = tamHeap;
            }
        }
    }

    /**
     * Algoritmo de ordenacao Heapsort.
     */
    public void heapsort() {
        // Alterar o vetor ignorando a posicao zero
        Jogador[] tmp = new Jogador[n + 1];
        for (int i = 0; i < n; i++) {
            tmp[i + 1] = array[i];
            movimentacoes++;
        }
        array = tmp;

        // Contrucao do heap
        for (int tamHeap = 2; tamHeap <= n; tamHeap++) {
            constroi(tamHeap);
        }

        int tamHeap = n;
        while (tamHeap > 1) {
            swap(1, tamHeap--);
            reconstroi(tamHeap);
        }

        // Alterar o vetor para voltar a posicao zero
        tmp = array;
        array = new Jogador[n];
        for (int i = 0; i < n; i++) {
            array[i] = tmp[i + 1];
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
    public int compareNome(String a, String b) {

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

}

public class TP02Q09 {

    public static void main(String[] args) throws Exception {

        Jogador[] players = getPlayers();
        String entrada = MyIO.readLine();

        long comeco = new Date().getTime();
        Heapsort heapsort = new Heapsort(10000);

        while (!entrada.contains("FIM")) {
            heapsort.inserir(players[Integer.parseInt(entrada) == 223 ? 222 : Integer.parseInt(entrada)]);
            entrada = MyIO.readLine();
        }

        heapsort.heapsort();

        for (int x = 0; x < heapsort.getN(); x++)
            heapsort.imprimir(x);

        long fim = new Date().getTime();
        long tempo = fim - comeco;

        // criar arquivo com esse nome:
        File file = new File("633516_heapsort.txt");

        // Se o arquivo nao existir, ele gera
        if (!file.exists())
            file.createNewFile();

        // Prepara para escrever no arquivo
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);

        // Escreve e fecha arquivo
        String arquivo = "633516" + "\t" + tempo + "\t" + heapsort.getMovimentacoes();
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
