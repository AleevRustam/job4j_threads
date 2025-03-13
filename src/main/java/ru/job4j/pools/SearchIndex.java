package ru.job4j.pools;

import java.util.concurrent.RecursiveTask;

public class SearchIndex<T extends Comparable<T>> extends RecursiveTask<Integer> {

    public static final int ARRAY_LIMIT = 10;
    private final T[] array;
    private final T target;
    private final int from;
    private final int to;

    public SearchIndex(T[] array, T target, int from, int to) {
        this.array = array;
        this.target = target;
        this.from = from;
        this.to = to;
    }

    @Override
    protected Integer compute() {
        if (to - from <= ARRAY_LIMIT) {
            for (int i = from; i < to; i++) {
                if (array[i].compareTo(target) == 0) {
                    return i;
                }
            }
            return -1;
        } else {
            int middle = (from + to) / 2;
            SearchIndex<T> left = new SearchIndex<>(array, target, from, middle);
            SearchIndex<T> right = new SearchIndex<>(array, target, middle, to);

            left.fork();
            int rightResult = right.compute();
            int leftResult = left.join();

            return leftResult != -1 ? leftResult : rightResult;
        }
    }
}
