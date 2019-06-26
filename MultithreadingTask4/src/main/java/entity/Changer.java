package entity;

public class Changer implements Runnable {
    private Matrix matrix;
    private int element;

    public Changer(int x, Matrix matrix) {
        this.element = x;
        this.matrix = matrix;
    }

    public void run() {
        matrix.ChangeValue(element);
    }
}
