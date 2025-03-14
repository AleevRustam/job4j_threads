package ru.job4j.pools;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RowColSum {

    public Sums[] sum(int[][] matrix) {
        int size = matrix.length;
        Sums[] sums = new Sums[size];
        for (int i = 0; i < size; i++) {
            int rowSum = 0;
            int colSum = 0;
            for (int j = 0; j < size; j++) {
                rowSum += matrix[i][j];
                colSum += matrix[j][i];
            }
            sums[i] = new Sums(rowSum, colSum);
        }
        return sums;
    }

    public Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
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

                return new Sums(rowSum, colSum);
            }));
        }
        for (Integer key : futures.keySet()) {
            sums[key] = futures.get(key).get();
        }
        return sums;
    }
}
