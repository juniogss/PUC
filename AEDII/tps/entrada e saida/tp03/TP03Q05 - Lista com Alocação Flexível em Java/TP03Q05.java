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

class Lista {
    private Celula primeiro;
    private Celula ultimo;

    /**
     * Construtor da classe que cria uma lista sem Jogadores (somente no cabeca).
     */
    public Lista(Jogador jogador) {
        primeiro = new Celula(jogador);
        ultimo = primeiro;
    }

    /**
     * Insere um Jogador na primeira posicao da lista.
     * 
     * @param x Jogador a ser inserido.
     */
    public void inserirInicio(Jogador x) {
        Celula tmp = new Celula(x);
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;
        if (primeiro == ultimo) {
            ultimo = tmp;
        }
        tmp = null;
    }

    /**
     * Insere um Jogador na ultima posicao da lista.
     * 
     * @param x Jogador a ser inserido.
     */
    public void inserirFim(Jogador x) {
        ultimo.prox = new Celula(x);
        ultimo = ultimo.prox;
    }

    /**
     * Remove um Jogador da primeira posicao da lista.
     * 
     * @return resp Jogador a ser removido.
     * @throws Exception Se a lista nao contiver Jogadores.
     */
    public Jogador removerInicio() throws Exception {
        if (primeiro == ultimo) {
            throw new Exception("Erro ao remover (vazia)!");
        }

        Celula tmp = primeiro;
        primeiro = primeiro.prox;
        Jogador resp = primeiro.elemento;
        tmp.prox = null;
        tmp = null;
        return resp;
    }

    /**
     * Remove um Jogador da ultima posicao da lista.
     * 
     * @return resp Jogador a ser removido.
     * @throws Exception Se a lista nao contiver Jogadores.
     */
    public Jogador removerFim() throws Exception {
        if (primeiro == ultimo) {
            throw new Exception("Erro ao remover (vazia)!");
        }

        // Caminhar ate a penultima celula:
        Celula i;
        for (i = primeiro; i.prox != ultimo; i = i.prox)
            ;

        Jogador resp = ultimo.elemento;
        ultimo = i;
        i = ultimo.prox = null;

        return resp;
    }

    /**
     * Insere um Jogador em uma posicao especifica considerando que o primeiro
     * Jogador valido esta na posicao 0.
     * 
     * @param x   Jogador a ser inserido.
     * @param pos int posicao da insercao.
     * @throws Exception Se <code>posicao</code> invalida.
     */
    public void inserir(Jogador x, int pos) throws Exception {

        int tamanho = tamanho();

        if (pos < 0 || pos > tamanho) {
            throw new Exception("Erro ao inserir posicao (" + pos + " / tamanho = " + tamanho + ") invalida!");
        } else if (pos == 0) {
            inserirInicio(x);
        } else if (pos == tamanho) {
            inserirFim(x);
        } else {
            // Caminhar ate a posicao anterior a insercao
            Celula i = primeiro;
            for (int j = 0; j < pos; j++, i = i.prox)
                ;

            Celula tmp = new Celula(x);
            tmp.prox = i.prox;
            i.prox = tmp;
            tmp = i = null;
        }
    }

    /**
     * Remove um Jogador de uma posicao especifica da lista considerando que o
     * primeiro Jogador valido esta na posicao 0.
     * 
     * @param posicao Meio da remocao.
     * @return resp Jogador a ser removido.
     * @throws Exception Se <code>posicao</code> invalida.
     */
    public Jogador remover(int pos) throws Exception {
        Jogador resp;
        int tamanho = tamanho();

        if (primeiro == ultimo) {
            throw new Exception("Erro ao remover (vazia)!");

        } else if (pos < 0 || pos >= tamanho) {
            throw new Exception("Erro ao remover (posicao " + pos + " / " + tamanho + " invalida!");
        } else if (pos == 0) {
            resp = removerInicio();
        } else if (pos == tamanho - 1) {
            resp = removerFim();
        } else {
            // Caminhar ate a posicao anterior a insercao
            Celula i = primeiro;
            for (int j = 0; j < pos; j++, i = i.prox)
                ;

            Celula tmp = i.prox;
            resp = tmp.elemento;
            i.prox = tmp.prox;
            tmp.prox = null;
            i = tmp = null;
        }

        return resp;
    }

    /**
     * Mostra os elementos da lista separados por espacos.
     */
    public void mostrar() {
        int pos = 0;
        for (Celula i = primeiro.prox; i != null; i = i.prox) {
            MyIO.println(i.elemento.imprimir(pos));
            pos++;
        }
    }

    // /**
    // * Procura um elemento e retorna se ele existe.
    // *
    // * @param x Elemento a pesquisar.
    // * @return <code>true</code> se o elemento existir, <code>false</code> em caso
    // * contrario.
    // */
    // public boolean pesquisar(int x) {
    // boolean resp = false;
    // for (Celula i = primeiro.prox; i != null; i = i.prox) {
    // if (i.elemento == x) {
    // resp = true;
    // i = ultimo;
    // }
    // }
    // return resp;
    // }

    /**
     * Calcula e retorna o tamanho, em numero de elementos, da lista.
     * 
     * @return resp int tamanho
     */
    public int tamanho() {
        int tamanho = 0;
        for (Celula i = primeiro; i != ultimo; i = i.prox, tamanho++)
            ;
        return tamanho;
    }
}

public class TP03Q05 {

    public static void main(String[] args) throws Exception {
        Jogador[] players = getPlayers();
        String entrada = MyIO.readLine();
        Lista list = new Lista(players[Integer.parseInt(entrada) == 223 ? 222 : Integer.parseInt(entrada)]);

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
