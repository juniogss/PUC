class LAB01Q02Aquecimento {
   public static boolean isMaiuscula(char c) {
      return (c >= 'A' && c <= 'Z');
   }

   public static boolean isFim(String s) {
      return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
   }

   public static int contarLetrasMaiusculas(String s) {
      return contarLetrasMaiusculas(s, 0, 0);
   }

   /**
    * 
    * @param s linha a ser verificada
    * @param i contador
    * @param n número de letras maiúsculas
    * @return total de letras maiúsculas
    */
   public static int contarLetrasMaiusculas(String s, int i, int n) {

      if (i < s.length()) {
         if (isMaiuscula(s.charAt(i)) == true)
         n = contarLetrasMaiusculas(s, i + 1, n + 1);
         else
         n = contarLetrasMaiusculas(s, i + 1, n);
      }

      return n;
   }

   public static void main(String[] args) {
      String[] entrada = new String[1000];
      int numEntrada = 0;

      // Leitura da entrada padrao
      do {
         entrada[numEntrada] = MyIO.readLine();
      } while (isFim(entrada[numEntrada++]) == false);
      numEntrada--; // Desconsiderar ultima linha contendo a palavra FIM

      // Para cada linha de entrada, gerando uma de saida contendo o numero de letras
      // maiusculas da entrada
      for (int i = 0; i < numEntrada; i++) {
         MyIO.println(contarLetrasMaiusculas(entrada[i]));
      }
   }
}
