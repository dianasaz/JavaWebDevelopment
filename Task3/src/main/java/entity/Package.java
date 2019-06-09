package entity;

import java.util.Random;

public class Package {
    private TypeOfPackage type;
    private int quantity;
    private double priceForPackage;

    public Package(TypeOfPackage type, int quantity, double priceForPackage) {
        this.type = type;
        this.quantity = quantity;
        this.priceForPackage = priceForPackage;
    }

    public Package(){}

    public TypeOfPackage getType() {
        return type;
    }

    public void setType(TypeOfPackage type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPriceForPackage() {
        return priceForPackage;
    }

    public void setPriceForPackage(double priceForPackage) {
        this.priceForPackage = priceForPackage;
    }

    @Override
    public String toString() {
        return " type: " + type +
                " quantity: " + quantity +
                " priceForPackage: " + priceForPackage ;
    }
}
