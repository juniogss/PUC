import java.io.RandomAccessFile;
import java.util.Calendar;
import java.util.Date;

class Teste {
    public static void main(String[] args) {

        Player player = new Player("Neymar", new Date(2000, Calendar.JANUARY, 1), 70, 1.74, "PSG");

        try {
            RandomAccessFile file = new RandomAccessFile("player.db", "rw");

//            file.writeChars(player.getName());
            file.writeUTF(player.getName());
            file.writeLong(player.getBirth().getTime());
            file.writeFloat((float) player.getHeight());
            file.writeFloat((float) player.getWeight());
            file.writeUTF(player.getClub());

            file.seek(0);

            String name = file.readUTF();
            long time = file.readLong();
            float height = file.readFloat();
            float weight = file.readFloat();
            String club = file.readUTF();

            Player print = new Player(name, new Date(time), weight, height, club);

            System.out.println(print);

            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

class Player {

    private String name;
    private Date birth;
    private double weight;
    private double height;
    private String club;

    public Player(String name, Date birth, double weight, double height, String club) {
        this.name = name;
        this.birth = birth;
        this.weight = weight;
        this.height = height;
        this.club = club;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", birth=" + birth +
                ", weight=" + weight +
                ", height=" + height +
                ", club='" + club + '\'' +
                '}';
    }
}
