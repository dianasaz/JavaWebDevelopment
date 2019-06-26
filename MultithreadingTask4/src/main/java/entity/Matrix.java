package entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;


public class Matrix {

    private final Logger logger = LogManager.getLogger(Matrix.class);
    private Element[][] matrix;

    public Matrix(Element[][] h){
        this.matrix = h;
        clearDiagonal();
    }

    public int getElement(int x, int y){
        return matrix[x][y].getEl();
    }

    public void setElement(int x, int y, int el){
        matrix[x][y].setEl(el);
    }

    private void clearDiagonal(){
        for (int i = 0; i < matrix.length; i++){
            setElement(i, i, 0);
        }
    }

    public void ChangeValue(int element){
        for (int i = 0; i < matrix.length; i++) {
            if (getElement(i, i) == 0) {
                setElement(i, i, element);
                logger.log(Level.INFO, "Element[" + i + "][" + i + "] was changed to " + element);
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    logger.log(Level.ERROR, e.getMessage());
                }
            }
        }
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                s += matrix[i][j] + " ";
            }
            s += "\n";
        }
        return s;
    }

    public int getSize(){
        return matrix.length;
    }
}
