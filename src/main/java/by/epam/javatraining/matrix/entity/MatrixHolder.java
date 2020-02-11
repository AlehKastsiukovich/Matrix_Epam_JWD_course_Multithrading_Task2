package by.epam.javatraining.matrix.entity;

public class MatrixHolder {
    private int n;
    private int[][] matrix;

    private MatrixHolder() {
        n = 5;
        matrix = new int[n][n];
    }

    private static class MatrixInstanceCreator {
        private static final by.epam.javatraining.matrix.entity.MatrixHolder INSTANCE = new MatrixHolder();
    }

    public static by.epam.javatraining.matrix.entity.MatrixHolder getInstance() {
        return MatrixInstanceCreator.INSTANCE;
    }

    public int getN() {
        return n;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setN(int n) {
        this.n = n;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }
}
