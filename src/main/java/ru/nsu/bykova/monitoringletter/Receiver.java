package ru.nsu.bykova.monitoringletter;

/**
 * интерфейс для получателя письма
 */
public interface Receiver {
    /**
     * @return строку обращения для получателя
     */
    String getPersonalTitle();
}