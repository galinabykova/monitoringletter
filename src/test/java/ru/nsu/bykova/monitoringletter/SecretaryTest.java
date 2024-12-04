package ru.nsu.bykova.monitoringletter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SecretaryTest {
    @Test
    void getPersonalTitleWomenTest() {
        Secretary testSecretary = new Secretary("Галина", "Павловна", Gender.WOMAN);
        Assertions.assertEquals("дорогая Галина Павловна", testSecretary.getPersonalTitle());
    }

    @Test
    void getPersonalTitleMenTest() {
        Secretary testSecretary = new Secretary("Денис", "Романович", Gender.MAN);
        Assertions.assertEquals("дорогой Денис Романович", testSecretary.getPersonalTitle());
    }

    @Test
    void getPersonalTitleWithoutLastNameTest() {
        Secretary testSecretary = new Secretary("Денис", "", Gender.MAN);
        Assertions.assertEquals("дорогой Денис", testSecretary.getPersonalTitle());
    }
}