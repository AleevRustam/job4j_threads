package ru.job4j.pools;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;

class RowColSumTest {

    @Test
    public void whenSequentialSum() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        Sums[] expected = {
          new Sums(6, 12),
          new Sums(15, 15),
          new Sums(24, 18)
        };

        RowColSum rowColSum = new RowColSum();
        Sums[] result = rowColSum.sum(matrix);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void whenAsyncSum() throws ExecutionException, InterruptedException {

        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        Sums[] expected = {
                new Sums(6, 12),
                new Sums(15, 15),
                new Sums(24, 18)
        };

        RowColSum rowColSum = new RowColSum();
        Sums[] result = rowColSum.asyncSum(matrix);

        assertThat(result).isEqualTo(expected);
    }
}