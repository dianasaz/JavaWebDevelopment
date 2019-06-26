package entity;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Changer implements Runnable {
    private final Logger logger = LogManager.getLogger(Changer.class);
    private Matrix matrix;
    private int element;
    private ReentrantLock lock;

    public Changer(int x, Matrix matrix) {
        this.element = x;
        this.matrix = matrix;
    }

    public void run() {
        matrix.ChangeValue(element);
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
    }

    public String getMatrix() {
        return matrix.toString();
    }
}
