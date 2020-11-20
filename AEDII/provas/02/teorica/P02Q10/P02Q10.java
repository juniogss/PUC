public class P02Q10 {

    public static void main(String[] args) throws Exception {

        Pilha a = new Pilha();
        Pilha b = new Pilha();

        b.inserir(5);
        b.inserir(3);
        b.inserir(1);

        a.inserir(4);
        a.inserir(2);
        a.inserir(0);

        intercalar(a, b);

    }

    static boolean showA = true;

    public static void intercalar(Pilha a, Pilha b) throws Exception{

        if (a.topo != null && showA) {
            System.out.println(a.remover() + "");
        }

        if (b.topo != null && !showA) {
            System.out.println(b.remover() + "");
        }

        if (a.topo != null || b.topo != null){
            showA = !showA;
            intercalar(a, b);
        }
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

    }
}
