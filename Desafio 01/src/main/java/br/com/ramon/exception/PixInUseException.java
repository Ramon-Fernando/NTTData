package br.com.ramon.exception;

public class PixInUseException extends RuntimeException {

    public PixInUseException(String message) {
        super(message);
    }
}
