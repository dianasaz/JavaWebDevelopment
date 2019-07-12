package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Doctor {
    private static final int QUANTITY_OF_COUPON = 50;
    private int identity;
    private String name;
    private List<Service> service;
    private List<Pet> petList;
    private List<Coupon> coupons = new ArrayList<>(QUANTITY_OF_COUPON);

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setService(List<Service> service) {
        this.service = service;
    }

    public void setPetList(List<Pet> petList) {
        this.petList = petList;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }


    public int getIdentity() {
        return identity;
    }

    public String getName() {
        return name;
    }


    public void setIdentity(int identity) {
        this.identity = identity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Service> getService() {
        return service;
    }

    public List<Pet> getPetList() {
        return petList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return identity == doctor.identity &&
                Objects.equals(name, doctor.name) &&
                Objects.equals(service, doctor.service) &&
                Objects.equals(petList, doctor.petList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identity, name, service, petList);
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "identity=" + identity +
                ", name='" + name + '\'' +
                ", service=" + service +
                ", petList=" + petList +
                ", workList=" +
                '}';
    }
}
