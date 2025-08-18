package br.com.ramon.exception;

public class NoFundsEnoughException extends RuntimeException {

    public NoFundsEnoughException(String message) {
        super(message);
    }
}
