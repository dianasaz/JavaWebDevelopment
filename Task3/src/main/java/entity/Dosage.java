package entity;

import java.util.Random;

public class Dosage {
    private int dose;
    private int period;

    public Dosage(int dose, int period) {
        this.dose = dose;
        this.period = period;
    }

    public Dosage(){}

    public int getDose() {
        return dose;
    }

    public void setDose(int dose) {
        this.dose = dose;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    @Override
    public String toString() {
        return " dose:" + dose +
                " period: " + period;
    }
}
