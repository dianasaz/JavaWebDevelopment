package entity;

import java.util.Date;

public class Event {
    private int eventID;
    private Date date;
    private Service service;
    private Pet pet;

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public int getEventID() {
        return eventID;
    }

    public Date getDate() {
        return date;
    }

    public Service getService() {
        return service;
    }

    public Pet getPet() {
        return pet;
    }
}
