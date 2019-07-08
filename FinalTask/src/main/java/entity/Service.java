package entity;

public class Service {
    private int serviceID;
    private ServiceList kind;
    private int price;

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    public void setKind(ServiceList kind) {
        this.kind = kind;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getServiceID() {
        return serviceID;
    }

    public ServiceList getKind() {
        return kind;
    }

    public int getPrice() {
        return price;
    }
}
