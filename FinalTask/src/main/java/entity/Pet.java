package entity;

import java.util.List;

public class Pet {
    private int petID;
    private String name;
    private PetList kind;
    private int age;
    private double weight;
    private List<Integer> eventList;

    public void setPetID(int petID) {
        this.petID = petID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setKind(PetList kind) {
        this.kind = kind;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setEventList(List<Integer> eventList) {
        this.eventList = eventList;
    }

    public int getPetID() {
        return petID;
    }

    public String getName() {
        return name;
    }

    public PetList getKind() {
        return kind;
    }

    public int getAge() {
        return age;
    }

    public double getWeight() {
        return weight;
    }

    public List<Integer> getEventList() {
        return eventList;
    }
}
