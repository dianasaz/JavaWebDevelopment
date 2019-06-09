package entity;

public class Certificate {
    private int number;
    private int dateOfDelivery;
    private String organization;


    public Certificate(int number, int dateOfDelivery, String organization) {
        this.number = number;
        this.dateOfDelivery = dateOfDelivery;
        this.organization = organization;
    }

    public Certificate(){}

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getDateOfDelivery() {
        return dateOfDelivery;
    }

    public void setDateOfDelivery(int dateOfDelivery) {
        this.dateOfDelivery = dateOfDelivery;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    @Override
    public String toString() {
        return "number: " + number +
                " dateOfDelivery: " + dateOfDelivery +
                " organization: " + organization ;
    }
}
