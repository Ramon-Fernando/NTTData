package br.com.ramon.hangman.exception;

public class LetterAlreadyInputtedException extends RuntimeException {
    public LetterAlreadyInputtedException(String message) {
        super(message);
    }
}
