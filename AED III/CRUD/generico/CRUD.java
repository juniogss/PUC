package CRUD.generico;

import java.io.RandomAccessFile;
import java.lang.reflect.Constructor;

public class CRUD<T extends Register> {

    protected RandomAccessFile file;
    Constructor<T> constructor;

    CRUD(Constructor<T> constructor, String fileName) throws Exception {
        this.constructor = constructor;
        file = new RandomAccessFile(fileName, "rw");
        file.writeInt(0);
    }

    public void close() throws Exception {
        file.close();
    }

    /**
     * Cria um objeto no arquivo
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

        byte[] reg = object.toByteArray();

        file.writeBoolean(true);     // válido
        file.writeShort(reg.length);    // tamanho
        file.write(reg);                // registro

        return object.getID();
    }

    /**
     * Lê um registro do arquivo
     *
     * @param id identificador do registro
     * @return objeto do registro
     */
    public T read(int id) throws Exception {
        T obj = null;
        boolean found = false, isValid;
        short size;
        long lapPosition;
        int objID;

        if (id < 0) System.out.println("Registro Inválido");
        else {
            file.seek(4);
            while (!found) {
                isValid = file.readBoolean();
                size = file.readShort();
                lapPosition = file.getFilePointer();
                objID = file.readInt();
                file.seek(lapPosition + size);

                if (file.getFilePointer() >= file.length()) found = true;
                if (isValid && id == objID) {
                    found = true;
                    obj = constructor.newInstance();
                    byte[] mByte = new byte[size];
                    file.seek(lapPosition);
                    file.read(mByte);
                    obj.fromByteArray(mByte);
                }
            }
        }

        return obj;
    }

    /**
     * "Apaga" um registro do arquivo colocando uma flag <code>false</code> no lápide
     *
     * @param id identificador do registro
     */
    public void delete(int id) throws Exception {
        if (id > 0) {
            long lapidePos, currentLap;
            boolean found = false, isValid;
            short size;
            int objID;

            file.seek(4);

            while (!found) {
                lapidePos = file.getFilePointer();
                isValid = file.readBoolean();
                size = file.readShort();
                currentLap = file.getFilePointer();
                objID = file.readInt();
                file.seek(currentLap + size);

                if (file.getFilePointer() >= file.length()) found = true;
                if (isValid && id == objID) {
                    found = true;
                    file.seek(lapidePos);
                    file.writeBoolean(false);
                }
            }
        }
    }

    /**
     * Atualiza um registro no arquivo
     *
     * @param newObj objeto a ser atualizado
     */
    public void update(T newObj) throws Exception {
        boolean found = false;
        byte[] newByte = newObj.toByteArray();
        if (newObj.getID() < 0) System.out.println("Registro Inválido");
        else {
            file.seek(4);
            long lapPosition;
            boolean lap;
            short size;
            while (!found) {
                lapPosition = file.getFilePointer();
                lap = file.readBoolean();
                size = file.readShort();
                byte[] mByte = new byte[size];
                file.read(mByte);

                T obj = constructor.newInstance();
                obj.fromByteArray(mByte);
                if (file.getFilePointer() >= file.length()) found = true;
                if (lap && newObj.getID() == obj.getID()) {
                    file.seek(lapPosition);
                    file.read(mByte);
                    if (newByte.length <= mByte.length) {
                        file.seek(lapPosition);
                        file.writeBoolean(true);
                        file.writeShort(mByte.length);
                        file.write(newByte);
                    } else {
                        file.seek(lapPosition);
                        file.writeBoolean(false);
                        file.seek(file.length());
                        file.writeBoolean(true);
                        file.writeShort(newByte.length);
                        file.write(newByte);
                    }
                    found = true;
                }
            }
        }
    }
}