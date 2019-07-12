package entity;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Pet {
    private int identity;
    private String name;
    private PetList kind;
    private Date dateOfBirth;
    private double weight;
    private List<Event> eventList;

    public void setIdentity(int identity) {
        this.identity = identity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setKind(PetList kind) {
        this.kind = kind;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }

    public int getIdentity() {
        return identity;
    }

    public String getName() {
        return name;
    }

    public PetList getKind() {
        return kind;
    }

    public double getWeight() {
        return weight;
    }

    public List<Event> getEventList() {
        return eventList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return identity == pet.identity &&
                Double.compare(pet.weight, weight) == 0 &&
                Objects.equals(name, pet.name) &&
                kind == pet.kind &&
                Objects.equals(eventList, pet.eventList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identity, name, kind, weight, eventList);
    }

    @Override
    public String toString() {
        return "Pet{" +
                "identity=" + identity +
                ", name='" + name + '\'' +
                ", kind=" + kind +
                ", weight=" + weight +
                ", eventList=" + eventList +
                '}';
    }
}
