package by.sazanchuk.finalTask.entity;

import java.sql.Time;
import java.util.Date;
import java.util.Objects;

public class Coupon {
    private Integer identity;
    private Time time;
    private Doctor doctor;
    private boolean taken;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getIdentity() {
        return identity;
    }

    public Date getTime() {
        return time;
    }

    public by.sazanchuk.finalTask.entity.Doctor getDoctor() {
        return doctor;
    }

    public boolean isTaken() {
        return taken;
    }

    public void setIdentity(Integer identity) {
        this.identity = identity;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coupon coupon = (Coupon) o;
        return identity == coupon.identity &&
                taken == coupon.taken &&
                Objects.equals(time, coupon.time) &&
                Objects.equals(doctor, coupon.doctor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identity, time, doctor, taken);
    }
}
