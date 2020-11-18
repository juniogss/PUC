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
    public String imprimir(int pos) {
        String print = "[" + pos + "] ## " + getNome() + " ## " + getAltura() + " ## "
                + String.valueOf(getPeso()).replace(".0", "") + " ## " + getAnoNascimento() + " ## " + getUniversidade()
                + " ## " + getCidadeNascimento() + " ## " + getEstadoNascimento() + " ##";

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

class Lista {
    private Jogador[] array;
    private int n;

    /**
     * Construtor da classe.
     */
    public Lista() {
        this(200);
    }

    /**
     * Construtor da classe.
     * 
     * @param tamanho Tamanho da lista.
     */
    public Lista(int tamanho) {
        array = new Jogador[tamanho];
        n = 0;
    }

    /**
     * Insere um elemento na primeira posicao da lista e move os demais Jogadores
     * para o fim da lista.
     * 
     * @param x Jogador a ser inserido.
     * @throws Exception Se a lista estiver cheia.
     */
    public void inserirInicio(Jogador x) throws Exception {

        // validar insercao
        if (n >= array.length) {
            throw new Exception("Erro ao inserir!");
        }

        // levar Jogadores para o fim do array
        for (int i = n; i > 0; i--) {
            array[i] = array[i - 1];
        }

        array[0] = x;
        n++;
    }

    /**
     * Insere um Jogador na ultima posicao da lista.
     * 
     * @param x Jogador a ser inserido.
     * @throws Exception Se a lista estiver cheia.
     */
    public void inserirFim(Jogador x) throws Exception {

        // validar insercao
        if (n >= array.length) {
            throw new Exception("Erro ao inserir!");
        }

        array[n] = x;
        n++;
    }

    /**
     * Insere um elemento em uma posicao especifica e move os demais Jogadores para
     * o fim da lista.
     * 
     * @param x   Jogador a ser inserido.
     * @param pos Posicao de insercao.
     * @throws Exception Se a lista estiver cheia ou a posicao invalida.
     */
    public void inserir(Jogador x, int pos) throws Exception {

        // validar insercao
        if (n >= array.length || pos < 0 || pos > n) {
            throw new Exception("Erro ao inserir!");
        }

        // levar Jogadores para o fim do array
        for (int i = n; i > pos; i--) {
            array[i] = array[i - 1];
        }

        array[pos] = x;
        n++;
    }

    /**
     * Remove um Jogador da primeira posicao da lista e movimenta os demais
     * Jogadores para o inicio da mesma.
     * 
     * @return resp Jogador a ser removido.
     * @throws Exception Se a lista estiver vazia.
     */
    public Jogador removerInicio() throws Exception {

        // validar remocao
        if (n == 0) {
            throw new Exception("Erro ao remover!");
        }

        Jogador resp = array[0];
        n--;

        for (int i = 0; i < n; i++) {
            array[i] = array[i + 1];
        }

        return resp;
    }

    /**
     * Remove um Jogador da ultima posicao da lista.
     * 
     * @return resp Jogador a ser removido.
     * @throws Exception Se a lista estiver vazia.
     */
    public Jogador removerFim() throws Exception {

        // validar remocao
        if (n == 0) {
            throw new Exception("Erro ao remover!");
        }

        return array[--n];
    }

    /**
     * Remove um Jogador de uma posicao especifica da lista e movimenta os demais
     * Jogadores para o inicio da mesma.
     * 
     * @param pos Posicao de remocao.
     * @return resp Jogador a ser removido.
     * @throws Exception Se a lista estiver vazia ou a posicao for invalida.
     */
    public Jogador remover(int pos) throws Exception {

        // validar remocao
        if (n == 0 || pos < 0 || pos >= n) {
            throw new Exception("Erro ao remover!");
        }

        Jogador resp = array[pos];
        n--;

        for (int i = pos; i < n; i++) {
            array[i] = array[i + 1];
        }

        return resp;
    }

    /**
     * Mostra os elementos da lista separados por espacos.
     */
    public void mostrar() {
        for (int i = 0; i < n; i++)
            MyIO.println(array[i].imprimir(i));
    }

    /**
     * Procura um Jogador e retorna se ele existe.
     * 
     * @param x Jogador a ser pesquisado.
     * @return <code>true</code> se o array existir, <code>false</code> em caso
     *         contrario.
     */
    public boolean pesquisar(Jogador x) {
        boolean retorno = false;
        for (int i = 0; i < n && retorno == false; i++) {
            retorno = (array[i] == x);
        }
        return retorno;
    }
}

public class TP03Q01 {

    public static void main(String[] args) throws Exception {

        Jogador[] players = getPlayers();
        String entrada = MyIO.readLine();
        Lista list = new Lista();

        while (!entrada.contains("FIM")) {
            list.inserirFim(players[Integer.parseInt(entrada) == 223 ? 222 : Integer.parseInt(entrada)]);
            entrada = MyIO.readLine();
        }

        int n = Integer.parseInt(MyIO.readLine());

        for (int x = 0; x < n; x++) {
            String[] parts = MyIO.readLine().split(" ");

            switch (parts[0]) {
                case "II":
                    list.inserirInicio(players[Integer.parseInt(parts[1])]);
                    break;
                case "I*":
                    list.inserir(players[Integer.parseInt(parts[2])], Integer.parseInt(parts[1]));
                    break;
                case "IF":
                    list.inserirFim(players[Integer.parseInt(parts[1])]);
                    break;
                case "RI":
                    MyIO.println("(R) " + list.removerInicio().getNome());
                    break;
                case "R*":
                    MyIO.println("(R) " + list.remover(Integer.parseInt(parts[1])).getNome());
                    break;
                case "RF":
                    MyIO.println("(R) " + list.removerFim().getNome());
                    break;
                default:
                    break;
            }
        }

        list.mostrar();
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
