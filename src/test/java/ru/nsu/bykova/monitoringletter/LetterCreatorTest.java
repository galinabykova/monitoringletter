package ru.nsu.bykova.monitoringletter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LetterCreatorTest {
    @Test
    void LetterCreateTest() {
        String letter = LetterCreator.createLetter("Анна", "завершено", "Маша");
        Assertions.assertEquals("""
                    Здравствуйте, Анна,\n\nзавершено\n\nС уважением,\nМаша""", letter);
    }
}