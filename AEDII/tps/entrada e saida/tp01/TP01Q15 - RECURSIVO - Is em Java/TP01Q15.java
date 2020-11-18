public class TP01Q15 {

    public static boolean isFim(String s) {
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static boolean vowel(String word) {
        return vowel(word, 0);
    }

    /**
     * Verifica se a palavra só tem vogais
     * 
     * @param word palavra a ser testada
     * @return @true se só tiver vogais
     */
    public static boolean vowel(String word, int x) {
        boolean resp = true;
        int size = word.length();

        if (x < size)
            if (isVowel(word, x))
                resp = vowel(word, x + 1);
            else {
                resp = false;
                x = size;
            }

        return resp;
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

    public static boolean consonant(String word) {
        return consonant(word, 0);
    }

    /**
     * Verifica se a palavra só tem consoantes
     * 
     * @param word palavra a ser testada
     * @return @true se só tiver consoantes
     */
    public static boolean consonant(String word, int x) {
        boolean resp = true;
        int size = word.length();

        if (x < size)
            if (isVowel(word, x)) {
                resp = false;
                x = size;
            } else {
                if (isInteger(word, x)) {
                    resp = false;
                    x = size;
                } else
                    resp = consonant(word, x + 1);
            }

        return resp;
    }

    /**
     * Verifica se o char @param word na posição @param x é um inteiro
     * 
     * @return @true se inteiro
     */
    private static boolean isInteger(String word, int x) {
        return word.charAt(x) >= '0' && word.charAt(x) <= '9';
    }

    public static boolean integer(String word) {
        return integer(word, 0);
    }

    /**
     * Verifica se a palavra só tem inteiros
     * 
     * @param word palavra a ser testada
     * @return @true se só tiver inteiros
     */
    public static boolean integer(String word, int x) {
        boolean resp = true;
        int size = word.length();

        if (x < size)
            if (isInteger(word, x))
                resp = integer(word, x + 1);
            else {
                x = size;
                resp = false;
            }

        return resp;
    }

    public static boolean real(String testar) {
        return real(testar, 0, 0, 0);
    }

    /**
     * Verifica se a palavra só tem números reais
     * 
     * @param word palavra a ser testada
     * @return @true se só tiver reais
     */
    public static boolean real(String word, int x, int dot, int comma) {
        int size = word.length();
        boolean resp = true;

        if (x < size)
            if (!isInteger(word, x)) {
                if (word.charAt(x) == '.') {
                    resp = real(word, x + 1, dot + 1, comma);
                } else {
                    if (word.charAt(x) == ',') {
                        resp = real(word, x + 1, dot, comma + 1);
                    } else {
                        resp = false;
                    }
                }
            } else {
                resp = real(word, x + 1, dot, comma);
            }

        // verifica se é inteiro e tem apenas uma virgula
        // ou apenas um ponto ou nenhum dos dois
        if (dot > 1 || comma > 1)
            resp = false;
        else if (resp)
            if ((dot == 1 && comma == 0) || (dot == 0 && comma == 1) || (dot == 0 && comma == 0))
                resp = true;
            else
                resp = false;
        else
            resp = false;

        return resp;
    }

    public static void main(String[] args) {
        String entrada = MyIO.readLine();

        while (!isFim(entrada)) {

            if (vowel(entrada))
                MyIO.print("SIM ");
            else
                MyIO.print("NAO ");

            if (consonant(entrada))
                MyIO.print("SIM ");
            else
                MyIO.print("NAO ");

            if (integer(entrada))
                MyIO.print("SIM ");
            else
                MyIO.print("NAO ");

            if (real(entrada))
                MyIO.println("SIM");
            else
                MyIO.println("NAO");

            entrada = MyIO.readLine();
        }
    }
}
