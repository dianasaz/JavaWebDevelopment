package entity;

public class Matrix {
    private int n;
    private int[][] matrix;

    public Matrix(String line, int n){
        matrix = new int[n][];
        matrix[0] = new int[]{0, 7, 4, 18, 32};
        matrix[1] = new int[]{2, 0, 78, 21, 22};
        matrix[2] = new int[]{13, 7, 0, 34, 56};
        matrix[3] = new int[]{7, 90, 21, 0, 32};
        matrix[4] = new int[]{9, 14, 56, 9, 0};
    }

    public int getElement(int x, int y){
        return matrix[x][y];
    }

    public void setElement(int x, int y, int el){
        matrix[x][y] = el;
    }

    public int getSize(){
        return matrix.length;
    }
}
