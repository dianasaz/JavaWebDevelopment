package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Doctor {
    private static final int QUANTITY_OF_COUPON = 50;
    private Integer identity;
    private String name;
    private List<Service> service;
    private List<Coupon> coupons = new ArrayList<>(QUANTITY_OF_COUPON);

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setService(List<Service> service) {
        this.service = service;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }


    public Integer getIdentity() {
        return identity;
    }

    public String getName() {
        return name;
    }


    public void setIdentity(Integer identity) {
        this.identity = identity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Service> getService() {
        return service;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return identity == doctor.identity &&
                Objects.equals(name, doctor.name) &&
                Objects.equals(service, doctor.service);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identity, name, service);
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "identity=" + identity +
                ", name='" + name + '\'' +
                ", service=" + service +
                ", workList=" +
                '}';
    }
}
