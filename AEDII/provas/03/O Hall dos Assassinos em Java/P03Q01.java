import java.io.*;

public class P03Q01 {

    public static void main(String[] args) throws Exception {

        String entrada = MyIO.readLine();
        int n = 0;
        Lista list = new Lista();

        String[] dead = new String[2000];

        while (!entrada.contains("FIM")) {
            String[] line = entrada.split(" ");

            Murderer murderer = list.pesquisar(line[0]);

            if (murderer != null)
                murderer.setDeaths(murderer.getDeaths() + 1);
            else
                list.inserirFim(new Murderer(line[0], line[1], 1));

            dead[n] = line[1];
            n++;

            entrada = MyIO.readLine();
        }

        for (int x = 0; x < n; x++) {
            Murderer murderer = list.pesquisar(dead[x]);
            if (murderer != null)
                murderer.setName("");
        }

        list.mostrar();
    }
}

class Murderer {
    private int deaths;
    private String name;
    private String murdered;

    public Murderer(String name) {
        this.name = name;
    }

    public Murderer(String name, String murdered, int deaths) {
        this.name = name;
        this.murdered = murdered;
        this.deaths = deaths;
    }

    public int getDeaths() {
        return this.deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMurdered() {
        return this.murdered;
    }

    public void setMurdered(String murdered) {
        this.murdered = murdered;
    }

    public String toString() {
        return getName() + " " + getDeaths();
    }
}

class Lista {
    private Murderer[] array;
    private int n;

    /**
     * Construtor da classe.
     */
    public Lista() {
        this(2000);
    }

    /**
     * Construtor da classe.
     * 
     * @param tamanho Tamanho da lista.
     */
    public Lista(int tamanho) {
        array = new Murderer[tamanho];
        n = 0;
    }

    /**
     * Insere um Murderer na ultima posicao da lista.
     * 
     * @param x Murderer a ser inserido.
     * @throws Exception Se a lista estiver cheia.
     */
    public void inserirFim(Murderer x) throws Exception {

        // validar insercao
        if (n >= array.length) {
            throw new Exception("Erro ao inserir!");
        }

        array[n] = x;
        n++;
    }

    /**
     * Mostra os elementos da lista separados por espacos.
     */
    public void mostrar() {
        MyIO.println("HALL OF MURDERERS");
        for (int i = 0; i < n; i++)
            if (!array[i].getName().equals(""))
                MyIO.println(array[i] + "");
    }

    /**
     * Procura um Murderer e retorna se ele existe.
     * 
     * @param x Murderer a ser pesquisado.
     * @return <code>true</code> se o array existir, <code>false</code> em caso
     *         contrario.
     */
    public Murderer pesquisar(String name) {
        Murderer retorno = null;

        for (int i = 0; i < n && retorno == null; i++)
            if (array[i].getName().equals(name))
                retorno = array[i];

        return retorno;
    }
}