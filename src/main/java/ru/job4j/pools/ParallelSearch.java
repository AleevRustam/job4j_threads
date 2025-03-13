package ru.job4j.pools;

import java.util.concurrent.ForkJoinPool;

public class ParallelSearch<T extends Comparable<T>> {

    public int search(T[] array, T target) {
        if (array.length <= SearchIndex.ARRAY_LIMIT) {
            return linearSearch(array, target);
        } else {
            try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
                return forkJoinPool.invoke(new SearchIndex<>(array, target, 0, array.length));
            }
        }
    }

    private int linearSearch(T[] array, T target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].compareTo(target) == 0) {
                return i;
            }
        }
        return -1;
    }
}
