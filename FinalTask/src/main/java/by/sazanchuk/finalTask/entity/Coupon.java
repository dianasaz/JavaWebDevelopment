package by.sazanchuk.finalTask.entity;

import java.util.Date;
import java.util.Objects;

public class Coupon {
    private Integer identity;
    private Date time;
    private Integer doctor_id;
    private Integer user_id;
    private Integer pet_id;
    private Integer service_id;

    public Integer getService_id() {
        return service_id;
    }

    public void setService_id(Integer service_id) {
        this.service_id = service_id;
    }

    public void setDoctor_id(Integer doctor_id) {
        this.doctor_id = doctor_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public void setPet_id(Integer pet_id) {
        this.pet_id = pet_id;
    }

    public Integer getDoctor_id() {
        return doctor_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public Integer getPet_id() {
        return pet_id;
    }

    public Integer getIdentity() {
        return identity;
    }

    public Date getTime() {
        return time;
    }

    public void setIdentity(Integer identity) {
        this.identity = identity;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coupon coupon = (Coupon) o;
        return  Objects.equals(identity, coupon.identity) &&
                Objects.equals(time, coupon.time) &&
                Objects.equals(doctor_id, coupon.doctor_id) &&
                Objects.equals(user_id, coupon.user_id) &&
                Objects.equals(pet_id, coupon.pet_id) &&
                Objects.equals(service_id, coupon.service_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identity, time, doctor_id, user_id, pet_id, service_id);
    }
}
