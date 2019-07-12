package entity;

import java.util.Objects;

public class Service {
    private int identity;
    private String name;
    private int price;

    public void setIdentity(int identity) {
        this.identity = identity;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getIdentity() {
        return identity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
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
