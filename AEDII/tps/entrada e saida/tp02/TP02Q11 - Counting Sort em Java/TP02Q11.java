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

class Coutin {

    private Jogador[] array;
    int n;

    /**
     * Construtor.
     *
     * @param tamanho do array de numeros inteiros.
     */
    public Coutin(int tamanho) {
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

    public int getMaior() {
        int maior = (int) array[0].getAltura();

        for (int i = 0; i < n; i++) {
            if (maior < array[i].getAltura()) {
                maior = (int) array[i].getAltura();
            } 
        }
        return maior;
    }

    private boolean getMaior(int a, int b) {
        if (array[a].getAltura() < array[b].getAltura())
            return true;
        else if (array[a].getAltura() > array[b].getAltura())
            return false;
        else
            return compareNome(array[a].getNome(), array[b].getNome()) == 1;
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

        // MyIO.println(a + " - " + b);

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
     * Algoritmo de ordenacao Countingsort.
     */
    public void countinOrdenado() {
        // Array para contar o numero de ocorrencias de cada elemento
        int[] count = new int[getMaior() + 1];
        Jogador[] ordenado = new Jogador[n];

        // Inicializar cada posicao do array de contagem
        for (int i = 0; i < count.length; count[i] = 0, i++)
            ;

        // Agora, o count[i] contem o numero de elemento iguais a i
        for (int i = 0; i < n; count[(int) array[i].getAltura()]++, i++)
            ;

        // Agora, o count[i] contem o numero de elemento menores ou iguais a i
        for (int i = 1; i < count.length; count[i] += count[i - 1], i++)
            ;

        // Ordenando
        for (int i = n - 1; i >= 0; ordenado[count[(int) array[i].getAltura()]
                - 1] = array[i], count[(int) array[i].getAltura()]--, i--)
            ;

        // Copiando para o array original
        for (int i = 0; i < n; array[i] = ordenado[i], i++)
            ;
    }
}

public class TP02Q11 {

    public static void main(String[] args) throws Exception {

        Jogador[] players = getPlayers();
        String entrada = MyIO.readLine();

        long comeco = new Date().getTime();
        Coutin coutingsort = new Coutin(10000);

        while (!entrada.contains("FIM")) {
            coutingsort.inserir(players[Integer.parseInt(entrada) == 223 ? 222 : Integer.parseInt(entrada)]);
            entrada = MyIO.readLine();
        }

        coutingsort.countinOrdenado();

        for (int x = 0; x < coutingsort.getN(); x++)
            coutingsort.imprimir(x);

        long fim = new Date().getTime();
        long tempo = fim - comeco;

        // criar arquivo com esse nome:
        File file = new File("633516_coutingsort.txt");

        // Se o arquivo nao existir, ele gera
        if (!file.exists())
            file.createNewFile();

        // Prepara para escrever no arquivo
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);

        // Escreve e fecha arquivo
        String arquivo = "633516" + "\t" + tempo;
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
