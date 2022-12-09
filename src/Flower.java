import java.time.LocalDate;

public class Flower implements  Comparable<Flower>{
    private String name;
    private String notes;
    private LocalDate planted;
    private LocalDate watering;
    private int frequencyOfWatering;

    public Flower(String name, String notes, int frequencyOfWatering,LocalDate watering, LocalDate planted) throws PlantException {
        this.name = name;
        this.notes = notes;
        this.planted = planted;
        setWatering(watering);
        setFrequencyOfWatering(frequencyOfWatering);
    }



    //region Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDate getPlanted() {
        return planted;
    }

    public void setPlanted(LocalDate planted) {
        this.planted = planted;
    }

    public LocalDate getWatering() {
        return watering;
    }

    public void setWatering(LocalDate watering) throws PlantException {
        if (watering.isBefore(planted)) {
            throw new PlantException(
                    "Hodnota u poslední zálivky nesmí být starší než datum zasazení"
            );
        }
        this.watering = watering;
    }

    public int getFrequencyOfWatering() {
        return frequencyOfWatering;
    }

    public void setFrequencyOfWatering(int frequencyOfWatering) throws PlantException {
        if (frequencyOfWatering < 1) {
            throw new PlantException(
                    "Frekvene zalévání květiny nesmí být nižší než 1"
                            +" (zadal jsi : "+frequencyOfWatering+")");
        }

        this.frequencyOfWatering = frequencyOfWatering;
    }
    //endregion


    @Override
    public String toString(){
        return name + ", "+ notes + ", " + frequencyOfWatering + ", " + watering + ", " + planted;
    }
    public String getWateringInfo(){
        LocalDate lastWatering = watering;
        LocalDate nextWatering = lastWatering.plusDays(frequencyOfWatering);
        String info = "Květina " +  name + " má poslední zálivku ze dne: " +watering + ", další doporučená zálivka dne: " + nextWatering;
        return info;
    }

    @Override
    public int compareTo(Flower secondFlower) {

        return this.getName().compareTo(secondFlower.getName());
    }
}
