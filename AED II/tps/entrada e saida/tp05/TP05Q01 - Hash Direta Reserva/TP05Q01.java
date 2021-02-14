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

class Hash {
    private int tamTab;
    private int tamReserva;
    private int reserva;
    private Jogador[] tabela;
    public int cmp;

    public Hash(int tamTab, int tamReserva) {
        this.reserva = 0;
        this.tamReserva = tamReserva;
        this.tamTab = tamTab;
        this.tabela = new Jogador[tamReserva + tamTab];
    }

    private int transformacao(int id) {
        return id % tamTab;
    }

    public void inserir(Jogador x) {
        int pos = transformacao(x.getAltura());
        cmp++;
        if (tabela[pos] == null) {
            tabela[pos] = x;
        } else if (reserva < tamReserva) {
            tabela[tamTab + reserva] = x;
            reserva++;
        }
    }

    // public void show() {
    // for (int i = 0; i < tamTab + tamReserva; i++) {
    // if (tabela[i] != null)
    // MyIO.println(tabela[i].imprimir(i));
    // }
    // }

    public boolean pesquisar(String nome) {
        boolean resp = false;
        for (int i = 0; i < tamTab + tamReserva; i++) {
            cmp++;
            if (tabela[i] != null && tabela[i].getNome().equals(nome)) {
                resp = true;
                i = tamTab + tamReserva;
            }
        }
        return resp;
    }
}

public class TP05Q01 {

    public static void main(String[] args) throws Exception {

        Jogador[] players = getPlayers();
        String entrada = MyIO.readLine();
        Hash hash = new Hash(21, 9);

        long comeco = System.currentTimeMillis();

        while (!entrada.contains("FIM")) {
            hash.inserir(players[Integer.parseInt(entrada) == 223 ? 222 : Integer.parseInt(entrada)]);
            entrada = MyIO.readLine();
        }

        entrada = MyIO.readLine();

        // hash.show();

        while (!entrada.contains("FIM")) {
            System.out.print(entrada);
            MyIO.println((hash.pesquisar(entrada)) ? " SIM" : " NAO");
            entrada = MyIO.readLine();
        }

        long fim = System.currentTimeMillis();
        long tempo = fim - comeco;
        Arq.openWrite("633516_hashReserva.txt");
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
