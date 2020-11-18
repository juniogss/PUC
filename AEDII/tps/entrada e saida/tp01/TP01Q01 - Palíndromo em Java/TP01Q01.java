class TP01Q01 {

    public static boolean isFim(String s) {
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    /**
     * Verificar se é palíndromo
     * 
     * @param word palavra a ser testada
     * @return @true se for palíndromo
     */
    public static boolean isPalindrome(String word) {

        int size = word.length();
        boolean resp = true;

        for (int x = 0; x < size / 2; x++)
            if (word.charAt(x) != word.charAt(size - x - 1))
                resp = false;

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
