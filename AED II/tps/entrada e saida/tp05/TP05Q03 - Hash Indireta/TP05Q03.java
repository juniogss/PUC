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

class HashLista {
    private int tamTab;
    private Lista[] tabela;
    public int cmp;

    public HashLista(int tamTab) {
        this.tamTab = tamTab;
        this.tabela = new Lista[tamTab];
        for (int i = 0; i < tamTab; i++) {
            tabela[i] = new Lista();
        }
    }

    private int transformacao(int altura) {
        return altura % tamTab;
    }

    public void inserir(Jogador x) {
        int pos = transformacao(x.getAltura());
        tabela[pos].inserirInicio(x);
    }

    public boolean pesquisar(String nome) {
        boolean resp = false;
        for (int i = 0; i < tamTab; i++) {
            cmp++;
            resp = tabela[i].pesquisar(nome);
            if (resp)
                i = tamTab;
        }
        return resp;
    }
}

class Lista {
    private Celula primeiro, ultimo;

    Lista() {
        primeiro = new Celula();// no cabeca
        ultimo = primeiro;
    }

    public boolean isVazio() {
        boolean resp = false;
        if (ultimo == primeiro)
            resp = true;
        return resp;
    }

    public void inserirInicio(Jogador x) {
        Celula tmp = new Celula(x);
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;
        if (isVazio()) {
            ultimo = tmp;
        }
        tmp = null;
    }

    public Jogador removerInicio() {
        Jogador resp = primeiro.prox.elemento;
        Celula tmp = primeiro.prox;
        primeiro.prox = primeiro.prox.prox;
        tmp = tmp.prox = null;
        return resp;
    }

    public void inserirFim(Jogador x) {
        Celula tmp = new Celula(x);
        ultimo.prox = tmp;
        ultimo = tmp;
        tmp = null;
    }

    public Jogador removerFim() {
        Jogador resp = ultimo.elemento;
        Celula tmp = ultimo;
        ultimo = menosmenos(ultimo);
        tmp = ultimo.prox = null;
        return resp;
    }

    public void inserir(Jogador x, int pos) {
        int tam = tamanho();
        if (pos == 0)
            inserirInicio(x);
        else if (pos == tam)
            inserirFim(x);
        else {
            Celula i;
            int c = 0;
            for (i = primeiro; c < pos; c++, i = i.prox)
                ;
            Celula tmp = new Celula(x);
            tmp.prox = i.prox;
            i.prox = tmp;
            tmp = i = null;
        }
    }

    public Jogador remover(int pos) {
        int tam = tamanho();
        Jogador resp;
        if (pos == 0)
            resp = removerInicio();
        else if (pos == tam - 1) {
            resp = removerFim();
        } else {
            Celula i;
            int c = 0;
            for (i = primeiro; c < pos; c++, i = i.prox)
                ;
            resp = i.prox.elemento;
            Celula tmp = i.prox;
            i.prox = i.prox.prox;
            i = tmp = tmp.prox = null;
        }
        return resp;
    }

    public int tamanho() {
        int tam = 0;
        for (Celula i = primeiro.prox; i != null; tam++, i = i.prox)
            ;
        return tam;
    }

    public Celula menosmenos(Celula i) {
        Celula j = primeiro;
        for (; j.prox != i; j = j.prox)
            ;
        return j;
    }

    public boolean pesquisar(String nome) {
        boolean resp = false;
        for (Celula i = primeiro.prox; i != null; i = i.prox) {
            if (i.elemento.getNome().equals(nome)) {
                resp = true;
            }
        }
        return resp;
    }
}

class Celula {
    Celula prox;
    Jogador elemento;

    Celula() {
    }

    Celula(Jogador x) {
        elemento = x;
        x = null;
    }
}

public class TP05Q03 {

    public static void main(String[] args) throws Exception {

        Jogador[] players = getPlayers();
        String entrada = MyIO.readLine();
        HashLista hash = new HashLista(25);

        long comeco = System.currentTimeMillis();

        while (!entrada.contains("FIM")) {
            hash.inserir(players[Integer.parseInt(entrada) == 223 ? 222 : Integer.parseInt(entrada)]);
            entrada = MyIO.readLine();
        }

        entrada = MyIO.readLine();

        while (!entrada.contains("FIM")) {
            System.out.print(entrada);
            MyIO.println((hash.pesquisar(entrada)) ? " SIM" : " NAO");
            entrada = MyIO.readLine();
        }

        long fim = System.currentTimeMillis();
        long tempo = fim - comeco;
        Arq.openWrite("633516_hashIndireta.txt");
        Arq.print("633516\t" + tempo + "\t" + hash.cmp);
        Arq.close();

    }

    private static Jogador[] getPlayers() {
        Arq.openRead("/tmp/players.csv");
        Arq.readLine();

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
