package StackOverflow;

import java.io.RandomAccessFile;
import java.lang.reflect.Constructor;

public class CRUD<T extends Register> {

    private static final String USER_PATH = "db/user";
    private final HashExtensivel<PCVUser> hashUser;
    protected RandomAccessFile file;
    Constructor<T> constructor;

    CRUD(Constructor<T> constructor, String fileName) throws Exception {
        this.constructor = constructor;
        file = new RandomAccessFile(fileName, "rw");
        file.writeInt(0);
        hashUser = new HashExtensivel<>(PCVUser.class.getConstructor(), 5, USER_PATH + ".hash_d.db", USER_PATH + ".hash_c.db");
    }

    public void close() throws Exception {
        file.close();
    }

    /**
     * Cria um objeto no file e adiciona o ID no indice
     *
     * @param object objeto
     * @return id do objeto criado
     */
    public int create(T object) throws Exception {

        file.seek(0);
        object.setID(file.readInt() + 1);

        file.seek(0);
        file.writeInt(object.getID());

        file.seek(file.length());
        long pos = file.getFilePointer();

        byte[] reg = object.toByteArray();

        file.writeBoolean(true);
        file.writeShort(reg.length);
        file.write(reg);

        hashUser.create(new PCVUser(object.getID(), pos));

        return object.getID();
    }

    /**
     * Lê um registro do file de acordo com o índice
     *
     * @param id identificador do registro
     * @return objeto do registro
     */
    public T read(int id) throws Exception {

        PCVUser read = hashUser.read(id);
        if (read == null) return null;

        long position = read.getPosition();
        if (position < 0) return null;

        file.seek(position);
        boolean lap = file.readBoolean();
        int size = file.readShort();
        long lapPosition = file.getFilePointer();
        long objID = file.readInt();

        if (!lap || objID != id) return null;

        byte[] reg = new byte[size];
        file.seek(lapPosition);
        file.read(reg);

        T obj = constructor.newInstance();
        obj.fromByteArray(reg);

        return obj;
    }

    /**
     * "Apaga" um registro do file colocando uma flag <code>false</code> no lápide
     * e deleta a chave do índice
     *
     * @param id identificador do registro
     */
    public boolean delete(int id) throws Exception {

        PCVUser cvp = hashUser.read(id);
        if (cvp == null) return false;

        long position = cvp.getPosition();
        if (position == -1) return false;

        file.seek(position);
        file.writeBoolean(false);

        hashUser.delete(id);

        return true;
    }

    /**
     * Atualiza um registro no file, se o tamanho for maior que o antigo,
     * altera o lapide no file, cria um novo registro e atualiza o id no índice
     *
     * @param obj objeto a ser atualizado
     */
    public boolean update(T obj) throws Exception {

        PCVUser read = hashUser.read(obj.getID());
        if (read == null) return false;

        long position = read.getPosition();
        if (position < 0) return false;

        file.seek(position);
        boolean lap = file.readBoolean();
        int size = file.readShort();
        long lapPosition = file.getFilePointer();
        long objID = file.readInt();

        if (!lap || objID != obj.getID()) return false;

        byte[] reg = new byte[size];
        file.seek(lapPosition);
        file.read(reg);

        byte[] oldObj = obj.toByteArray();
        file.seek(position);

        if (oldObj.length <= size) {

            file.writeBoolean(true);
            file.writeShort(size);

        } else {

            file.writeBoolean(false);
            file.seek(file.length());

            hashUser.update(new PCVUser(obj.getID(), file.getFilePointer()));

            file.writeBoolean(true);
            file.writeShort(oldObj.length);

        }

        file.write(oldObj);
        return true;
    }
}