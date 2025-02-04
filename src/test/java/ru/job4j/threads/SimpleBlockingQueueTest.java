package ru.job4j.threads;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SimpleBlockingQueueTest {

    @Test
    public void whenProducerAndConsumerWorkThanQueueIsEmptyAtEnd() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(5);
        Thread producer = new Thread(
                () -> {
                    try {
                        for (int i = 0; i < 10; i++) {
                            queue.offer(i);
                            System.out.println("Produced: " + i);
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
        );
        Thread consumer = new Thread(
                () -> {
                    try {
                        for (int i = 0; i < 10; i++) {
                            Integer value = queue.poll();
                            System.out.println("Consumed: " + value);
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
        );

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        synchronized (queue) {
            Assertions.assertEquals(0, queue.getSize());
        }
    }

}