package entity;

public class Version {
    private VersionType type;
    private Pharm pharm;

    public Version(VersionType type, Pharm pharm) {
        this.type = type;
        this.pharm = pharm;
    }

    public Version(){}

    public VersionType getType() {
        return type;
    }

    public void setType(VersionType type) {
        this.type = type;
    }

    public Pharm getPharm() {
        return pharm;
    }

    public void setPharm(Pharm pharm) {
        this.pharm = pharm;
    }

    @Override
    public String toString() {
        return "type: " + type +
                " pharm: " + pharm ;
    }
}
