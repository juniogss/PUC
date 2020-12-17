class Doidona {
    HashReserva T1;
    HashRehash T2;
    Lista T3;
    ArvoreBinaria T3;
  
    public Doidona() {
      T1 = new HashReserva();
      T2 = new HashRehash();
      T3 = new Lista();
      T4 = new ArvoreBinaria();
    }
  
    public void Inserir(Player x) {
      boolean wasInserted = T1.inserir(x);
      if (!wasInserted) {
        int hashValue = x.getHeigth() % 3;
        if (hashValue < 1) {
          T2.inserir(x);
        } else if (hashValue < 2) {
          T3.inserirFim(x);
        } else {
          T4.inserir(x);
        }
      }
    }
  