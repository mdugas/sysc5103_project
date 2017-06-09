package ca.carleton.sce.util;

import java.util.function.Consumer;

public class Mutex<T> {
    private final Object lock = new Object();
    private final T content;

    public Mutex(T content) {
        this.content = content;
    }

    public void lock(Consumer<T> consumer) {
        synchronized (lock) {
            consumer.accept(content);
        }
    }
}
