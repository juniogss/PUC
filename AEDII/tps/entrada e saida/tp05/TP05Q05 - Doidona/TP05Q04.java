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

public class TP05Q04 {

    public static void main(String[] args) {

        Jogador[] players = getPlayers();
        String entrada = MyIO.readLine();
        Doidona doida = new Doidona();

        while (!entrada.contains("FIM")) {
            doida.Inserir(players[Integer.parseInt(entrada) == 223 ? 222 : Integer.parseInt(entrada)]);
            entrada = MyIO.readLine();
        }

        entrada = MyIO.readLine();

        while (!entrada.contains("FIM")) {
            System.out.print(entrada);
            MyIO.println(doida.search(entrada) ? " SIM" : " NAO");
            entrada = MyIO.readLine();
        }
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

class Jogador {

    private int id;
    private String nome;
    private int altura;
    private int peso;
    private String universidade;
    private int anoNascimento;
    private String cidadeNascimento;
    private String estadoNascimento;

    public Jogador() {
    }

    public Jogador(int id, String nome, int altura, int peso, String universidade, int anoNascimento,
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

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
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
    public Jogador elemento;
    public Celula prox;

    public Celula() {
        this(null);
    }

    public Celula(Jogador elemento) {
        this.elemento = elemento;
        this.prox = null;
    }
}

class No {
    public Jogador elemento;
    public No esq, dir;

    public No(Jogador elemento) {
        this(elemento, null, null);
    }

    public No(Jogador elemento, No esq, No dir) {
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
    }
}

class Lista {
    public Celula primeiro;
    public Celula ultimo;

    public Lista() {
        primeiro = new Celula();
        ultimo = primeiro;
    }

    public int tamanho() {
        int tamanho = 0;
        for (Celula i = primeiro; i != ultimo; i = i.prox, tamanho++)
            ;
        return tamanho;
    }

    public void inserirFim(Jogador j) {
        ultimo.prox = new Celula(j);
        ultimo = ultimo.prox;
    }

    public boolean pesquisar(String j) {
        boolean resp = false;
        for (Celula i = primeiro.prox; i != null; i = i.prox) {
            if (i.elemento.getNome().equals(j)) {
                resp = true;
                i = ultimo;
            }
        }
        return resp;
    }

    public void mostrar() {
        for (Celula i = primeiro.prox; i != null; i = i.prox) {
            System.out.println(i.elemento + " ");
        }
    }
}

class ArvoreBinaria {
    public No raiz;

    public ArvoreBinaria() {
        raiz = null;
    }

    public boolean pesquisar(String x) {
        System.out.print(" raiz");
        return pesquisar(x, raiz);
    }

    private boolean pesquisar(String x, No i) {
        boolean resp;
        if (i == null) {
            resp = false;
        } else if (x.compareTo(i.elemento.getNome()) == 0) {
            resp = true;
        } else if (x.compareTo(i.elemento.getNome()) < 0) {
            System.out.print(" esq");
            resp = pesquisar(x, i.esq);
        } else {
            System.out.print(" dir");
            resp = pesquisar(x, i.dir);
        }
        return resp;
    }

    public void inserir(Jogador j) {
        raiz = inserir(j, raiz);
    }

    private No inserir(Jogador j, No i) {
        if (i == null) {
            i = new No(j);
        } else if (j.getNome().compareTo(i.elemento.getNome()) < 0) {
            i.esq = inserir(j, i.esq);
        } else if (j.getNome().compareTo(i.elemento.getNome()) > 0) {
            i.dir = inserir(j, i.dir);
        } else {
            System.out.println("Erro ao inserir!");
        }
        return i;
    }
}

class HashReserva {
    Jogador tabela[];
    int m1, m2, m, reserva;

    public HashReserva() {
        this(11, 0);
    }

    public HashReserva(int m1, int m2) {
        this.m1 = m1;
        this.m2 = m2;
        this.m = m1 + m2;
        this.tabela = new Jogador[this.m];
        for (int i = 0; i < m; i++) {
            tabela[i] = null;
        }
        reserva = 0;
    }

    public int h(int altura) {
        return altura % m1;
    }

    public boolean inserir(Jogador elemento) {
        boolean resp = false;
        if (elemento != null) {
            int pos = h(elemento.getAltura());
            if (tabela[pos] == null) {
                tabela[pos] = elemento;
                resp = true;
            }
        }
        return resp;
    }

    public boolean pesquisar(String Player) {
        int pos = -1;
        for (int i = 0; i < m; i++) {
            if (tabela[i] != null && tabela[i].getNome().equals(Player)) {
                pos = i;
                i = m;
            }
        }
        return pos != -1;
    }
}

class HashRehash {
    Jogador tabela[];
    int m;

    public HashRehash() {
        this(9);
    }

    public HashRehash(int m) {
        this.m = m;
        this.tabela = new Jogador[this.m];
        for (int i = 0; i < m; i++) {
            tabela[i] = null;
        }
    }

    public int h(int altura) {
        return altura % m;
    }

    public int reh(int altura) {
        return ++altura % m;
    }

    public boolean inserir(Jogador elemento) {
        boolean resp = false;
        if (elemento != null) {
            int pos = h(elemento.getAltura());
            if (tabela[pos] == null) {
                tabela[pos] = elemento;
                resp = true;
            } else {
                pos = reh(elemento.getAltura());
                if (tabela[pos] == null) {
                    tabela[pos] = elemento;
                    resp = true;
                }
            }
        }
        return resp;
    }

    public boolean pesquisar(String Player) {
        int pos = -1;
        for (int i = 1; i < m; i++) {
            if (tabela[i] != null && tabela[i].getNome().equals(Player)) {
                pos = i;
                i = m;
            }
        }
        return pos != -1;
    }
}

class Doidona {
    HashReserva T1;
    HashRehash T2;
    Lista T3;
    ArvoreBinaria T4;

    public Doidona() {
        T1 = new HashReserva();
        T2 = new HashRehash();
        T3 = new Lista();
        T4 = new ArvoreBinaria();
    }

    public void Inserir(Jogador x) {
        boolean wasInserted = T1.inserir(x);
        if (!wasInserted) {
            int hashValue = x.getAltura() % 3;
            if (hashValue < 1) {
                T2.inserir(x);
            } else if (hashValue < 2) {
                T3.inserirFim(x);
            } else {
                T4.inserir(x);
            }
        }
    }

    public boolean search(String x) {
        return T1.pesquisar(x) || T2.pesquisar(x) || T3.pesquisar(x) || T4.pesquisar(x);
    }

}