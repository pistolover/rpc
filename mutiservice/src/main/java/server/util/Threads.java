package server.util;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

public class Threads {

    public static ThreadFactory makeName(final String name) {
        final AtomicLong couter = new AtomicLong(-1);
        return new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, String.format("%s-%d", name, couter.incrementAndGet()));
            }
        };
    }
}
