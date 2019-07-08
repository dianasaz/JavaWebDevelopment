package entity;

import java.util.Date;
import java.util.List;

public class Doctor {
    private int doctorID;
    private String name;
    private List<Service> service;
    private List<PetList> petList;
    private List<Date> workList;

    public void setWorkList(List<Date> workList) {
        this.workList = workList;
    }

    public List<Date> getWorkList() {
        return workList;
    }

    public int getDoctorID() {
        return doctorID;
    }

    public String getName() {
        return name;
    }

    public List<Service> getService() {
        return service;
    }

    public List<PetList> getPetList() {
        return petList;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setService(List<Service> service) {
        this.service = service;
    }

    public void setPetList(List<PetList> petList) {
        this.petList = petList;
    }
}
