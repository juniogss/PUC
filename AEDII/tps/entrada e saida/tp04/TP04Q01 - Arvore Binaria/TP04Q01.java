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

/**
 * No da arvore binaria
 * 
 * @author Max do Val Machado
 */
class No {
    public Jogador elemento; // Conteudo do no.
    public No esq, dir; // Filhos da esq e dir.

    /**
     * Construtor da classe.
     * 
     * @param elemento Conteudo do no.
     */
    public No(Jogador elemento) {
        this(elemento, null, null);
    }

    /**
     * Construtor da classe.
     * 
     * @param elemento Conteudo do no.
     * @param esq      No da esquerda.
     * @param dir      No da direita.
     */
    public No(Jogador elemento, No esq, No dir) {
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
    }
}

/**
 * Arvore binaria de pesquisa
 * 
 * @author Max do Val Machado
 */
class ArvoreBinaria {
    private No raiz; // Raiz da arvore.
    int cmp;

    /**
     * Construtor da classe.
     */
    public ArvoreBinaria() {
        raiz = null;
        cmp = 0;
    }

    /**
     * Metodo publico iterativo para pesquisar elemento.
     * 
     * @param x Elemento que sera procurado.
     * @return <code>true</code> se o elemento existir, <code>false</code> em caso
     *         contrario.
     */
    public boolean pesquisar(String x) {
        MyIO.print(" raiz ");
        return pesquisar(x, raiz);
    }

    /**
     * Metodo privado recursivo para pesquisar elemento.
     * 
     * @param x Elemento que sera procurado.
     * @param i No em analise.
     * @return <code>true</code> se o elemento existir, <code>false</code> em caso
     *         contrario.
     */
    private boolean pesquisar(String nome, No i) {
        boolean resp;
        cmp++;

        if (i == null) {
            resp = false;

        } // vai pra direita lexicamente eh maior
        else if (nome.compareTo(i.elemento.getNome()) > 0) {
            cmp++;
            MyIO.print("dir ");
            resp = pesquisar(nome, i.dir);
        }
        // vai pra esq lexicamente eh menor
        else if (nome.compareTo(i.elemento.getNome()) < 0) {
            cmp++;
            MyIO.print("esq ");
            resp = pesquisar(nome, i.esq);
        }
        // encontrou
        else {
            resp = true;
        }
        return resp;
    }

    /**
     * Metodo publico iterativo para exibir elementos.
     */
    public void caminharCentral() {
        System.out.print("[ ");
        caminharCentral(raiz);
        System.out.println("]");
    }

    /**
     * Metodo privado recursivo para exibir elementos.
     * 
     * @param i No em analise.
     */
    private void caminharCentral(No i) {
        if (i != null) {
            caminharCentral(i.esq); // Elementos da esquerda.
            System.out.print(i.elemento + " "); // Conteudo do no.
            caminharCentral(i.dir); // Elementos da direita.
        }
    }

    /**
     * Metodo publico iterativo para exibir elementos.
     */
    public void caminharPre() {
        System.out.print("[ ");
        caminharPre(raiz);
        System.out.println("]");
    }

    /**
     * Metodo privado recursivo para exibir elementos.
     * 
     * @param i No em analise.
     */
    private void caminharPre(No i) {
        if (i != null) {
            System.out.print(i.elemento + " "); // Conteudo do no.
            caminharPre(i.esq); // Elementos da esquerda.
            caminharPre(i.dir); // Elementos da direita.
        }
    }

    /**
     * Metodo publico iterativo para exibir elementos.
     */
    public void caminharPos() {
        System.out.print("[ ");
        caminharPos(raiz);
        System.out.println("]");
    }

    /**
     * Metodo privado recursivo para exibir elementos.
     * 
     * @param i No em analise.
     */
    private void caminharPos(No i) {
        if (i != null) {
            caminharPos(i.esq); // Elementos da esquerda.
            caminharPos(i.dir); // Elementos da direita.
            System.out.print(i.elemento + " "); // Conteudo do no.
        }
    }

    /**
     * Metodo publico iterativo para inserir elemento.
     * 
     * @param x Elemento a ser inserido.
     * @throws Exception Se o elemento existir.
     */
    public void inserir(Jogador x) throws Exception {
        raiz = inserir(x, raiz);
    }

    /**
     * Metodo privado recursivo para inserir elemento.
     * 
     * @param x Elemento a ser inserido.
     * @param i No em analise.
     * @return No em analise, alterado ou nao.
     * @throws Exception Se o elemento existir.
     */
    private No inserir(Jogador x, No i) throws Exception {
        if (i == null) {
            i = new No(x);

        } // vai pra direita lexicamente eh maior
        else if (x.getNome().compareTo(i.elemento.getNome()) > 0) {
            i.dir = inserir(x, i.dir);
        }
        // vai pra esq lexicamente eh menor
        else if (x.getNome().compareTo(i.elemento.getNome()) < 0) {
            i.esq = inserir(x, i.esq);
        }
        return i;
    }

    /**
     * Metodo publico para inserir elemento.
     * 
     * @param x Elemento a ser inserido.
     * @throws Exception Se o elemento existir.
     */
    public void inserirPai(Jogador x) throws Exception {
        if (raiz == null) {
            raiz = new No(x);
        } else if (x.getNome().compareTo(raiz.elemento.getNome()) > 0) {
            inserirPai(x, raiz.esq, raiz);
        } else if (x.getNome().compareTo(raiz.elemento.getNome()) < 0) {
            inserirPai(x, raiz.dir, raiz);
        } else {
            throw new Exception("Erro ao inserirPai!");
        }
    }

    /**
     * Metodo privado recursivo para inserirPai elemento.
     * 
     * @param x   Elemento a ser inserido.
     * @param i   No em analise.
     * @param pai No superior ao em analise.
     * @throws Exception Se o elemento existir.
     */
    private void inserirPai(Jogador x, No i, No pai) throws Exception {
        if (i == null) {
            if (x.getNome().compareTo(i.elemento.getNome()) > 0) {
                pai.esq = new No(x);
            } else {
                pai.dir = new No(x);
            }
        }

        else if (x.getNome().compareTo(i.elemento.getNome()) > 0) {
            inserirPai(x, i.esq, i);
        }
        // vai pra esq lexicamente eh menor
        else if (x.getNome().compareTo(i.elemento.getNome()) < 0) {
            inserirPai(x, i.dir, i);
        } else
            throw new Exception("Erro ao inserirPai!");

    }

    /**
     * Metodo publico iterativo para remover elemento.
     * 
     * @param x Elemento a ser removido.
     * @throws Exception Se nao encontrar elemento.
     */
    public void remover(Jogador x) throws Exception {
        raiz = remover(x, raiz);
    }

    /**
     * Metodo privado recursivo para remover elemento.
     * 
     * @param x Elemento a ser removido.
     * @param i No em analise.
     * @return No em analise, alterado ou nao.
     * @throws Exception Se nao encontrar elemento.
     */
    private No remover(Jogador x, No i) throws Exception {

        if (i == null) {
            throw new Exception("Erro ao remover!");

        } else if (x.getNome().compareTo(i.elemento.getNome()) > 0) {
            i.esq = remover(x, i.esq);

        } else if (x.getNome().compareTo(i.elemento.getNome()) < 0) {
            i.dir = remover(x, i.dir);

            // Sem no a direita.
        } else if (i.dir == null) {
            i = i.esq;

            // Sem no a esquerda.
        } else if (i.esq == null) {
            i = i.dir;

            // No a esquerda e no a direita.
        } else {
            i.esq = antecessor(i, i.esq);
        }

        return i;
    }

    /**
     * Metodo para trocar no removido pelo antecessor.
     * 
     * @param i No que teve o elemento removido.
     * @param j No da subarvore esquerda.
     * @return No em analise, alterado ou nao.
     */
    private No antecessor(No i, No j) {

        // Existe no a direita.
        if (j.dir != null) {
            // Caminha para direita.
            j.dir = antecessor(i, j.dir);

            // Encontrou o maximo da subarvore esquerda.
        } else {
            i.elemento = j.elemento; // Substitui i por j.
            j = j.esq; // Substitui j por j.ESQ.
        }
        return j;
    }

    /**
     * Metodo publico iterativo para remover elemento.
     * 
     * @param x Elemento a ser removido.
     * @throws Exception Se nao encontrar elemento.
     */
    public void remover2(Jogador x) throws Exception {
        if (raiz == null) {
            throw new Exception("Erro ao remover2!");
        } else if (x.getNome().compareTo(raiz.elemento.getNome()) > 0) {
            remover2(x, raiz.esq, raiz);
        } else if (x.getNome().compareTo(raiz.elemento.getNome()) < 0) {
            remover2(x, raiz.dir, raiz);
        } else if (raiz.dir == null) {
            raiz = raiz.esq;
        } else if (raiz.esq == null) {
            raiz = raiz.dir;
        } else {
            raiz.esq = antecessor(raiz, raiz.esq);
        }
    }

    /**
     * Metodo privado recursivo para remover elemento.
     * 
     * @param x   Elemento a ser removido.
     * @param i   No em analise.
     * @param pai do No em analise.
     * @throws Exception Se nao encontrar elemento.
     */
    private void remover2(Jogador x, No i, No pai) throws Exception {
        if (i == null) {
            throw new Exception("Erro ao remover2!");
        } else if (x.getNome().compareTo(i.elemento.getNome()) > 0) {
            remover2(x, i.esq, i);
        } else if (x.getNome().compareTo(i.elemento.getNome()) < 0) {
            remover2(x, i.dir, i);
        } else if (i.dir == null) {
            pai = i.esq;
        } else if (i.esq == null) {
            pai = i.dir;
        } else {
            i.esq = antecessor(i, i.esq);
        }
    }

    public Jogador getRaiz() throws Exception {
        return raiz.elemento;
    }

    public static boolean igual(ArvoreBinaria a1, ArvoreBinaria a2) {
        return igual(a1.raiz, a2.raiz);
    }

    private static boolean igual(No i1, No i2) {
        boolean resp;
        if (i1 != null && i2 != null) {
            resp = (i1.elemento == i2.elemento) && igual(i1.esq, i2.esq) && igual(i1.dir, i2.dir);
        } else if (i1 == null && i2 == null) {
            resp = true;
        } else {
            resp = false;
        }
        return resp;
    }

    // public int soma() {
    // return soma(raiz);
    // }

    // public int soma(No i) {
    // int resp = 0;
    // if (i != null) {
    // resp = i.elemento + soma(i.esq) + soma(i.dir);
    // }
    // return resp;
    // }

    // public int quantidadePares() {
    // return quantidadePares(raiz);
    // }

    // public int quantidadePares(No i) {
    // int resp = 0;
    // if (i != null) {
    // resp = ((i.elemento % 2 == 0) ? 1 : 0) + quantidadePares(i.esq) +
    // quantidadePares(i.dir);
    // }
    // return resp;
    // }

    // public boolean hasDiv11() {
    // return hasDiv11(raiz);
    // }

    // public boolean hasDiv11(No i) {
    // boolean resp = false;
    // if (i != null) {
    // resp = (i.elemento % 11 == 0) || hasDiv11(i.esq) || hasDiv11(i.dir);
    // }
    // return resp;
    // }
}

public class TP04Q01 {

    public static void main(String[] args) throws Exception {

        Jogador[] players = getPlayers();
        String entrada = MyIO.readLine();
        ArvoreBinaria arvore = new ArvoreBinaria();

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
        Arq.openWrite("633516_arvoreBinaria.txt");
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
