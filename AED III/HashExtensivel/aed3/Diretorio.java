package HashExtensivel.aed3;

import java.io.*;

class Diretorio {

    byte profundidadeGlobal;
    long[] enderecos;

    public Diretorio() {
        profundidadeGlobal = 0;
        enderecos = new long[1];
        enderecos[0] = 0;
    }

    public boolean atualizaEndereco(int p, long e) {
        if (p > Math.pow(2, profundidadeGlobal))
            return false;
        enderecos[p] = e;
        return true;
    }

    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeByte(profundidadeGlobal);
        int quantidade = (int) Math.pow(2, profundidadeGlobal);
        int i = 0;
        while (i < quantidade) {
            dos.writeLong(enderecos[i]);
            i++;
        }
        return baos.toByteArray();
    }

    public void fromByteArray(byte[] ba) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(bais);
        profundidadeGlobal = dis.readByte();
        int quantidade = (int) Math.pow(2, profundidadeGlobal);
        enderecos = new long[quantidade];
        int i = 0;
        while (i < quantidade) {
            enderecos[i] = dis.readLong();
            i++;
        }
    }

    public String toString() {
        String s = "\nProfundidade global: " + profundidadeGlobal;
        int i = 0;
        int quantidade = (int) Math.pow(2, profundidadeGlobal);
        while (i < quantidade) {
            s += "\n" + i + ": " + enderecos[i];
            i++;
        }
        return s;
    }

    protected long endereço(int p) {
        if (p > Math.pow(2, profundidadeGlobal))
            return -1;
        return enderecos[p];
    }

    protected boolean duplica() {
        if (profundidadeGlobal == 127)
            return false;
        profundidadeGlobal++;
        int q1 = (int) Math.pow(2, profundidadeGlobal - 1);
        int q2 = (int) Math.pow(2, profundidadeGlobal);
        long[] novosEnderecos = new long[q2];
        int i = 0;
        while (i < q1) {
            novosEnderecos[i] = enderecos[i];
            i++;
        }
        while (i < q2) {
            novosEnderecos[i] = enderecos[i - q1];
            i++;
        }
        enderecos = novosEnderecos;
        return true;
    }

    // Para efeito de determinar o cesto em que o elemento deve ser inserido,
    // só serão considerados valores absolutos da chave.
    protected int hash(int chave) {
        return Math.abs(chave) % (int) Math.pow(2, profundidadeGlobal);
    }

    protected int hash2(int chave, int pl) { // cálculo do hash para uma dada profundidade local
        return Math.abs(chave) % (int) Math.pow(2, pl);
    }

}
