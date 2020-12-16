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

class DoidonaComTADsProntas {
    final int TAMT1 = 100;
    final int TAMT3 = 100;
    final int NULO = -0x7FFFFF;

    int[] t1;
    int[] t3;

    ArvoreBinaria arvoreBinaria;
    ListaSimples lista;
    AVL arvoreAVL;

    public Doidona(){
       t1 = new int [TAMT1];
       t3 = new int [TAMT3];
 
       for(int i = 0; i < TAMT1; i++){
          t1[i] = NULO;
       }
       for(int i = 0; i < TAMT3; i++){
          t3[i] = NULO;
       }
 
       arvoreBinaria = new ArvoreBinaria();
       arvoreAVL = new AVL();
       lista = new ListaSimples();
    }

    public int hashT1(int elemento) {
    }

    public int hashT2(int elemento) {
        return elemento % 3;
    }

    public int hashT3(int elemento) {
    }

    public int rehashT3(int elemento) {
    }

    public void inserir(int elemento) {
        int i = hashT1(elemento);
        if (elemento == NULO) {
            // ????
        } else if (t1[i] == NULO) {
            t1[i] = elemento;
        } else if (hashT2(elemento) == 0) {
            i = hashT3(elemento);

            if (t3[i] == NULO) {
                t3[i] = elemento;
            } else {
                i = rehashT3(elemento);

                if (t3[i] == NULO) {
                    t3[i] = elemento;
                } else {
                    arvoreBinaria.inserir(elemento);
                }
            }
        } else if (hashT2(elemento) == 1) {
            lista.inserirFim(elemento);
        } else if (hashT2(elemento) == 2) {
            arvoreAVL.inserir(elemento);
        } else {
            System.out.println("ERRO!!!!");
        }
    }

    void remover(int valor) {
    }

    boolean pesquisar(int valor) {
        boolean resp = false;
        int pos = hashT1(valor);
        if (t1[pos] == valor) {
            resp = true;
        } else {
            pos = hashT2(valor);
            if (pos == 0) {
                pos = hashT3(valor);
                if (t3[pos] == valor) {
                    resp = true;
                } else {
                    pos = rehashT3(valor);
                    if (t3[pos] == valor) {
                        resp = true;
                    } else {
                        resp = arvoreBinaria.pesquisar(valor);
                    }
                }
            } else if (pos == 1) {
                resp = lista.pesquisar(valor);
            } else {
                resp = arvoreAVL.pesquisar(valor);
            }
        }
        return resp;
    }

    void mostrar() {
        for (int i = 0; i < TAMT1; i++) {
            if (t1[i] != NULO) {
                System.out.println(t1[i]);
            }
        }
        for (int i = 0; i < TAMT3; i++) {
            if (t3[i] != NULO) {
                System.out.println(t3[i]);
            }
        }
        arvoreBinaria.mostrar();
        lista.mostrar();
        arvoreAVL.mostrar();
    }
}

public class TP05Q04 {

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
