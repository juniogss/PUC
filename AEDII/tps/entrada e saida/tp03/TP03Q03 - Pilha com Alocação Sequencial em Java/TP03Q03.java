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

class Celula {
    public Jogador elemento; // Elemento inserido na celula.
    public Celula prox; // Aponta a celula prox.

    /**
     * Construtor da classe.
     * 
     * @param elemento Jogador inserido na celula.
     */
    public Celula(Jogador elemento) {
        this.elemento = elemento;
        this.prox = null;
    }
}

class Pilha {
    private Celula topo;
    public int size = 0;

    /**
     * Construtor da classe que cria uma fila sem elementos.
     */
    public Pilha() {
        topo = null;
    }

    /**
     * Insere Jogador na pilha (politica FILO).
     * 
     * @param x Jogador a inserir.
     */
    public void inserir(Jogador x) {
        Celula tmp = new Celula(x);
        tmp.prox = topo;
        topo = tmp;
        tmp = null;
        size++;
    }

    /**
     * Remove Jogador da pilha (politica FILO).
     * 
     * @return Jogador removido.
     * @trhows Exception Se a sequencia nao contiver Jogadores.
     */
    public Jogador remover() throws Exception {
        if (topo == null) {
            throw new Exception("Erro ao remover!");
        }
        Jogador resp = topo.elemento;
        Celula tmp = topo;
        topo = topo.prox;
        tmp.prox = null;
        tmp = null;
        size--;
        return resp;
    }

    public void mostraPilha() {
        mostraPilha(topo, size);
    }

    private void mostraPilha(Celula i, int pos) {
        if (i != null) {
            mostraPilha(i.prox, --pos);
            MyIO.println(i.elemento.imprimir(pos));
        }
    }
}

public class TP03Q03 {

    public static void main(String[] args) throws Exception {

        Jogador[] players = getPlayers();
        String entrada = MyIO.readLine();
        Pilha pilha = new Pilha();

        while (!entrada.contains("FIM")) {
            pilha.inserir(players[Integer.parseInt(entrada) == 223 ? 222 : Integer.parseInt(entrada)]);
            entrada = MyIO.readLine();
        }

        int n = Integer.parseInt(MyIO.readLine());

        for (int x = 0; x < n; x++) {
            String[] parts = MyIO.readLine().split(" ");

            switch (parts[0]) {
                case "I":
                    pilha.inserir(players[Integer.parseInt(parts[1])]);
                    break;
                case "R":
                    MyIO.println("(R) " + pilha.remover().getNome());
                    break;
                default:
                    MyIO.println("(R) " + pilha.remover().getNome());
                    break;
            }
        }

        pilha.mostraPilha();
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
