class Pilha {
    private Celula topo;

    // region EXERCICIO 6 e 7
    public Pilha() {
        topo = null;
    }

    public void inserir(int x) {
        Celula tmp = new Celula(x);
        tmp.prox = topo;
        topo = tmp;
        tmp = null;
    }

    // endregion

    // region EXERCICIO 1

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

    // endregion

    // region EXERCICIO 2

    public int getMax() {
        int max = topo.elemento;
        for (Celula i = topo.prox; i != null; i = i.prox) {
            if (i.elemento > max)
                max = i.elemento;
        }
        return max;
    }

    // endregion

    // region EXERCICIO 3

    public int getMaxRec() {
        return getMax(topo.prox, topo.elemento);
    }

    public int getMax(Celula i, int max) {
        if (i.elemento > max)
            max = i.elemento;

        if (i.prox != null)
            getMax(i.prox, max);

        return max;
    }

    // endregion

    // region EXERCICIO 4
    public void mostrar() {
        mostrar(topo);
    }

    public void mostrar(Celula i) {
        System.out.print(i.elemento + " ");
        if (i.prox != null)
            mostrar(i.prox);
    }

    // endregion

    // region EXERCICIO 5
    public void mostraPilha() {
        mostraPilha(topo);
    }

    private void mostraPilha(Celula i) {
        if (i != null) {
            mostraPilha(i.prox);
            System.out.print(i.elemento + " ");
        }
    }

    // endregion

    // region EXERCICIO 6

    public void mostrarIterativo() {
        System.out.print("[ ");
        for (Celula i = topo; i != null; i = i.prox) {
            System.out.print(i.elemento + " ");
        }
        System.out.println("] ");
    }

    // endregion

}