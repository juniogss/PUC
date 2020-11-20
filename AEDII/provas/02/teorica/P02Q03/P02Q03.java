public class P02Q03 {

    public static void main(String[] args) throws Exception {

        Fila a = new Fila();
        Fila b = new Fila();
        Fila c = new Fila();

        a.inserir(1);
        b.inserir(1);
        c.inserir(1);

        a.inserir(2);
        b.inserir(2);
        c.inserir(2);

        a.inserir(3);
        b.inserir(3);
        c.inserir(3);

        c.inserir(3);

        c.inserir(3);

        soma(a, b, c);
    }

    private static void soma(Fila a, Fila b, Fila c) {

        int sum = 0;

        if (a.primeiro.prox != null) {
            sum += a.primeiro.prox.elemento;
            a.primeiro = a.primeiro.prox;
        }

        if (b.primeiro.prox != null) {
            sum += b.primeiro.prox.elemento;
            b.primeiro = b.primeiro.prox;
        }

        if (c.primeiro.prox != null) {
            sum += c.primeiro.prox.elemento;
            c.primeiro = c.primeiro.prox;
        }

        System.out.println(sum);

        if (a.primeiro.prox != null || b.primeiro.prox != null || c.primeiro.prox != null)
            soma(a, b, c);

    }

    static class Celula {
        public int elemento; // Elemento inserido na celula.
        public Celula prox; // Aponta a celula prox.

        /**
         * Construtor da classe.
         */
        public Celula() {
            this(0);
        }

        /**
         * Construtor da classe.
         * 
         * @param elemento int inserido na celula.
         */
        public Celula(int elemento) {
            this.elemento = elemento;
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

        /**
         * Insere elemento na fila (politica FIFO).
         * 
         * @param x int elemento a inserir.
         */
        public void inserir(int x) {
            ultimo.prox = new Celula(x);
            ultimo = ultimo.prox;
        }

        /**
         * Remove elemento da fila (politica FIFO).
         * 
         * @return Elemento removido.
         * @trhows Exception Se a fila nao tiver elementos.
         */
        public int remover() throws Exception {
            if (primeiro == ultimo) {
                throw new Exception("Erro ao remover!");
            }

            Celula tmp = primeiro;
            primeiro = primeiro.prox;
            int resp = primeiro.elemento;
            tmp.prox = null;
            tmp = null;
            return resp;
        }

        /**
         * Mostra os elementos separados por espacos.
         */
        public void mostrar() {
            System.out.print("[ ");

            for (Celula i = primeiro.prox; i != null; i = i.prox) {
                System.out.print(i.elemento + " ");
            }

            System.out.println("] ");
        }
    }
}
