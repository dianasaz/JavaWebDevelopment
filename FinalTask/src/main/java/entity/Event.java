package entity;

import java.util.Date;

public class Event {
    private int eventID;
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

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public int getEventID() {
        return eventID;
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
}
