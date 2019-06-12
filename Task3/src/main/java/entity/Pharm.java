package entity;

public class Pharm {
    private String name;
    private Certificate certificate;
    private Package aPackage;
    private Dosage dosage;

    public Pharm(String name, Certificate certificate, Package aPackage, Dosage dosage) {
        this.name = name;
        this.certificate = certificate;
        this.aPackage = aPackage;
        this.dosage = dosage;
    }

    public Pharm(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Certificate getCertificate() {
        return certificate;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }

    public Package getaPackage() {
        return aPackage;
    }

    public void setaPackage(Package aPackage) {
        this.aPackage = aPackage;
    }

    public Dosage getDosage() {
        return dosage;
    }

    public void setDosage(Dosage dosage) {
        this.dosage = dosage;
    }

    @Override
    public String toString() {
        return "name: " + name +
                " certificate: " + certificate +
                " Package: " + aPackage +
                " dosage: " + dosage;
    }
}
