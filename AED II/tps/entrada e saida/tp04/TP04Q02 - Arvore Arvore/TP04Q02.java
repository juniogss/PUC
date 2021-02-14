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

class No {
    No esq;
    No dir;
    No2 raiz;
    int elemento;

    No(int x) {
        this(x, null, null);
    }

    No(int x, No esq, No dir) {
        this.esq = esq;
        this.dir = dir;
        elemento = x;
    }
}

class No2 {
    No2 dir;
    No2 esq;
    Jogador elemento;

    No2(Jogador x) {
        this(x, null, null);
    }

    No2(Jogador x, No2 esq, No2 dir) {
        this.esq = esq;
        this.dir = dir;
        elemento = x;
    }
}

/**
 * Arvore de arvore
 * 
 * @author Max do Val Machado
 */
class ArvoreArvore {
    private No raiz; // Raiz da arvore.
    int cmp;

    /**
     * Construtor da classe.
     */
    public ArvoreArvore() {
        raiz = null;
        cmp = 0;
    }

    public boolean pesquisar(String x) {
        System.out.print(" raiz ");
        return pesquisar(x, raiz);
    }

    private boolean pesquisar(String x, No i) {
        boolean resp;
        if (i != null) {
            resp = pesquisar2(x, i.raiz);
            if (resp == false) {
                System.out.print("esq ");
                resp = pesquisar(x, i.esq);
            }
            if (resp == false) {
                System.out.print("dir ");
                resp = pesquisar(x, i.dir);
            }
        } else {
            resp = false;
        }

        return resp;
    }

    private boolean pesquisar2(String x, No2 no) {
        boolean resp;
        if (no != null) {
            cmp++;
            if (x.compareTo(no.elemento.getNome()) == 0) {
                resp = true;
            } else {
                cmp++;
                System.out.print("ESQ ");
                resp = pesquisar2(x, no.esq);
            }
            if (resp == false) {
                cmp++;
                System.out.print("DIR ");
                resp = pesquisar2(x, no.dir);
            }
        } else {
            resp = false;
        }

        return resp;
    }

    public void inserir(int x) throws Exception {
        raiz = inserir(x, raiz);
    }

    private No inserir(int x, No i) throws Exception {
        if (i == null) {
            i = new No(x);
        } else if (x < i.elemento) {
            i.esq = inserir(x, i.esq);
        } else if (x > i.elemento) {
            i.dir = inserir(x, i.dir);
        }

        return i;
    }

    public void inserir(Jogador x) throws Exception {
        raiz = inserir(raiz, x);
    }

    private No inserir(No i, Jogador x) throws Exception {

        if (i == null) {
            throw new Exception("Erro!");
        } else if (x.getAltura() % 15 == i.elemento) {
            i.raiz = inserir2(i.raiz, x);
        } else if (x.getAltura() % 15 < i.elemento) {
            i.esq = inserir(i.esq, x);
        } else {
            i.dir = inserir(i.dir, x);
        }
        return i;
    }

    private No2 inserir2(No2 i, Jogador x) throws Exception {

        if (i == null) {
            i = new No2(x);
        } else if (x.getNome().compareTo(i.elemento.getNome()) < 0) {
            i.esq = inserir2(i.esq, x);
        } else if (x.getNome().compareTo(i.elemento.getNome()) > 0) {
            i.dir = inserir2(i.dir, x);
        } else {
            throw new Exception("Erro!");
        }

        return i;
    }

}

public class TP04Q02 {

    public static void main(String[] args) throws Exception {

        Jogador[] players = getPlayers();
        String entrada = MyIO.readLine();

        ArvoreArvore arvore = new ArvoreArvore();
        arvore.inserir(7);
        arvore.inserir(3);
        arvore.inserir(11);
        arvore.inserir(1);
        arvore.inserir(5);
        arvore.inserir(9);
        arvore.inserir(12);
        arvore.inserir(0);
        arvore.inserir(2);
        arvore.inserir(4);
        arvore.inserir(6);
        arvore.inserir(8);
        arvore.inserir(10);
        arvore.inserir(12);
        arvore.inserir(13);

        while (!entrada.contains("FIM")) {
            arvore.inserir(players[Integer.parseInt(entrada) == 223 ? 222 : Integer.parseInt(entrada)]);
            entrada = MyIO.readLine();
        }

        entrada = MyIO.readLine();

        long comeco = System.currentTimeMillis();

        while (!entrada.contains("FIM")) {
            System.out.print(entrada);
            MyIO.println((arvore.pesquisar(entrada)) ? "SIM" : "NAO");
            entrada = MyIO.readLine();
        }

        long fim = System.currentTimeMillis();
        long tempo = fim - comeco;
        Arq.openWrite("633516_arvoreArvore.txt");
        Arq.print("633516\t" + tempo + "\t" + arvore.cmp);
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
