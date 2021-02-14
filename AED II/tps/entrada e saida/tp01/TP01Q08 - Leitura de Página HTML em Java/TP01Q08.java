import java.io.*;
import java.net.*;

public class TP01Q08 {

    public static boolean isFim(String s) {
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    /**
     * Verifica quantidade de vogais que o texto possui
     * 
     * @param word texto a ser verificado
     * @return array com quantidades de vogais em suas posições
     */
    public static int[] vowel(String word) {

        int[] array = new int[22];

        // inicializar os valores do array
        for (int x = 0; x < 22; x++)
            array[x] = 0;

        for (int x = 0; x < word.length(); x++)
            switch (word.charAt(x)) {
                case 'a':
                    array[0] = array[0] + 1;
                    break;
                case 'e':
                    array[1] = array[1] + 1;
                    break;
                case 'i':
                    array[2] = array[2] + 1;
                    break;
                case 'o':
                    array[3] = array[3] + 1;
                    break;
                case 'u':
                    array[4] = array[4] + 1;
                    break;
                case 'á':
                    array[5] = array[5] + 1;
                    break;
                case 'é':
                    array[6] = array[6] + 1;
                    break;
                case 'í':
                    array[7] = array[7] + 1;
                    break;
                case 'ó':
                    array[8] = array[8] + 1;
                    break;
                case 'ú':
                    array[9] = array[9] + 1;
                    break;
                case 'à':
                    array[10] = array[10] + 1;
                    break;
                case 'è':
                    array[11] = array[11] + 1;
                    break;
                case 'ì':
                    array[12] = array[12] + 1;
                    break;
                case 'ò':
                    array[13] = array[13] + 1;
                    break;
                case 'ù':
                    array[14] = array[14] + 1;
                    break;
                case 'ã':
                    array[15] = array[15] + 1;
                    break;
                case 'õ':
                    array[16] = array[16] + 1;
                    break;
                case 'â':
                    array[17] = array[17] + 1;
                    break;
                case 'ê':
                    array[18] = array[18] + 1;
                    break;
                case 'î':
                    array[19] = array[19] + 1;
                    break;
                case 'ô':
                    array[20] = array[20] + 1;
                    break;
                case 'û':
                    array[21] = array[21] + 1;
                    break;
            }

        return array;
    }

    /**
     * Verifica se letra @param word na posição @param x é vogal
     * 
     * @return @true se vogal
     */
    private static boolean isVowel(String word, int x) {
        return word.charAt(x) == 'a' || word.charAt(x) == 'e' || word.charAt(x) == 'i' || word.charAt(x) == 'o'
                || word.charAt(x) == 'u' || word.charAt(x) == 'A' || word.charAt(x) == 'E' || word.charAt(x) == 'I'
                || word.charAt(x) == 'O' || word.charAt(x) == 'U';
    }

    /**
     * Verifica a quantidade de consoantes
     * 
     * @param word texto a ser verificado
     * @return número de consoantes
     */
    public static int consonant(String word) {
        int consonants = 0;

        for (int x = 0; x < word.length(); x++)
            if (word.charAt(x) >= 'a' && word.charAt(x) <= 'z')
                if (!isVowel(word, x))
                    consonants++;

        return consonants;
    }

    /**
     * Verifica a quantidade de <br>
     * 
     * @param word texto a ser verificado
     * @return número de <br>
     */
    public static int br(String word) {
        int cont = 0;

        for (int x = 0; x < word.length(); x++)
            if (word.charAt(x) == '<' && word.charAt(x + 1) == 'b' && word.charAt(x + 2) == 'r'
                    && word.charAt(x + 3) == '>')
                cont++;

        return cont;
    }

    /**
     * Verifica a quantidade de
     * <table>
     * 
     * @param word texto a ser verificado
     * @return número de
     *         <table>
     */
    public static int table(String word) {
        int cont = 0;

        for (int x = 0; x < word.length(); x++)
            if (word.charAt(x) == '<' && word.charAt(x + 1) == 't' && word.charAt(x + 2) == 'a'
                    && word.charAt(x + 3) == 'b' && word.charAt(x + 4) == 'l' && word.charAt(x + 5) == 'e'
                    && word.charAt(x + 6) == '>') {
                cont++;
                x = x + 6;
            }

        return cont;
    }

    /**
     * Leitura da página
     * 
     * @param address url
     * @return texto do conteúdo
     */
    public static String readHttp(String address) {
        URL url;
        InputStream is = null;
        BufferedReader br;
        String resp = "", line;

        try {
            url = new URL(address);
            is = url.openStream();
            br = new BufferedReader(new InputStreamReader(is));

            while ((line = br.readLine()) != null) // loop rodando enquanto tiver linhas a serem lidas
                resp = resp + line;

        } catch (MalformedURLException mue) {
            mue.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        try {
            is.close();
        } catch (IOException ignored) {

        }

        return resp;
    }

    public static void show(String word, String nome) {
        int[] array = vowel(word);
        int consoantes = consonant(word);
        int br = br(word);
        int table = table(word);

        // ajuste para a retirada de consoantes que ja tem no br
        if (br > 0)
            consoantes = consoantes - (2 * br);

        // ajuste de consoantes para a retirada das consoantes que ja tem no table
        if (table > 0) {
            consoantes = consoantes - (3 * table);
            array[0] = array[0] - table;
            array[1] = array[1] - table;
        }

        MyIO.print("a(" + array[0] + ") ");
        MyIO.print("e(" + array[1] + ") ");
        MyIO.print("i(" + array[2] + ") ");
        MyIO.print("o(" + array[3] + ") ");
        MyIO.print("u(" + array[4] + ") ");
        MyIO.print("á(" + array[5] + ") ");
        MyIO.print("é(" + array[6] + ") ");
        MyIO.print("í(" + array[7] + ") ");
        MyIO.print("ó(" + array[8] + ") ");
        MyIO.print("ú(" + array[9] + ") ");
        MyIO.print("à(" + array[10] + ") ");
        MyIO.print("è(" + array[11] + ") ");
        MyIO.print("ì(" + array[12] + ") ");
        MyIO.print("ò(" + array[13] + ") ");
        MyIO.print("ù(" + array[14] + ") ");
        MyIO.print("ã(" + array[15] + ") ");
        MyIO.print("õ(" + array[16] + ") ");
        MyIO.print("â(" + array[17] + ") ");
        MyIO.print("ê(" + array[18] + ") ");
        MyIO.print("î(" + array[19] + ") ");
        MyIO.print("ô(" + array[20] + ") ");
        MyIO.print("û(" + array[21] + ") ");
        MyIO.print("consoante(" + consoantes + ") ");
        MyIO.print("<br>(" + br + ") ");
        MyIO.print("<table>(" + table + ") ");
        MyIO.println(nome);

    }

    public static void main(String[] args) throws Exception {

        MyIO.setCharset("UTF-8");
        String address, name, http;

        name = MyIO.readLine();

        while (!isFim(name)) {
            address = MyIO.readLine();
            http = readHttp(address);
            show(http, name);
            name = MyIO.readLine();
        }
    }
}
