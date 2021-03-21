package CRUD.generico;

import org.jetbrains.annotations.NotNull;

import java.io.RandomAccessFile;
import java.lang.reflect.Constructor;

public class CRUD<T extends Register> {

    protected RandomAccessFile arq;
    Constructor<T> constructor;

    CRUD(Constructor<T> constructor, String fileName) throws Exception {
        this.constructor = constructor;
        arq = new RandomAccessFile(fileName, "rw");
        arq.writeInt(0);
    }

    public void close() throws Exception {
        arq.close();
        System.out.println("O CRUD foi encerrado...");
    }

    public int create(T object) throws Exception {
        int newID = -1;
        if (object.getID() == -1) {
            boolean keepReading = true;
            short regLength;
            int lastID;
            long currentlyPos;
            byte[] reg;
            arq.seek(0);
            lastID = arq.readInt();
            newID = lastID + 1;

            if (lastID > 0)
                do {
                    arq.readByte();
                    regLength = arq.readShort();
                    currentlyPos = arq.getFilePointer();
                    arq.seek(currentlyPos + regLength);

                    if (arq.getFilePointer() >= arq.length())
                        keepReading = false;
                } while (keepReading);

            object.setID(newID);
            reg = object.toByteArray();
            arq.writeByte(0);
            arq.writeShort(reg.length);
            arq.write(reg);
            arq.seek(0);
            arq.writeInt(newID);
        } else
            System.out.println("CREATE: ID do objeto invalido");

        return newID;
    }

    public T read(int ID) throws Exception {

        T object = null;

        if (ID > 0) {
            arq.seek(0);
            int lastID = arq.readInt();

            if (lastID > 0) {
                long currentlyPos;
                boolean keepReading = true;
                boolean founded = false;
                byte lapide;

                short regLength;
                int currentlyID;

                do {
                    lapide = arq.readByte();
                    regLength = arq.readShort();
                    currentlyPos = arq.getFilePointer();
                    currentlyID = arq.readInt();
                    arq.seek(currentlyPos + regLength);

                    if (arq.getFilePointer() >= arq.length())
                        keepReading = false;
                    if (lapide == 0 && currentlyID == ID) {
                        keepReading = false;
                        founded = true;
                        object = this.constructor.newInstance();

                        byte[] ba = new byte[regLength];
                        arq.seek(currentlyPos);
                        arq.read(ba);
                        object.fromByteArray(ba);
                    }
                } while (keepReading);
                if (!founded)
                    System.out.println("READ: O registro com o ID buscado não foi encontrado.");
            } else
                System.out.println("READ: Nenhum registro foi inserido no arquivo.");
        } else
            System.out.println("READ: ID fornecido invalido.");

        return object;
    }

    public void delete(int ID) throws Exception {

        if (ID > 0) {
            arq.seek(0);
            int lastID = arq.readInt();

            if (lastID > 0) {
                long currentlyPos;
                long lapidePos;
                boolean keepReading = true;
                boolean founded = false;
                byte lapide;
                short regLength;
                int currentlyID;

                do {
                    lapidePos = arq.getFilePointer();
                    lapide = arq.readByte();
                    regLength = arq.readShort();
                    currentlyPos = arq.getFilePointer();
                    currentlyID = arq.readInt();
                    arq.seek(currentlyPos + regLength);

                    if (arq.getFilePointer() >= arq.length())
                        keepReading = false;
                    if (lapide == 0 && ID == currentlyID) {
                        keepReading = false;
                        founded = true;
                        arq.seek(lapidePos);
                        arq.writeByte(1);
                    }
                } while (keepReading);

                if (!founded)
                    System.out.println("DELETE: O registro com o ID buscado não foi encontrado.");
            } else
                System.out.println(
                        "DELETE: A exclusão do registro não foi concluida. Nenhum registro foi inserido no arquivo.");
        } else
            System.out.println("DELETE: A exclusão do registro não foi concluida. O ID fornecido é invalido.");

    }

    public void update(@NotNull T object) throws Exception {

        int ID = object.getID();

        if (ID > 0) {
            arq.seek(0);
            int lastID = arq.readInt();

            if (lastID > 0) {
                long lapidePos;
                long currentlyPos;
                boolean bigger = false;
                boolean keepReading = true;
                boolean founded = false;
                byte lapide;
                short regLength;
                int currentlyID;
                byte[] newBa = object.toByteArray();

                do {
                    lapidePos = arq.getFilePointer();
                    lapide = arq.readByte();
                    regLength = arq.readShort();
                    currentlyPos = arq.getFilePointer();
                    currentlyID = arq.readInt();
                    arq.seek(currentlyPos + regLength);

                    if (arq.getFilePointer() >= arq.length())
                        keepReading = false;
                    if (lapide == 0 && ID == currentlyID) {
                        founded = true;
                        byte[] oldBa = new byte[regLength];
                        arq.seek(currentlyPos);
                        arq.read(oldBa);
                        if (newBa.length <= oldBa.length) {
                            keepReading = false;
                            arq.seek(currentlyPos);
                            arq.write(newBa);
                        } else {
                            bigger = true;
                            arq.seek(lapidePos);
                            arq.writeByte(1);

                            arq.seek(currentlyPos + regLength);

                        }
                    }
                } while (keepReading);

                if (bigger) {
                    arq.writeByte(0);
                    arq.writeShort(newBa.length);
                    arq.write(newBa);
                }
                if (!founded)
                    System.out.println("UPDATE: O registro com o ID buscado não foi encontrado.");
            } else
                System.out.println("UPDATE: Nenhum registro foi inserido no arquivo.");
        } else
            System.out.println("UPDATE: A atualizaçãp do registro não foi concluida. O ID fornecido é invalido.");

    }
}