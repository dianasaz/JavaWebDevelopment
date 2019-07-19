package by.sazanchuk.finalTask.entity;

import java.util.Objects;

public class Service {
    private Integer identity;
    private String name;
    private Integer price;

    public void setIdentity(Integer identity) {
        this.identity = identity;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getIdentity() {
        return identity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service service = (Service) o;
        return identity == service.identity &&
                price == service.price &&
                name == service.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(identity, name, price);
    }

    @Override
    public String toString() {
        return "Service{" +
                "identity=" + identity +
                ", name=" + name +
                ", price=" + price +
                '}';
    }
}
