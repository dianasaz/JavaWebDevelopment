package entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Changer implements Runnable {
    private final Logger logger = LogManager.getLogger(Changer.class);
    private Matrix matrix;
    private int element;

    public Changer(int x){
        this.element = x;
        matrix = new Matrix(" ", 5);
    }
    public void run() {
        for (int i = 0; i < matrix.getSize(); i++){
            for (int j = 0; j < matrix.getSize(); j++){
                if (matrix.getElement(i, j) == 0){
                    matrix.setElement(i, j, element);
                    logger.log(Level.INFO, "Element[" + i + "][" + j + "] was changed to" + element);
                }
            }
        }
    }
}
