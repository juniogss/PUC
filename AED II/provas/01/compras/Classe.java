import java.io.*;
import java.util.Formatter;
import java.util.Scanner;
import java.io.File;

public class Classe {

    public static void main(String[] args) throws Exception {

        int n = Integer.parseInt(MyIO.readLine());

        for (int i = 0; i < n; i++) {
            String line = MyIO.readLine();

            String[] list = sort(getList(line));
            for (int j = 0; j < list.length; j++) {
                if (j == list.length - 1)
                    MyIO.print(list[j]);
                else
                    MyIO.print(list[j] + " ");
            }

            MyIO.println("");
        }
    }

    /**
     * Recebe a linha
     * @return lista com valores únicos
     */
    public static String[] getList(String line) {

        String[] list = line.split(" ");
        String[] aux = new String[list.length];

        int count = 0;
        for (int i = 0; i < list.length; i++) {
            if (!contains(aux, list[i])) {
                aux[count] = list[i];
                count++;
            }
        }

        String[] out = new String[count];
        for (int i = 0; i < count; i++) {
            out[i] = aux[i];
        }

        return out;
    }

    /**
     * Ordena a lista por ordem alfabética
     * @return lista ordenada
     */
    public static String[] sort(String[] array) {
        for (int i = 0; i <= array.length - 1; i++) {
            for (int j = 1; j < array.length - i; j++) {
                if (compare(array[j - 1], array[j]) == 1) {
                    String temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                }
            }
        }

        return array;
    }

    /**
     * Compara duas palavras
     * @return \-1 se primeira < segunda
     * @return \1 se primeira > segunda
     */
    private static int compare(String first, String second) {
        int len;

        if (first.length() >= second.length())
            len = second.length();
        else
            len = first.length();

        for (int i = 0; i < len; i++) {
            if (first.charAt(i) > second.charAt(i))
                return 1;
            else if (first.charAt(i) < second.charAt(i))
                return -1;
        }

        if (first.length() > second.length())
            return 1;
        else if (first.length() < second.length())
            return -1;

        return 0;
    }

    /**
     * Verifica se a entrada já existe no array
     * @param v palavra a ser verificada
     */
    public static boolean contains(final String[] array, final String v) {

        boolean result = false;

        for (String i : array) {
            if (i == null)
                i = "";
            if (i.equals(v)) {
                result = true;
                break;
            }
        }

        return result;
    }

}
