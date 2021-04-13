package CRUD.indexado;

import java.io.File;

public class Main {
    public static void main(String[] args) {

        EnergyDrink drink1 = new EnergyDrink("Red Bull", "Melancia", 11.50F);
        EnergyDrink drink2 = new EnergyDrink("Red Bull", "Açaí", 11.50F);
        EnergyDrink drink3 = new EnergyDrink("Loud", "Original", 8.00F);

        try {

            File d = new File("db");
            if (!d.exists()) d.mkdir();

            new File("db/energetic.db").delete();
            new File("db/energetic.hashd.db").delete();
            new File("db/energetic.hashc.db").delete();

            CRUD<EnergyDrink> arq = new CRUD<>(EnergyDrink.class.getConstructor(), "db/energetic.db");

            // Insere os 3 energeticos
            System.out.println("\nCREATE");
            int id1 = arq.create(drink1);
            int id2 = arq.create(drink2);
            int id3 = arq.create(drink3);

            // Busca 3 energeticos
            System.out.println("\nREAD");
            System.out.println(arq.read(id1));
            System.out.println(arq.read(id2));
            System.out.println(arq.read(id3));

            System.out.println("\nUPDATE");
            drink1.setFlavor("icais");
            drink1.setPrice(9.50F);
            arq.update(drink1);
            System.out.println(arq.read(id1));

            drink2.setFlavor("Sem açhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhúcar");
            drink2.setPrice(9.50F);
            arq.update(drink2);
            System.out.println(arq.read(id2));

            System.out.println("\nDELETE");
            System.out.println(arq.read(id1));
            arq.delete(id1);
            EnergyDrink aux = arq.read(id1);
            System.out.println(aux == null ? "Energético excluído\n" : aux);

            // Busca 3 energeticos
            System.out.println("\nREAD");
            System.out.println(arq.read(id1));
            System.out.println(arq.read(id2));
            System.out.println(arq.read(id3));

            arq.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}