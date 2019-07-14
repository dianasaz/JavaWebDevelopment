package entity;

import java.util.Date;
import java.util.Objects;

public class Event {
    private Integer identity;
    private Date date;
    private int service;
    private int pet;
    private int doctor;

    public int getDoctor() {
        return doctor;
    }

    public void setDoctor(int doctor) {
        this.doctor = doctor;
    }

    public void setIdentity(Integer identity) {
        this.identity = identity;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public Integer getIdentity() {
        return identity;
    }

    public Date getDate() {
        return date;
    }

    public int getService() {
        return service;
    }

    public void setService(int service) {
        this.service = service;
    }

    public void setPet(int pet) {
        this.pet = pet;
    }

    public int getPet() {
        return pet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return identity == event.identity &&
                service == event.service &&
                pet == event.pet &&
                doctor == event.doctor &&
                Objects.equals(date, event.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identity, date, service, pet, doctor);
    }

    @Override
    public String toString() {
        return "Event{" +
                "identity=" + identity +
                ", date=" + date +
                ", service=" + service +
                ", pet=" + pet +
                ", doctor=" + doctor +
                '}';
    }
}
