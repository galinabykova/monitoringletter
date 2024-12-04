package ru.nsu.bykova.monitoringletter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

class MonitoringLetterCreatorTest {
    @Test
    void createLetterTest() {
        Receiver receiver = new Secretary("Галина", "Павловна", Gender.WOMAN);

        HashMap<String, String> yesterdayMap = new HashMap<>();
        yesterdayMap.put("https://one.ru", "<html><head><title>ONE</title></head><body><p>OLD</p></body></html>");
        yesterdayMap.put("https://two.ru", "<html><head><title>TWO</title></head><body><p>OLD</p></body></html>");

        HashMap<String, String> todayMap = new HashMap<>();
        todayMap.put("https://one.ru", "<html><head><title>ONE</title></head><body><p>NEW</p></body></html>");
        todayMap.put("https://third.ru", "<html><head><title>THIRD</title></head><body><p>NEW</p></body></html>");

        String report = MonitoringLetterCreator.createLetter(yesterdayMap, todayMap, receiver);
        Assertions.assertEquals("""
            Здравствуйте, дорогая Галина Павловна,
            
            Исчезли следующие страницы:
            https://two.ru
            Появились следующие новые страницы:
            https://third.ru
            Изменились следующие страницы:
            https://one.ru
            
            С уважением,
            автоматизированная система
            мониторинга.""", report);
    }
}