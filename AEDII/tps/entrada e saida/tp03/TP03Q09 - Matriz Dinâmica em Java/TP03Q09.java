public class TP03Q09 {
    public static void main(String[] args) {
        int repete = MyIO.readInt();
        int linhas, colunas;
        for (int i = 0; i < repete; i++) {
            
            linhas = MyIO.readInt();
            colunas = MyIO.readInt();

            MatrizDinamica m1 = new MatrizDinamica(colunas, linhas);
            m1.preenche();
            m1.diagonalPrincipal();
            m1.diagonalSecundaria();
            
            linhas = MyIO.readInt();
            colunas = MyIO.readInt();

            MatrizDinamica m2 = new MatrizDinamica(colunas, linhas);
            m2.preenche();
            
            MatrizDinamica soma = new MatrizDinamica(colunas, linhas);
            soma.soma(m1, m2);
            soma.mostrar();

            MatrizDinamica multiplicacao = new MatrizDinamica(colunas, linhas);
            multiplicacao.multiplicacao(m1, m2, colunas);
            multiplicacao.mostrar();
        }
    }
}

class MatrizDinamica {
    Celula inicio;

    MatrizDinamica(int c, int l) {
        inicio = new Celula();
        Celula old = inicio;
        Celula aux = inicio;
        Celula tmp;
        Celula a;
        Celula b;
        for (int i = 0; i < c; i++) {
            if (i == 0) {
                for (int j = 1; j < l; j++) {
                    tmp = new Celula();
                    tmp.esq = old;
                    old.dir = tmp;
                    old = tmp;
                }
            } else {
                old = new Celula();
                old.sup = aux;
                aux.inf = old;
                a = aux.dir;
                for (int j = 1; j < l; j++) {
                    b = new Celula();
                    old.dir = b;
                    b.esq = old;
                    a.inf = b;
                    b.sup = a;
                    old = b;
                    a = a.dir;
                }
                aux = aux.inf;
            }
        }
    }

    public void mostrar() {
        for (Celula i = inicio; i != null; i = i.inf) {
            for (Celula j = i; j != null; j = j.dir) {
                MyIO.print(j.elemento + " ");
            }
            MyIO.println("");
        }
    }

    public void preenche() {
        for (Celula i = inicio; i != null; i = i.inf) {
            for (Celula j = i; j != null; j = j.dir) {
                int x = MyIO.readInt();
                j.elemento = x;
            }
        }
    }

    public void diagonalPrincipal() {
        MyIO.print(inicio.elemento + " ");
        Celula old = inicio;
        for (Celula i = inicio; i != null; i = i.inf) {
            for (Celula j = i; j != null; j = j.dir) {
                if (j != inicio && j.esq != null && j.esq.sup != null && j.esq.sup == old) {
                    MyIO.print(j.elemento + " ");
                    old = j;
                }
            }
        }
        MyIO.println("");
    }

    public void diagonalSecundaria() {
        Celula ultima = pegaUltimo(inicio);
        MyIO.print(ultima.elemento + " ");
        Celula old = ultima;
        for (Celula i = inicio; i != null; i = i.inf) {
            for (Celula j = i; j != null; j = j.dir) {
                if (j != old && j.dir != null && j.dir.sup != null && j.dir.sup == old) {
                    MyIO.print(j.elemento + " ");
                    old = j;
                }
            }
        }
        MyIO.println("");
    }

    public Celula pegaUltimo(Celula start) {
        Celula i;
        for (i = start; i.dir != null; i = i.dir)
            ;
        return i;
    }

    public void soma(MatrizDinamica m1, MatrizDinamica m2) {
        for (Celula i1 = m1.inicio, i2 = m2.inicio,
                ri = this.inicio; i1 != null; i1 = i1.inf, i2 = i2.inf, ri = ri.inf) {
            for (Celula j1 = i1, j2 = i2, rj = ri; j1 != null; j1 = j1.dir, j2 = j2.dir, rj = rj.dir) {
                rj.elemento = j1.elemento + j2.elemento;
            }
        }
    }

    public void multiplicacao(MatrizDinamica m1, MatrizDinamica m2, int n) {
        int sm;
        int i;
        int j;
        int k;
        Celula im;
        Celula jm;
        for (i = 0, im = this.inicio; i < n; i++, im = im.inf) {
            for (j = 0, jm = im; j < n; j++, jm = jm.dir) {
                sm = 0;
                for (k = 0; k < n; k++) {
                    sm = sm + (pegam1(m1, i, k) * pegam2(m2, k, j));
                }
                jm.elemento = sm;
            }
        }
    }

    public int pegam1(MatrizDinamica m1, int i, int k) {
        // x=linhas
        // y=colunas
        int x = 0, y;
        int resp = 0;
        for (Celula ic = m1.inicio; x < i + 1; x++, ic = ic.inf) {
            y = 0;
            for (Celula jc = ic; y < k + 1; y++, jc = jc.dir) {
                if (x == i && y == k)
                    resp = jc.elemento;
            }
        }
        return resp;
    }

    public int pegam2(MatrizDinamica m2, int k, int j) {
        // x=linhas
        // y=colunas
        int x = 0, y;
        int resp = 0;
        for (Celula ic = m2.inicio; x < k + 1; x++, ic = ic.inf) {
            y = 0;
            for (Celula jc = ic; y < j + 1; y++, jc = jc.dir) {
                if (x == k && y == j)
                    resp = jc.elemento;
            }
        }
        return resp;
    }

}

class Celula {
    Celula dir;
    Celula esq;
    Celula sup;
    Celula inf;
    int elemento;

    Celula(int x) {
        dir = null;
        esq = null;
        inf = null;
        sup = null;
        elemento = x;
    }

    Celula() {
        dir = null;
        esq = null;
        inf = null;
        sup = null;
    }
}
