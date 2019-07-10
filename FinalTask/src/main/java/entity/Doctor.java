package entity;

import java.util.Date;
import java.util.List;

public class Doctor {
    private int doctorID;
    private String name;
    private List<Integer> service;
    private List<Integer> petList;
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


    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getService() {
        return service;
    }

    public List<Integer> getPetList() {
        return petList;
    }

    public void setService(List<Integer> service) {
        this.service = service;
    }

    public void setPetList(List<Integer> petList) {
        this.petList = petList;
    }
}
