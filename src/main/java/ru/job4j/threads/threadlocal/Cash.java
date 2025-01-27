package ru.job4j.threads.threadlocal;

public class Cash {
    private static Cash cash;

    public static synchronized Cash getInstance() {
        if (cash == null) {
            cash = new Cash();
        }
        return cash;
    }
}
