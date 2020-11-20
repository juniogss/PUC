public class P02Q09 {

    public static void main(String[] args) throws Exception {

        Fila a = new Fila();

        a.enfileirar(new Celula(1, 5));
        a.enfileirar(new Celula(1, 9));
        a.enfileirar(new Celula(1, 7));
        a.enfileirar(new Celula(1, 15));
        a.enfileirar(new Celula(1, 10));

        a.mostrar();

        MyIO.println(a.desenfileirar().prioridade);

        a.mostrar();

        MyIO.println(a.desenfileirar().prioridade);

        a.mostrar();

    }

    static class Celula {
        public int elemento; // Elemento inserido na celula.
        public int prioridade; // Elemento inserido na celula.
        public Celula prox; // Aponta a celula prox.

        /**
         * Construtor da classe.
         */
        public Celula() {
            this(0, 0);
        }

        /**
         * Construtor da classe.
         * 
         * @param elemento int inserido na celula.
         */
        public Celula(int elemento, int prioridade) {
            this.elemento = elemento;
            this.prioridade = prioridade;
            this.prox = null;
        }
    }

    static class Fila {
        private Celula primeiro;
        private Celula ultimo;

        /**
         * Construtor da classe que cria uma fila sem elementos (somente no cabeca).
         */
        public Fila() {
            primeiro = new Celula();
            ultimo = primeiro;
        }

        public void enfileirar(Celula celula) {
            ultimo.prox = celula;
            ultimo = ultimo.prox;
        }

        public Celula desenfileirar() throws Exception {

            Celula maior = null, anterior = null;

            if (primeiro == ultimo) {
                throw new Exception("Erro ao remover!");
            }

            maior = primeiro.prox;

            Celula i = primeiro.prox.prox, j = primeiro.prox;
            while (i != null) {
                if (i.prioridade > maior.prioridade) {
                    anterior = j;
                    maior = i;
                }
                j = i;
                i = i.prox;
            }

            Celula maiorTmp = maior;

            if (maior == primeiro) {
                Celula tmp = primeiro;
                primeiro = primeiro.prox;
                tmp.prox = null;
                tmp = null;
            } else {

                Celula tmp = maior;
                maior = maior.prox;
                tmp.prox = null;
                tmp = null;
                anterior.prox = maior;

            }

            return maiorTmp;
        }

        /**
         * Mostra os elementos separados por espacos.
         */
        public void mostrar() {
            System.out.print("[ ");

            for (Celula i = primeiro.prox; i != null; i = i.prox) {
                System.out.print(i.prioridade + " ");
            }

            System.out.println("] ");
        }
    }
}
