package entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;


public class Matrix {
    private final Logger logger = LogManager.getLogger(Matrix.class);
    private int[][] matrix;
    private ReentrantLock lock;

    public Matrix(int[][] h, ReentrantLock lock){
        this.matrix = h;
        this.lock = lock;
        clearDiagonal();
    }

    public int getElement(int x, int y){
        return matrix[x][y];
    }

    public void setElement(int x, int y, int el){
        lock.lock();
        try {
            matrix[x][y] = el;
        } finally {
            lock.unlock();
        }
    }

    public int getSize(){
        return matrix.length;
    }

    private void clearDiagonal(){
        for (int i = 0; i < matrix.length; i++){
            setElement(i, i, 0);
        }
    }

    public void ChangeValue(int element){
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][i] == 0) {
                setElement(i, i, element);
                logger.log(Level.INFO, "Element[" + i + "][" + i + "] was changed to " + element);
            }
        }
    }

    public static int[][] makeMatrix(List<String> lines){
        int[][] m = new int[lines.size()][];

        for (int a = 0; a < lines.size(); a++){
            String[] res = lines.get(a).split(" ");
            int[] d = new int[res.length];
            for (int i = 0; i < res.length; i++) {
                int s = Integer.valueOf(res[i]);
                d[i] = s;
            }
            m[a] = d;
        }
        return m;
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
}
