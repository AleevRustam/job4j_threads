package ru.job4j.pools;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ParallelSearchTest {

    @Test
    public void testIntegerArraySmallSize() {
        Integer[] array = {1, 2, 3, 4, 5};
        ParallelSearch<Integer> search = new ParallelSearch<>();
        int index = search.search(array, 3);
        assertThat(index).isEqualTo(2);
    }

    @Test
    public void testIntegerArrayLargeSize() {
        ParallelSearch<Integer> search = new ParallelSearch<>();
        Integer[] array = new Integer[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        int index = search.search(array, 50);
        assertThat(index).isEqualTo(50);
    }

    @Test
    public void testStringArraySmallSize() {
        ParallelSearch<String> search = new ParallelSearch<>();
        String[] array = {"apple", "banana", "cherry"};
        int index = search.search(array, "banana");
        assertThat(index).isEqualTo(1);
    }

    @Test
    public void testStringArrayLargeSize() {
        ParallelSearch<String> search = new ParallelSearch<>();
        String[] array = new String[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = "string" + i;
        }
        int index = search.search(array, "string50");
        assertThat(index).isEqualTo(50);
    }

    @Test
    public void testElementNotFound() {
        ParallelSearch<Integer> search = new ParallelSearch<>();
        Integer[] array = {1, 2, 3, 4, 5};
        int index = search.search(array, 10);
        assertThat(index).isEqualTo(-1);
    }
}