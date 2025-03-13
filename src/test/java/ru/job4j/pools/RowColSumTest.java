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
                {7, 8 , 9}
        };
        RowColSum.Sums[] result = RowColSum.Sums.sum(matrix);
        assertThat(result[0].getRowSum()).isEqualTo(6);
        assertThat(result[1].getRowSum()).isEqualTo(15);
        assertThat(result[2].getRowSum()).isEqualTo(24);
        assertThat(result[0].getColSum()).isEqualTo(12);
        assertThat(result[1].getColSum()).isEqualTo(15);
        assertThat(result[2].getColSum()).isEqualTo(18);
    }

    @Test
    public void whenAsyncSum() throws ExecutionException, InterruptedException {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        RowColSum.Sums[] result = RowColSum.Sums.asyncSum(matrix);
        assertThat(result[0].getRowSum()).isEqualTo(6);
        assertThat(result[1].getRowSum()).isEqualTo(15);
        assertThat(result[2].getRowSum()).isEqualTo(24);
        assertThat(result[0].getColSum()).isEqualTo(12);
        assertThat(result[1].getColSum()).isEqualTo(15);
        assertThat(result[2].getColSum()).isEqualTo(18);
    }
}