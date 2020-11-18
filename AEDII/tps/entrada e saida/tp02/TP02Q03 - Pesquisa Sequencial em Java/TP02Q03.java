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

class Selecao {

    private Jogador[] players;
    int n;
    int contador;

    /**
     * Construtor.
     *
     * @param tamanho do array de numeros inteiros.
     */
    public Selecao(int tamanho) {
        players = new Jogador[tamanho];
        n = 0;
        contador = 0;
    }

    public void inserir(Jogador jogador) {
        players[n] = jogador;
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
        Jogador temp = players[i];
        players[i] = players[j];
        players[j] = temp;
    }

    /**
     * Algoritmo de ordenacao por selecao.
     */
    public boolean selecao(String nome) {
        boolean resp = false;
        for (int x = 0; x < n; x++)
            if (players[x].getNome().contains(nome)) {
                resp = true;
                contador++;
            }

        return resp;
    }

    public int getContador() {
        return contador;
    }

    public String imprimir(int x) {
        return players[x].imprimir();
    }
}

public class TP02Q03 {

    public static void main(String[] args) throws Exception {

        Jogador[] players = getPlayers();
        String entrada = MyIO.readLine();

        long comeco = new Date().getTime();
        Selecao selecao = new Selecao(200);

        while (!entrada.contains("FIM")) {
            selecao.inserir(players[Integer.parseInt(entrada)]);
            entrada = MyIO.readLine();
        }

        entrada = MyIO.readLine();

        while (!entrada.contains("FIM")) {
            MyIO.println(selecao.selecao(entrada) ? "SIM" : "NAO");
            entrada = MyIO.readLine();
        }

        long fim = new Date().getTime();
        long tempo = fim - comeco;

        // criar arquivo com esse nome:
        File file = new File("633516_sequencial.txt");

        // Se o arquivo nao existir, ele gera
        if (!file.exists())
            file.createNewFile();

        // Prepara para escrever no arquivo
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);

        // Escreve e fecha arquivo
        String arquivo = "633516" + "\t" + tempo + "\t" + selecao.getContador();
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
