import java.io.RandomAccessFile;

public class TP01Q09 {

    /**
     * Passar a entrada pra um .txt
     * 
     * @param num quantidade de numeros
     */
    public static void open(int num) {

        try {
            RandomAccessFile rf = new RandomAccessFile("texto.txt", "rw");
            for (int x = 0; x < num; x++) {
                // escrever tudo o que for lido dentro do arquivo texto.txt
                rf.writeDouble(MyIO.readDouble());
            }
            rf.close();
        } catch (Exception ignored) {

        }
    }

    /**
     * 
     * @param num
     */
    public static void reopen(int num) {
        double entrada = 0;

        try {
            RandomAccessFile rf = new RandomAccessFile("texto.txt", "r");
            for (int x = (num - 1); x >= 0; x--) {
                
                rf.seek(x * 8); // andar de byte em byte
                entrada = rf.readDouble();

                if ((entrada - (int) entrada == 0)) // caso nao tenha nada na casa decimal
                    MyIO.println((int) entrada);// converter pra inteiro
                else
                    MyIO.println(entrada);// caso contrario, printamos normalmente

            }
            rf.close();// fechamos arquivo

        } catch (Exception ignored) {
        }
    }

    public static void main(String[] args) {
        int entrada = MyIO.readInt();
        open(entrada);
        reopen(entrada);
    }
}
