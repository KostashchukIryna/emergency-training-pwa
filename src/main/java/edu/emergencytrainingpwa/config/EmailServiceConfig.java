package edu.emergencytrainingpwa.config;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailServiceConfig {
    private static final int BASE_THREADS_AMOUNT = 1;

    private static final int MAX_THREADS_AMOUNT = Runtime.getRuntime().availableProcessors();

    private static final IdleTimeout IDLE_TIMEOUT = new IdleTimeout(10, TimeUnit.SECONDS);

    private static final int MAX_TASKS_IN_QUEUE = 100;

    @Bean
    public Executor sendEmailExecutor() {
        return new ThreadPoolExecutor(
            BASE_THREADS_AMOUNT,
            MAX_THREADS_AMOUNT,
            IDLE_TIMEOUT.getIdleTime(),
            IDLE_TIMEOUT.getIdleTimeUnit(),
            new ArrayBlockingQueue<>(MAX_TASKS_IN_QUEUE));
    }

    /**
     * This class represents the amount of time needed for idle thread destruction
     * in the send email thread pool. The main purpose of this class is to ship the
     * amount of time with the according time unit, so the type system can help us
     * not to misuse one of the parameters without any relation to the another one.
     */
    @Getter
    @RequiredArgsConstructor
    private static final class IdleTimeout {
        /**
         * Keep alive time required for thread destruction.
         */
        private final long idleTime;
        /**
         * Time unit that describes the amount of time required for thread destruction.
         */
        private final TimeUnit idleTimeUnit;
    }
}
