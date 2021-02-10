import java.io.RandomAccessFile;

class Teste {
    public static void main(String[] args) {

        try {
            RandomAccessFile arq = new RandomAccessFile("file.db", "rw");

            arq.writeUTF("a");
            arq.writeUTF("b");
            arq.writeUTF("c");
            arq.writeUTF("d");
            arq.writeUTF("1");
            arq.writeUTF("2");
            arq.writeUTF("3");
            arq.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
