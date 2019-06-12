package entity;

import java.util.List;
import java.util.Objects;

public class Medicine {
    private String name;
    private Group group;
    private List<String> analog;
    private Version version;

    public Medicine(String name, Group group,List<String> analog, Version version) {
        this.name = name;
        this.group = group;
        this.analog = analog;
        this.version = version;
    }

    public Medicine() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public List<String> getAnalog() {
        return analog;
    }

    public void setAnalog(List<String> analog) {
        this.analog = analog;
    }

    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medicine medicine = (Medicine) o;
        return Objects.equals(name, medicine.name) &&
                group == medicine.group &&
                Objects.equals(analog, medicine.analog) &&
                Objects.equals(version, medicine.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, group, analog, version);
    }

    @Override
    public String toString() {
        return "Medicine\n\t" +
                "name: " + name  +
                "\n\tgroup: " + group +
                "\n\tanalog: " + analog +
                "\n\tversion: " + version ;
    }
}
