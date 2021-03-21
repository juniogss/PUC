package CRUD.generico;

import java.io.IOException;

public interface Register {
    int getID();

    void setID(int n);

    byte[] toByteArray() throws IOException;

    void fromByteArray(byte[] ba) throws IOException;
}