import java.time.LocalDate;
import java.util.*;

public class Main {
    public static final String FILENAME = "kvetiny.txt";
    public static final String OUTPUT_FILENAME = "vystup.txt";
    public static void main(String[] args) {

        RegisterFlowers register = new RegisterFlowers();
        try {
            register.readFlowersFromFile(FILENAME);
        } catch (PlantException e) {
            System.err.println(
                    "Chyba při čtení souboru: "
                            +e.getLocalizedMessage());
        }


        try {
            register.addFlower(new Flower(
                    "Bazalka", "v kuchyni", 3, LocalDate.of(2021, 9, 4), LocalDate.of(2021, 9, 4)));;
            register.addFlower(new Flower(
                    "Květina", "v koupelně", 2, LocalDate.of(2021, 9, 4), LocalDate.of(2021, 9, 4)));;


        } catch (PlantException e) {
            System.err.println("Chyba při přidávání položky: "
                    +e.getLocalizedMessage());
        }
        List<Flower> flowers =register.getFlowers();
        register.removeFlower(flowers.get(1));
        register.removeFlower(flowers.get(4));
        flowers=register.getFlowers();

        //Vypis rostin s doporučenou další zálivkou
        System.out.println("Vypís rostlin s další doporučenou zálivkou.");
        for (Flower allFlowers :flowers) {
            System.out.println(allFlowers.getWateringInfo());
        }


        try {
            register.writePurchasesToFile(OUTPUT_FILENAME);
        } catch (PlantException e) {
            System.err.println(e.getLocalizedMessage());
        }

        System.out.println("--------------------------------------------------------"+"\n"+"Seřazené rostliny podle jména rostliny");

        Collections.sort(flowers);
        flowers.forEach(System.out::println);

        System.out.println("----------------------------------------------------------"+"\n"+"Seřazené rostliny podle poslední zálivky");


        Collections.sort(flowers, new Comparator<Flower>() {
            @Override
            public int compare(Flower flower1, Flower flower2) {
                return flower1.getWatering().compareTo(flower2.getWatering());
            }
        });

        flowers.forEach(System.out::println);

        System.out.println("----------------------------------------------------------"+"\n"+"datum kdy byla rostlina zasazena");

        Set<Flower> dateOfPlant = new HashSet<>(flowers);
        flowers.forEach(flower -> System.out.println("Květina "+ flower.getName() + " byla zasazena " + flower.getPlanted()));


    }
}

