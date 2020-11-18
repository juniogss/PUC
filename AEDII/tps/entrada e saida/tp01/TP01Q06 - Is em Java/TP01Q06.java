public class TP01Q06 {

    public static boolean isFim(String s) {
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    /**
     * Verifica se a palavra só tem vogais
     * 
     * @param word palavra a ser testada
     * @return @true se só tiver vogais
     */
    public static boolean vowel(String word) {
        boolean resp = true;

        for (int x = 0; x < word.length(); x++)
            if (!isVowel(word, x))
                resp = false;

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
    
    /**
     * Verifica se a palavra só tem consoantes
     * 
     * @param word palavra a ser testada
     * @return @true se só tiver consoantes
     */
    public static boolean consonant(String word) {
        boolean resp = true;

        for (int x = 0; x < word.length(); x++)
            if (isVowel(word, x))
                resp = false;
            else if (isInteger(word, x))
                resp = false;

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

    /**
     * Verifica se a palavra só tem inteiros
     * 
     * @param word palavra a ser testada
     * @return @true se só tiver inteiros
     */
    public static boolean integer(String word) {
        boolean resp = true;

        for (int x = 0; x < word.length(); x++)
            if (!isInteger(word, x))
                resp = false;

        return resp;
    }

    /**
     * Verifica se a palavra só tem números reais
     * 
     * @param word palavra a ser testada
     * @return @true se só tiver reais
     */
    public static boolean real(String word) {
        int dot = 0, comma = 0;
        boolean resp = true;

        for (int x = 0; x < word.length(); x++)
            if (!isInteger(word, x)) {
                if (word.charAt(x) == '.') {
                    dot++;
                } else {
                    if (word.charAt(x) == ',') {
                        comma++;
                    } else {
                        resp = false;
                    }
                }
            }

        // verifica se é inteiro e tem apenas uma virgula
        // ou apenas um ponto ou nenhum dos dois
        if (resp)
            if ((dot == 1 && comma == 0) || (dot == 0 && comma == 1) || (dot == 0 && comma == 0))
                return true;
            else
                return false;
        else
            return false;
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
