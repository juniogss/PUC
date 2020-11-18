import java.io.*;
import java.util.Formatter;
import java.util.Scanner;
import java.io.File;

public class Classe {

    public static void main(String[] args) throws Exception {

        int n = Integer.parseInt(MyIO.readLine());

        for (int i = 0; i < n; i++) {
            String line = MyIO.readLine();
            MyIO.println(line);
            // MyIO.println(diamonds(line));
        }
    }

    private static int diamonds(String s) {

        MyIO.println(s);

        int size = s.length();
        int counter = 0;
        int tmp = 0;

        for (int j = 0; j < size; ++j) {
            if (s.charAt(j) == '<')
                tmp++;
            if (s.charAt(j) == '>' && tmp > 0) {
                counter++;
                tmp--;
            }
        }

        return counter;

    }
}
