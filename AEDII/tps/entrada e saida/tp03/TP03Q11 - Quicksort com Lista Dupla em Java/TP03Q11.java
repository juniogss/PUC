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
    public String imprimir() {
        String print = "[" + getId() + " ## " + getNome() + " ## " + getAltura() + " ## "
                + String.valueOf(getPeso()).replace(".0", "") + " ## " + getAnoNascimento() + " ## " + getUniversidade()
                + " ## " + getCidadeNascimento() + " ## " + getEstadoNascimento() + "]";

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

class CelulaDupla {
    public Jogador elemento;
    public CelulaDupla ant;
    public CelulaDupla prox;

    /**
     * Construtor da classe.
     * 
     * @param elemento int inserido na celula.
     */
    public CelulaDupla(Jogador elemento) {
        this.elemento = elemento;
        this.ant = this.prox = null;
    }
}

class ListaDupla {
    private CelulaDupla primeiro;
    private CelulaDupla ultimo;

    /**
     * Construtor da classe que cria uma lista dupla sem elementos (somente no
     * cabeca).
     */
    public ListaDupla(CelulaDupla celulaDupla) {
        primeiro = celulaDupla;
        ultimo = primeiro;
    }

    /**
     * Insere um elemento na primeira posicao da lista.
     * 
     * @param x Jogador elemento a ser inserido.
     */
    public void inserirInicio(Jogador x) {
        CelulaDupla tmp = new CelulaDupla(x);

        tmp.ant = primeiro;
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;
        if (primeiro == ultimo) {
            ultimo = tmp;
        } else {
            tmp.prox.ant = tmp;
        }
        tmp = null;
    }

    /**
     * Insere um elemento na ultima posicao da lista.
     * 
     * @param x Jogador a ser inserido.
     */
    public void inserirFim(Jogador x) {
        ultimo.prox = new CelulaDupla(x);
        ultimo.prox.ant = ultimo;
        ultimo = ultimo.prox;
    }

    /**
     * Remove um elemento da primeira posicao da lista.
     * 
     * @return resp int elemento a ser removido.
     * @throws Exception Se a lista nao contiver elementos.
     */
    public Jogador removerInicio() throws Exception {
        if (primeiro == ultimo) {
            throw new Exception("Erro ao remover (vazia)!");
        }

        CelulaDupla tmp = primeiro;
        primeiro = primeiro.prox;
        Jogador resp = primeiro.elemento;
        tmp.prox = primeiro.ant = null;
        tmp = null;
        return resp;
    }

    /**
     * Remove um elemento da ultima posicao da lista.
     * 
     * @return resp Jogador a ser removido.
     * @throws Exception Se a lista nao contiver elementos.
     */
    public Jogador removerFim() throws Exception {
        if (primeiro == ultimo) {
            throw new Exception("Erro ao remover (vazia)!");
        }
        Jogador resp = ultimo.elemento;
        ultimo = ultimo.ant;
        ultimo.prox.ant = null;
        ultimo.prox = null;
        return resp;
    }

    /**
     * Insere um elemento em uma posicao especifica considerando que o primeiro
     * elemento valido esta na posicao 0.
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
            CelulaDupla i = primeiro;
            for (int j = 0; j < pos; j++, i = i.prox)
                ;

            CelulaDupla tmp = new CelulaDupla(x);
            tmp.ant = i;
            tmp.prox = i.prox;
            tmp.ant.prox = tmp.prox.ant = tmp;
            tmp = i = null;
        }
    }

    /**
     * Remove um elemento de uma posicao especifica da lista considerando que o
     * primeiro elemento valido esta na posicao 0.
     * 
     * @param posicao Meio da remocao.
     * @return resp int elemento a ser removido.
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
            CelulaDupla i = primeiro.prox;
            for (int j = 0; j < pos; j++, i = i.prox)
                ;

            i.ant.prox = i.prox;
            i.prox.ant = i.ant;
            resp = i.elemento;
            i.prox = i.ant = null;
            i = null;
        }

        return resp;
    }

    /**
     * Mostra os elementos da lista separados por espacos.
     */
    public void mostrar() {
        for (CelulaDupla i = primeiro.prox; i != null; i = i.prox) {
            MyIO.println(i.elemento.imprimir());
        }
    }

    /**
     * Mostra os elementos da lista de forma invertida e separados por espacos.
     */
    public void mostrarInverso() {
        System.out.print("[ ");
        for (CelulaDupla i = ultimo; i != primeiro; i = i.ant) {
            System.out.print(i.elemento + " ");
        }
        System.out.println("] "); // Termina de mostrar.
    }

    /**
     * Procura um elemento em uma posicao.
     * 
     * @param x posicao da Celula a pesquisar.
     * @return <code>true</code> se o elemento existir, <code>false</code> em caso
     *         contrario.
     */
    CelulaDupla getCel(int x) {
        CelulaDupla i;
        int pos = 0;

        for (i = primeiro.prox; i != null; i = i.prox) {
            if (pos == x)
                break;

            pos++;
        }
        return i;
    }

    /**
     * Calcula e retorna o tamanho, em numero de elementos, da lista.
     * 
     * @return resp int tamanho
     */
    public int tamanho() {
        int tamanho = 0;
        for (CelulaDupla i = primeiro; i != ultimo; i = i.prox, tamanho++)
            ;
        return tamanho;
    }
}

class QuickSort {

    private ListaDupla listaDupla;

    /**
     * Construtor.
     * 
     * @param listaDupla lista duplamente encadeada.
     */
    public QuickSort(ListaDupla listaDupla) {
        this.listaDupla = listaDupla;
    }

    /**
     * Troca o conteudo de duas posicoes do array
     * 
     * @param i int primeira posicao
     * @param j int segunda posicao
     */
    public void swap(int i, int j) {
        Jogador temp = listaDupla.getCel(i).elemento;
        listaDupla.getCel(i).elemento = listaDupla.getCel(j).elemento;
        listaDupla.getCel(j).elemento = temp;
    }

    public void quicksort() {
        quicksort(0, listaDupla.tamanho() - 1);
    }

    public String getEstado(int i) {
        return listaDupla.getCel(i).elemento.getEstadoNascimento();
    }

    public String getName(int i) {
        return listaDupla.getCel(i).elemento.getNome();
    }

    /**
     * Comparar strings em ordem alfabetica
     *
     * @param a   primeira string
     * @param b   segunda string
     * @param pos posicao do caracter
     * @return 1 menor, 2 maior
     */
    public int compareNome(String a, String b, int pos) {

        if (a.charAt(pos) == b.charAt(pos))
            if (a.length() == pos + 1 && b.length() == pos + 1)
                return -1;
            else if (a.length() == pos + 1)
                return -1;
            else if (b.length() == pos + 1)
                return 1;
            else
                compareNome(a, b, ++pos);
        else if (a.charAt(pos) < b.charAt(pos))
            return -1;
        else if (a.charAt(pos) > b.charAt(pos))
            return 1;

        return ((int) a.charAt(pos) - (int) b.charAt(pos));
    }

    /**
     * Comparar strings em ordem alfabetica
     *
     * @param a   primeira string
     * @param b   segunda string
     * @param pos posicao do caracter
     * @return 1 menor, -1 maior
     */
    public int compareText(String a, String b) {

        int len;

        if (a.length() >= b.length())
            len = b.length();
        else
            len = a.length();

        for (int i = 0; i < len; i++) {
            if (a.charAt(i) > b.charAt(i))
                return 1;
            else if (a.charAt(i) < b.charAt(i))
                return -1;
        }

        if (a.length() > b.length())
            return 1;
        else if (a.length() < b.length())
            return -1;

        return 0;
    }

    /**
     * Algoritmo de ordenacao Quicksort.
     * 
     * @param esq inicio do array a ser ordenado
     * @param dir fim do array a ser ordenado
     */
    private void quicksort(int esq, int dir) {
        int i = esq, j = dir;
        String pivo = getEstado((dir + esq) / 2);
        String pivoName = getName((dir + esq) / 2);
        while (i <= j) {
            while (compareText(getEstado(i), pivo) == -1
                    || compareText(getEstado(i), pivo) == 0 && compareText(getName(i), pivoName) == -1)
                i++;
            while (compareText(getEstado(j), pivo) == 1
                    || compareText(getEstado(j), pivo) == 0 && compareText(getName(j), pivoName) == 1)
                j--;
            if (i <= j) {
                swap(i, j);
                i++;
                j--;
            }
        }
        if (esq < j)
            quicksort(esq, j);
        if (i < dir)
            quicksort(i, dir);
    }
}

public class TP03Q11 {

    public static void main(String[] args) throws Exception {
        Jogador[] players = getPlayers();
        String entrada = MyIO.readLine();
        ListaDupla list = new ListaDupla(new CelulaDupla(players[0]));

        while (!entrada.contains("FIM")) {
            list.inserirFim(players[Integer.parseInt(entrada) == 223 ? 222 : Integer.parseInt(entrada)]);
            entrada = MyIO.readLine();
        }

        long comeco = new Date().getTime();

        QuickSort quickSort = new QuickSort(list);
        quickSort.quicksort();

        list.mostrar();

        long fim = new Date().getTime();
        long tempo = fim - comeco;

        File file = new File("633516_quicksort2.txt");

        if (!file.exists())
            file.createNewFile();

        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);

        String arquivo = "633516" + "\t" + tempo;
        bw.write(arquivo);
        bw.close();
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
