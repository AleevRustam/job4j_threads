package ru.job4j.pools;

public class ParallelSearch<T extends Comparable<T>> {

    public int search(T[] array, T target) {
           return SearchIndex.search(array, target);
    }
}
