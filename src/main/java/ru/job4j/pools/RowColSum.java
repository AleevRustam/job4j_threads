package ru.job4j.pools;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RowColSum {
    public static class Sums {

        private int rowSum;
        private int colSum;

        public int getRowSum() {
            return rowSum;
        }

        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public int getColSum() {
            return colSum;
        }

        public void setColSum(int colSum) {
            this.colSum = colSum;
        }

        public static Sums[] sum(int[][] matrix) {
            int size = matrix.length;
            Sums[] sums = new Sums[size];
            for (int i = 0; i < size; i++) {
                sums[i] = new Sums();
                int rowSum = 0;
                int colSum = 0;
                for (int j = 0; j < size; j++) {
                    rowSum += matrix[i][j];
                    colSum += matrix[j][i];
                }
                sums[i].setRowSum(rowSum);
                sums[i].setColSum(colSum);
            }
            return sums;
        }

        public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
            int size = matrix.length;
            Sums[] sums = new Sums[size];
            Map<Integer, CompletableFuture<Sums>> futures = new HashMap<>();

            for (int i = 0; i < size; i++) {
                final int index = i;
                futures.put(i, CompletableFuture.supplyAsync(() -> {
                    int rowSum = 0;
                    int colSum = 0;
                    for (int j = 0; j < size; j++) {
                        rowSum += matrix[index][j];
                        colSum += matrix[j][index];
                    }
                    Sums sum = new Sums();
                    sum.setRowSum(rowSum);
                    sum.setColSum(colSum);
                    return sum;
                }));
            }
            for (Integer key : futures.keySet()) {
                sums[key] = futures.get(key).get();
            }
            return sums;
        }
    }
}
