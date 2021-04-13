package HashExtensivel.aed3;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.lang.reflect.Constructor;
import java.util.ArrayList;

public class Cesto <T extends RegistroHashExtensivel<T>>{

    Constructor<T> construtor;
    byte profundidadeLocal; // profundidade local do cesto
    short quantidade; // quantidade de pares presentes no cesto
    short quantidadeMaxima; // quantidade máxima de pares que o cesto pode conter
    ArrayList<T> elementos; // sequência de elementos armazenados
    short bytesPorElemento; // tamanho fixo de cada elemento em bytes
    short bytesPorCesto; // tamanho fixo do cesto em bytes

    public Cesto(Constructor<T> ct, int qtdmax) throws Exception {
        this(ct, qtdmax, 0);
    }

    public Cesto(Constructor<T> ct, int qtdmax, int pl) throws Exception {
        construtor = ct;
        if (qtdmax > 32767)
            throw new Exception("Quantidade máxima de 32.767 elementos");
        if (pl > 127)
            throw new Exception("Profundidade local máxima de 127 bits");
        profundidadeLocal = (byte) pl;
        quantidade = 0;
        quantidadeMaxima = (short) qtdmax;
        elementos = new ArrayList<>(quantidadeMaxima);
        bytesPorElemento = ct.newInstance().size();
        bytesPorCesto = (short) (bytesPorElemento * quantidadeMaxima + 3);
    }

    public byte[] toByteArray() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeByte(profundidadeLocal);
        dos.writeShort(quantidade);
        int i = 0;
        while (i < quantidade) {
            dos.write(elementos.get(i).toByteArray());
            i++;
        }
        byte[] vazio = new byte[bytesPorElemento];
        while (i < quantidadeMaxima) {
            dos.write(vazio);
            i++;
        }
        return baos.toByteArray();
    }

    public void fromByteArray(byte[] ba) throws Exception {
        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(bais);
        profundidadeLocal = dis.readByte();
        quantidade = dis.readShort();
        int i = 0;
        elementos = new ArrayList<>(quantidadeMaxima);
        byte[] dados = new byte[bytesPorElemento];
        T elem;
        while (i < quantidadeMaxima) {
            dis.read(dados);
            elem = construtor.newInstance();
            elem.fromByteArray(dados);
            elementos.add(elem);
            i++;
        }
    }

    public boolean create(T elem) {
        if (full())
            return false;
        int i = quantidade - 1;
        while (i >= 0 && elem.hashCode() < elementos.get(i).hashCode())
            i--;
        elementos.add(i + 1, elem);
        quantidade++;
        return true;
    }

    public T read(int chave) {
        if (empty())
            return null;
        int i = 0;
        while (i < quantidade && chave > elementos.get(i).hashCode())
            i++;
        if (i < quantidade && chave == elementos.get(i).hashCode())
            return elementos.get(i);
        else
            return null;
    }

    public boolean update(T elem) {
        if (empty())
            return false;
        int i = 0;
        while (i < quantidade && elem.hashCode() > elementos.get(i).hashCode())
            i++;
        if (i < quantidade && elem.hashCode() == elementos.get(i).hashCode()) {
            elementos.set(i, elem);
            return true;
        } else
            return false;
    }

    public boolean delete(int chave) {
        if (empty())
            return false;
        int i = 0;
        while (i < quantidade && chave > elementos.get(i).hashCode())
            i++;
        if (chave == elementos.get(i).hashCode()) {
            elementos.remove(i);
            quantidade--;
            return true;
        } else
            return false;
    }

    public boolean empty() {
        return quantidade == 0;
    }

    public boolean full() {
        return quantidade == quantidadeMaxima;
    }

    public String toString() {
        String s = "Profundidade Local: " + profundidadeLocal + "\nQuantidade: " + quantidade + "\n| ";
        int i = 0;
        while (i < quantidade) {
            s += elementos.get(i).toString() + " | ";
            i++;
        }
        while (i < quantidadeMaxima) {
            s += "- | ";
            i++;
        }
        return s;
    }

    public int size() {
        return bytesPorCesto;
    }

}
