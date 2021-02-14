class ListaDupla {
    private CelulaDupla primeiro;
    private CelulaDupla ultimo;

    public ListaDupla() {
        primeiro = new CelulaDupla();
        ultimo = primeiro;
    }

    /* PAG 32 */
    void inverteListaDupla() {
        CelulaDupla i = primeiro.prox;
        CelulaDupla j = ultimo;
        while (i != j && j.prox != i) {
            int tmp = i.elemento;
            i.elemento = j.elemento;
            j.elemento = tmp;
            i = i.prox;
            j = j.ant;
        }
    }

    /* PAG 34 */
    void inverteListaSimples() {
        Celula i = primeiro.prox;
        Celula j = ultimo;
        Celula k;
        while (i != j && j.prox != i) {
            int tmp = i.elemento;
            i.elemento = j.elemento;
            j.elemento = tmp;
            i = i.prox;
            for (k = primeiro; k.prox != j; k = k.prox)
                ;
            j = k;
        }
    }
}