class Fila {
    private Celula primeiro;
    private Celula ultimo;

    public Fila() {
        primeiro = new Celula();
        ultimo = primeiro;
    }

    public void inserir(int x) {
        ultimo.prox = new Celula(x);
        ultimo = ultimo.prox;
    }

    /* PAG 24 */
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

    /* PAG 26 */
    public void mostrar() {
        System.out.print("[ ");

        for (Celula i = primeiro.prox; i != null; i = i.prox) {
            System.out.print(i.elemento + " ");
        }

        System.out.println("] ");
    }

    /* PAG 27 */
    public int maior() throws Exception {
        int maior = -1;
        if (primeiro == ultimo) {
            throw new Exception("Erro");
        } else {
            maior = primeiro.prox.elemento;
            Celula i = primeiro.prox.prox;
            while (i != null) {
                if (i.elemento > maior)
                    maior = i.elemento;
                i = i.prox;
            }
        }

        return maior;
    }

    /* PAG 29 */
    public int mostrarTerceiro() {
        return primeiro.prox.prox.prox.elemento;
    }

    /* PAG 32 */
    public int somar() {
        int resp = 0;
        for (Celula i = primeiro.prox; i != null; i = i.prox)
            resp += i.elemento;

        return resp;
    }

    /* PAG 33 */
    void inverter() {
        Celula fim = ultimo;
        while (primeiro != fim) {
            Celula nova = new Celula(primeiro.prox.elemento);
            nova.prox = fim.prox;
            fim.prox = nova;
            Celula tmp = primeiro.prox;
            primeiro.prox = tmp.prox;
            nova = tmp = tmp.prox = null;
            if (ultimo == fim) {
                ultimo = ultimo.prox;
            }
        }
        fim = null;
    }

    /* PAG 35 */
    int contar() {
        return contar(primeiro.prox);
    }

    int contar(Celula i) {
        int resp;
        if (i == null)
            resp = 0;
        else if (i.elemento % 2 == 0 && i.elemento % 5 == 0)
            resp = 1 + contar(i.prox);
        else
            resp = contar(i.prox);

        return resp;
    }

    /* PAG 38 */
    public Celula toFila(Celula topo) {
        Fila fila = new Fila();
        for (Celula i = topo; i != null; i = i.prox)
            fila.inserir(i.elemento);
        return fila.primeiro;
    }

}

/* PAG 39 */
class FilaSemNo {
    private Celula primeiro;
    private Celula ultimo;

    public Fila() {
        primeiro = null;
        ultimo = null;
    }

    public inserir(int x) {
        if (primeiro == null)
            primeiro = ultimo = new Celula(x);
        else {
            ultimo.prox = new Celula(x);
            ultimo = ultimo.prox;
        }
    }
}