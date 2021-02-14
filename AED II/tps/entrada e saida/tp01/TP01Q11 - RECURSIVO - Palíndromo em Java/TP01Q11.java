class TP01Q11 {

    public static boolean isFim(String s) {
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static boolean isPalindrome(String word) {
        return isPalindrome(word, 0);
    }

    /**
     * Verificar se é palíndromo
     * 
     * @param word palavra a ser testada
     * @return @true se for palíndromo
     */
    public static boolean isPalindrome(String word, int x) {

        int size = word.length();
        boolean resp = true;

        if (x < size / 2) {
            if (word.charAt(x) != word.charAt(size - x - 1)) {
                resp = false;
                x = size;
            } else
                resp = isPalindrome(word, x + 1);
        }
        return resp;
    }

    public static void main(String[] args) {
        String entrada = MyIO.readLine();

        while (!isFim(entrada)) {
            if (isPalindrome(entrada))
                MyIO.println("SIM");
            else
                MyIO.println("NAO");

            entrada = MyIO.readLine();
        }
    }
}
