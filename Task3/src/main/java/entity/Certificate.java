package entity;

import java.util.Date;

public class Certificate {
    private int number;
    private Date dateOfDelivery;
    private String organization;


    public Certificate(int number, Date dateOfDelivery, String organization) {
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

    public Date getDateOfDelivery() {
        return dateOfDelivery;
    }

    public void setDateOfDelivery(Date dateOfDelivery) {
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
