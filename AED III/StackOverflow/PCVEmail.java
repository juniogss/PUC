package StackOverflow;

import java.io.*;

public class PCVEmail implements RegistroHashExtensivel<PCVEmail> {

    private String email;
    private int id;
    private final short SIZE = 44;

    public PCVEmail() {
        this("", -1);
    }

    public PCVEmail(String email, int id) {
        try {
            this.email = email;
            this.id = id;
            if (email.length() + 6 > SIZE)
                throw new Exception("Número de caracteres do email maior que o permitido. Os dados serão cortados.");
        } catch (Exception ec) {
            ec.printStackTrace();
        }
    }

    @Override
    public int hashCode() {
        return this.email.hashCode();
    }

    public short size() {
        return this.SIZE;
    }

    public String toString() {
        return this.email + ";" + this.id;
    }

    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeUTF(email);
        dos.writeInt(id);
        byte[] bs = baos.toByteArray();
        byte[] bs2 = new byte[SIZE];
        for (int i = 0; i < SIZE; i++)
            bs2[i] = ' ';
        for (int i = 0; i < bs.length && i < SIZE; i++)
            bs2[i] = bs[i];
        return bs2;
    }

    public void fromByteArray(byte[] ba) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(bais);
        this.email = dis.readUTF();
        this.id = dis.readInt();
    }

}