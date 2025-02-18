package ru.job4j.threads;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
public class CASCount {
    private final AtomicInteger count = new AtomicInteger();

    public void increment() {
        int currentValue;
        int newValue;
        do {
            currentValue = count.get();
            newValue = currentValue + 1;
        } while (!count.compareAndSet(currentValue, newValue));
    }

    public int get() {
        return count.get();
    }
}
