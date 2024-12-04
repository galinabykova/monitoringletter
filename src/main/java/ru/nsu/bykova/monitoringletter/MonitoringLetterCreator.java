package ru.nsu.bykova.monitoringletter;

import java.util.Map;

/**
 * Класс с методом, который было необходимо реализовать
 */
public class MonitoringLetterCreator {
    private static final String SIGNATURE = "автоматизированная система\nмониторинга.";

    /***
     * конструирует письмо
     *
     * @param yesterdayTable вчерашняя хэш-таблица, ключ URL, значение страница HTML
     * @param todayTable сегодняшняя хэш-таблица, ключ URL, значение страница HTML
     * @param receiver получатель письма
     * @return письмо в виде строки в виде строки
     */
    public static String createLetter(Map<String, String> yesterdayTable, Map<String, String> todayTable,
                                      Receiver receiver) {
        return LetterCreator.createLetter(receiver.getPersonalTitle(),
                ReportCreator.createReport(yesterdayTable, todayTable),
                SIGNATURE);
    }
}