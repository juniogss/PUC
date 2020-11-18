import java.util.Random;

public class TP01Q04 {

    public static boolean isFim(String s) {
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    /**
     * Recebe as duas variaveis que serao localizadas e trocadas
     * 
     * @param modificar entrada a ser alterada
     * @param a         primeira letra
     * @param b         segunda letra
     * @return palavra alterada
     */
    public static String aleatorio(final String modificar, final char a, final char b) {
        final int tamanho = modificar.length();
        String nova = ""; // nova String a ser gerada

        for (int x = 0; x < tamanho; x++) {
            if (modificar.charAt(x) == a) {
                nova = nova + b;
            } else {
                nova = nova + modificar.charAt(x);
            }
        }
        return nova;
    }

    public static void main(final String[] args) {
        String entrada;
        // criando gerador e setando seed padrao
        final Random gerador = new Random();
        gerador.setSeed(4);

        char letra1, letra2;

        entrada = MyIO.readLine();

        while (!isFim(entrada)) {
            letra1 = ((char) ('a' + (Math.abs(gerador.nextInt()) % 26)));
            letra2 = ((char) ('a' + (Math.abs(gerador.nextInt()) % 26)));

            MyIO.println(Aleatorio(entrada, letra1, letra2));
            entrada = MyIO.readLine();
        }
    }
}
