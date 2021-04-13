package StackOverflow;

import java.io.*;

public class User implements Register {

    protected int ID;
    protected String name;
    protected String email;
    protected String password;

    public User() {
        this.ID = -1;
        this.name = "";
        this.email = "";
        this.password = "";
    }

    public User(String name, String email, String password) {
        this.ID = -1;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] toByteArray() throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);

        dos.writeInt(ID);
        dos.writeUTF(name);
        dos.writeUTF(email);
        dos.writeUTF(password);
        dos.close();
        baos.close();

        return baos.toByteArray();
    }

    public void fromByteArray(byte[] ba) throws IOException {

        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(bais);

        ID = dis.readInt();
        name = dis.readUTF();
        email = dis.readUTF();
        password = dis.readUTF();
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}