import java.io.*;

public class Batuke {
    public static void main(String[] args) {
        int size, x, y;

        size = MyIO.readInt();
        Matriz m1 = new Matriz(size, size);
        m1.preenche();

        x = MyIO.readInt();
        y = MyIO.readInt();

        Celula start = m1.getCel(x, y);

        int allCel = size * size;
        int visit = 1;
        int walk = 1;

        Celula tmp = start;
        MyIO.print(tmp.elemento);

        if (start.dir != null) {
            tmp = start.dir;
            visit++;
            print(tmp);
        }

        // down
        if(tmp.inf != null){
            tmp = tmp.inf;
            print(tmp);
            visit++;
        }

        // left
        while (tmp.esq != null) {
            visit++;
            tmp = tmp.esq;
            print(tmp);
        }

        // up
        while (tmp.sup != null) {
            visit++;
            tmp = tmp.sup;
            print(tmp);
        }

        // rigth
        while (tmp.dir != null) {
            visit++;
            tmp = tmp.dir;
            print(tmp);
        }

        // down
        while (tmp.inf != null) {
            visit++;
            tmp = tmp.inf;
            print(tmp);
        }

        // left
        while (tmp.esq != null) {
            visit++;
            tmp = tmp.esq;
            print(tmp);
        }

        MyIO.println("");
        MyIO.println(visit + "");
    }

    public static void print(Celula celula) {
        MyIO.print(" " + celula.elemento);
    }
}

class Matriz {
    Celula inicio;

    Matriz(int c, int l) {
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

    public Celula getCel(int x, int y) {
        int lin = 0;
        int col = 0;

        Celula cel = null;

        for (Celula i = inicio; i != null; i = i.inf) {
            for (Celula j = i; j != null; j = j.dir) {
                if (lin == x && col == y) {
                    cel = j;
                    break;
                }
                col++;
            }
            col = 0;
            lin++;
        }

        return cel;
    }

    public void preenche() {
        int n = 1;
        for (Celula i = inicio; i != null; i = i.inf) {
            for (Celula j = i; j != null; j = j.dir) {
                j.elemento = n;
                n++;
            }
        }
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
