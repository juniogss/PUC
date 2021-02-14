class ListaSimples {
    private Celula primeiro;
    private Celula ultimo;

    public Lista() {
        primeiro = new Celula();
        ultimo = primeiro;
    }

    /**
     * Remove o elemento da segunda posição da lista
     * 
     * @return resp int elemento removido.
     * @throws Exception Se <code>posicao</code> invalida.
     */
    public int removerSegunda() throws Exception {
        int tamanho = tamanho();
        int resp;

        if (primeiro == ultimo) {
            throw new Exception("Erro ao remover (vazia)!");

        } else if (2 >= tamanho) {
            throw new Exception("Erro ao remover (posicao 2 / " + tamanho + " invalida!");
        } else {
            Celula p = primeiro.prox;
            Celula q = p.prox;
            p.prox = q.prox;

            resp = q.elemento;
            q.prox = null;
            q = null;
        }

        return resp;
    }
}