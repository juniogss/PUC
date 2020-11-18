class TP01Q13 {

    public static boolean isFim(String s) {
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static String criptografar(String word) {
        return criptografar(word, 0);
    }

    /**
     * Recebe a palavra e exibe sua criptografia
     * 
     * @param word
     */
    public static String criptografar(String word, int x) {
        String encrypted = "";

        // adiciona 3 posições em cada caractere da palavra
        if (x < word.length())
            encrypted = (char) (word.charAt(x) + 3) + criptografar(word, x + 1);

        return encrypted;
    }

    public static void main(String[] args) {
        String entrada = MyIO.readLine();

        while (!isFim(entrada)) {
            MyIO.println(criptografar(entrada));
            entrada = MyIO.readLine();
        }
    }
}
