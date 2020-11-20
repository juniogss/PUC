public class PROVA02Q01 {

    public static void main(String[] args) throws Exception {

        Pilha pilha = new Pilha();
        Fila fila = new Fila();

        pilha.inserir(0);
        pilha.inserir(1);
        pilha.inserir(2);
        pilha.inserir(3);
        pilha.inserir(4);
        pilha.inserir(5);

        pilha.mostraPilha();

        fila.inserir(5);
        fila.inserir(7);
        fila.inserir(8);
        fila.inserir(9);

        fila.mostrar();

        ultimo = fila.ultimo;
        mostraPilha(pilha.topo);
        mostrar(fila.primeiro);
        // copy(pilha.topo, fila.primeiro, fila.ultimo);
        // mostraPilha(pilha.topo, fila.ultimo);

    }

    private static void copy(Celula topo, Celula primeiro, Celula ultimo) {

        for (Celula i = topo.prox; i != null; i = i.prox) {
            ultimo.prox = i;
            ultimo = ultimo.prox;
        }

        System.out.println("");
        mostrar(primeiro);
    }

    private void mostraPilha(Celula topo, Celula fila) {
        
        if (topo != null) {
            mostraPilha(topo.prox, fila);
            fila.prox = topo;
            topo = fila.prox;
        }

        System.out.println("" + fila.elemento);

    }

    static Celula ultimo;

    private static void mostraPilha(Celula i) {
        if (i != null) {
            mostraPilha(i.prox);
            ultimo.prox = i;
            ultimo = i;
        } else ultimo.prox = null;
    }

    /**
     * Mostra os elementos separados por espacos.
     */
    public static void mostrar(Celula primeiro) {
        System.out.print("[ ");

        for (Celula i = primeiro.prox; i != null; i = i.prox) {
            System.out.print(i.elemento + " ");
        }

        System.out.println("] ");
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

    static class Pilha {
        private Celula topo;

        /**
         * Construtor da classe que cria uma fila sem elementos.
         */
        public Pilha() {
            topo = null;
        }

        /**
         * Insere elemento na pilha (politica FILO).
         * 
         * @param x int elemento a inserir.
         */
        public void inserir(int x) {
            Celula tmp = new Celula(x);
            tmp.prox = topo;
            topo = tmp;
            tmp = null;
        }

        /**
         * Remove elemento da pilha (politica FILO).
         * 
         * @return Elemento removido.
         * @trhows Exception Se a sequencia nao contiver elementos.
         */
        public int remover() throws Exception {
            if (topo == null) {
                throw new Exception("Erro ao remover!");
            }
            int resp = topo.elemento;
            Celula tmp = topo;
            topo = topo.prox;
            tmp.prox = null;
            tmp = null;
            return resp;
        }

        /**
         * Mostra os elementos separados por espacos, comecando do topo.
         */
        public void mostrar() {
            System.out.print("[ ");
            for (Celula i = topo; i != null; i = i.prox) {
                System.out.print(i.elemento + " ");
            }
            System.out.println("] ");
        }

        public int getSoma() {
            return getSoma(topo);
        }

        private int getSoma(Celula i) {
            int resp = 0;
            if (i != null) {
                resp += i.elemento + getSoma(i.prox);
            }
            return resp;
        }

        public int getMax() {
            int max = topo.elemento;
            for (Celula i = topo.prox; i != null; i = i.prox) {
                if (i.elemento > max)
                    max = i.elemento;
            }
            return max;
        }

        public void mostraPilha() {
            mostraPilha(topo);
        }

        private void mostraPilha(Celula i) {
            if (i != null) {
                mostraPilha(i.prox);
                System.out.println("" + i.elemento);
            }
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
