package io.github.devandref.kafka_consumer_email_notification.exception;

public class NoRetryableException extends RuntimeException{

    public NoRetryableException(String message) {
        super(message);
    }

    public NoRetryableException(Throwable cause) {
        super(cause);
    }
}

