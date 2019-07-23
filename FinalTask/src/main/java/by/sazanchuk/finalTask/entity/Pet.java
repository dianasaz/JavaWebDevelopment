package by.sazanchuk.finalTask.entity;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Pet {
    private Integer identity;
    private int user_identity;
    private String name;
    private PetList kind;
    private Date dateOfBirth;
    private List<Event> eventList;

    public int getUser_identity() {
        return user_identity;
    }

    public void setUser_identity(int user_identity) {
        this.user_identity = user_identity;
    }

    public void setIdentity(Integer identity) {
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

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }

    public Integer getIdentity() {
        return identity;
    }

    public String getName() {
        return name;
    }

    public PetList getKind() {
        return kind;
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
                Objects.equals(name, pet.name) &&
                kind == pet.kind &&
                Objects.equals(eventList, pet.eventList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identity, name, kind, eventList);
    }

    @Override
    public String toString() {
        return "Pet{" +
                "identity=" + identity +
                ", name='" + name + '\'' +
                ", kind=" + kind +
                ", eventList=" + eventList +
                '}';
    }
}
