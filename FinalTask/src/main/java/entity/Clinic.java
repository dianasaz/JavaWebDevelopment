package entity;

import java.util.List;

public class Clinic {
    private String name;
    private String address;
    private int number;
    private List<Doctor> doctor;

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setDoctor(List<Doctor> doctor) {
        this.doctor = doctor;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getNumber() {
        return number;
    }

    public List<Doctor> getDoctor() {
        return doctor;
    }
}
