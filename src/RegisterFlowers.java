import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class RegisterFlowers {

    public static final String DELIMETER = "\t";
    private List<Flower> flowers = new ArrayList<>();

    public void addFlower(Flower newFlower) {
        flowers.add(newFlower);
    }

    public void removeFlower(Flower flower) {
        flowers.remove(flower);
    }

    public List<Flower> getFlowers() {
        return new ArrayList<>(flowers);
    }

    public void readFlowersFromFile(String filename) throws PlantException {
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(filename)))) {
            while (scanner.hasNextLine()) {
                String nextLIne = scanner.nextLine();
                String[] items = nextLIne.split(DELIMETER);
                String name = items[0];
                String notes = items[1];
                int frequencyOfWatering = Integer.parseInt(items[2]);
                LocalDate watering = LocalDate.parse(items[3]);
                LocalDate planted = LocalDate.parse(items[4]);


                Flower newFlowers = new Flower(name, notes, frequencyOfWatering, watering, planted);
                addFlower(newFlowers);


            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void writePurchasesToFile(String filename) throws PlantException {

        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Flower flower : flowers) {
                String outputLine =
                        flower.getName()+DELIMETER
                                +flower.getNotes()+DELIMETER
                                +flower.getFrequencyOfWatering()+DELIMETER
                                +flower.getWatering()+DELIMETER
                                +flower.getPlanted();

                writer.println(outputLine);
            }
        } catch (IOException e) {
            throw new PlantException("Nastala chyba při zápisu do souboru: "
                    +e.getLocalizedMessage());
        }

    }
}
