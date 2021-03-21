import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class EnergyDrink implements Registro {

    protected int ID;
    protected String brand;
    protected String flavor;
    protected float price;

    public EnergyDrink() {
        this.ID = 1;
        this.brand = "";
        this.flavor = "";
        this.price = 0;
    }

    public EnergyDrink(String brand, String flavor, float price) {
        this.ID = -1;
        this.brand = brand;
        this.flavor = flavor;
        this.price = price;
    }

    public int getID() {
        return this.ID;
    }

    public void setID(int id) {
        this.ID = id;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getFlavor() {
        return this.flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public float getPrice() {
        return this.price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public byte[] toByteArray() throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);

        dos.writeInt(ID);
        dos.writeUTF(brand);
        dos.writeUTF(flavor);
        dos.writeFloat(price);
        dos.close();
        baos.close();

        return baos.toByteArray();
    }

    public void fromByteArray(byte[] ba) throws IOException {

        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(bais);

        ID = dis.readInt();
        brand = dis.readUTF();
        flavor = dis.readUTF();
        price = dis.readFloat();
    }

    @Override
    public String toString() {
        return "{" + " id='" + getID() + "'" + ", brand='" + getBrand() + "'" + ", flavor='" + getFlavor() + "'"
                + ", price='" + getPrice() + "'" + "}";
    }
}