package br.com.ramon.hangman;

import br.com.ramon.hangman.model.HangmanChar;

import java.util.stream.Stream;

public class Main {

    public static void main(String... args) {
        var letter = Stream.of(args)
                .map(a -> a.toLowerCase().charAt(0))
                .map(HangmanChar::new).toList();
        System.out.println(letter);
    }
}
