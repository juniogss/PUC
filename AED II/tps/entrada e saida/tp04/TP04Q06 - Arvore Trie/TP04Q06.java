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

class Trie {
    int cmp;
    No raiz;

    public Trie() {
        raiz = new No((char) 0, false);
    }

    public void inserir(String s) {
        raiz.array[No.hash(s.charAt(0))] = inserir(raiz.array[No.hash(s.charAt(0))], s, 0);
    }

    private No inserir(No i, String s, int n) {
        if (i == null) {
            i = new No(s.charAt(n), (n == s.length() - 1) ? true : false);
        }
        if (!i.folha && n != s.length() - 1) {
            i.array[No.hash(s.charAt(n + 1))] = inserir(i.array[No.hash(s.charAt(n + 1))], s, n + 1);
        }
        return i;
    }

    public boolean pesquisar(String s) {
        return pesquisar(raiz.array[No.hash(s.charAt(0))], s, 0);
    }

    private boolean pesquisar(No i, String s, int n) {
        boolean resp = false;
        if (i != null) {
            cmp++;
            if (!i.folha && n != s.length() - 1) {
                resp = pesquisar(i.array[No.hash(s.charAt(n + 1))], s, n + 1);
            } else if (i.folha && n == s.length() - 1) {
                resp = true;

            }
        }
        return resp;
    }

    public static Trie percorre(No raiz, Trie arv) {
        return percorre(raiz, "", arv);
    }

    private static Trie percorre(No i, String s, Trie arv) {
        if (!i.folha) {
            for (int k = 0; k < i.tam; k++) {
                if (i.array[k] != null) {
                    arv = percorre(i.array[k], s + i.letra, arv);
                }
            }
        } else {
            s += i.letra;
            arv.inserir(s.substring(1, s.length()));
        }
        return arv;
    }

    public static Trie merge(Trie arv1, Trie arv2) {
        Trie arv3 = new Trie();
        arv3 = percorre(arv1.raiz, arv3);
        arv3 = percorre(arv2.raiz, arv3);
        return arv3;
    }

    public void mostrar() {
        mostrar(raiz, "");
    }

    private void mostrar(No i, String s) {
        if (!i.folha) {
            for (int k = 0; k < i.tam; k++) {
                if (i.array[k] != null) {
                    mostrar(i.array[k], s + i.letra);
                }
            }
        } else {

            System.out.println(s + i.letra);
        }
    }
}

class No {
    char letra;
    No[] array;
    boolean folha;
    int tam = 256;

    public No(char letra, boolean folha) {
        this.letra = letra;
        this.folha = folha;
        array = new No[tam];
        for (int i = 0; i < tam; i++) {
            array[i] = null;
        }
    }

    public static int hash(char letra) {
        return (int) letra;
    }
}

public class TP04Q06 {

    public static void main(String[] args) throws Exception {

        Jogador[] players = getPlayers();
        String entrada = MyIO.readLine();

        Trie trie1 = new Trie();

        while (!entrada.contains("FIM")) {
            Jogador jogador = players[Integer.parseInt(entrada) == 223 ? 222 : Integer.parseInt(entrada)];
            trie1.inserir(jogador.getNome());

            MyIO.println(entrada + "\t" + jogador.getNome());
            entrada = MyIO.readLine();
        }

        entrada = MyIO.readLine();

        Trie trie2 = new Trie();

        while (!entrada.contains("FIM")) {
            Jogador jogador = players[Integer.parseInt(entrada) == 223 ? 222 : Integer.parseInt(entrada)];
            trie2.inserir(jogador.getNome());

            MyIO.println(entrada + "\t" + jogador.getNome());
            entrada = MyIO.readLine();
        }
        entrada = MyIO.readLine();

        Trie trie3 = Trie.merge(trie1, trie2);
        long comeco = System.currentTimeMillis();

        while (!entrada.contains("FIM")) {
            MyIO.println((trie3.pesquisar(entrada)) ? entrada + " SIM" : entrada + " NAO");
            entrada = MyIO.readLine();
        }

        long fim = System.currentTimeMillis();
        long tempo = fim - comeco;
        Arq.openWrite("633516_arvoreTrie.txt");
        Arq.print("633516\t" + tempo + "\t" + trie1.cmp);
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
