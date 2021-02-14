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

class QuickSort {

    private Jogador[] array;
    private int n;

    /**
     * Construtor.
     * 
     * @param tamanho do array de numeros inteiros.
     */
    public QuickSort(int tamanho) {
        array = new Jogador[tamanho];
        n = 0;
    }

    public void inserir(Jogador pres) {
        array[n] = pres;
        n++;
    }

    public int getN() {
        return n;
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
    }

    public void imprimir(int x) {
        MyIO.println(array[x].imprimir());
    }

    public void ordQuick() {
        quicksort(0, n - 1);
    }

    public String getEstado(int i) {
        return array[i].getEstadoNascimento();
    }

    public String getName(int i) {
        return array[i].getNome();
    }

    /**
     * Comparar strings em ordem alfabetica
     *
     * @param a   primeira string
     * @param b   segunda string
     * @param pos posicao do caracter
     * @return 1 menor, 2 maior
     */
    public int compareNome(String a, String b, int pos) {

        if (a.charAt(pos) == b.charAt(pos))
            if (a.length() == pos + 1 && b.length() == pos + 1)
                return -1;
            else if (a.length() == pos + 1)
                return -1;
            else if (b.length() == pos + 1)
                return 1;
            else
                compareNome(a, b, ++pos);
        else if (a.charAt(pos) < b.charAt(pos))
            return -1;
        else if (a.charAt(pos) > b.charAt(pos))
            return 1;

        return ((int) a.charAt(pos) - (int) b.charAt(pos));
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

    /**
     * Algoritmo de ordenacao Quicksort.
     * 
     * @param esq inicio do array a ser ordenado
     * @param dir fim do array a ser ordenado
     */
    private void quicksort(int esq, int dir) {
        int i = esq, j = dir;
        String pivo = getEstado((dir + esq) / 2);
        String pivoName = getName((dir + esq) / 2);
        while (i <= j) {
            while (compareText(getEstado(i), pivo) == -1
                    || compareText(getEstado(i), pivo) == 0 && compareText(getName(i), pivoName) == -1)
                i++;
            while (compareText(getEstado(j), pivo) == 1
                    || compareText(getEstado(j), pivo) == 0 && compareText(getName(j), pivoName) == 1)
                j--;
            if (i <= j) {
                swap(i, j);
                i++;
                j--;
            }
        }
        if (esq < j)
            quicksort(esq, j);
        if (i < dir)
            quicksort(i, dir);
    }
}

public class TP02Q18 {

    public static void main(String[] args) throws Exception {

        Jogador[] players = getPlayers();
        String entrada = MyIO.readLine();

        long comeco = new Date().getTime();
        QuickSort quick = new QuickSort(10000);

        while (!entrada.contains("FIM")) {
            quick.inserir(players[Integer.parseInt(entrada) == 223 ? 222 : Integer.parseInt(entrada)]);
            entrada = MyIO.readLine();
        }

        quick.ordQuick();

        for (int x = 0; x < 10; x++)
            quick.imprimir(x);
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
