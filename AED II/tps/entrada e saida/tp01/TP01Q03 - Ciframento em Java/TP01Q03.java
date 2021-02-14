class TP01Q03 {

    public static boolean isFim(String s) {
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    /**
     * Recebe a palavra e exibe sua criptografia
     * @param word
     */
    public static void criptografar(String word) {
        String encrypted = "";

        // adiciona 3 posições em cada caractere da palavra
        for (int x = 0; x < word.length(); x++)
            encrypted = encrypted + (char) (word.charAt(x) + 3);

        MyIO.println(encrypted);
    }

    public static void main(String[] args) {
        String entrada = MyIO.readLine();

        while (!isFim(entrada)) {
            criptografar(entrada);
            entrada = MyIO.readLine();
        }
    }
}
