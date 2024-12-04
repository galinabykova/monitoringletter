package ru.nsu.bykova.monitoringletter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

class ReportCreatorTest {
    @Nested
    class KeySetSubtractionAsStringTest {
        @Test
        void CommonTest() {
            HashMap<String, String> minuend = new HashMap<>();
            minuend.put("https://one.ru", "");
            minuend.put("https://two.ru", "");
            HashMap<String, String> subtrahend = new HashMap<>();
            subtrahend.put("https://one.ru", "");
            subtrahend.put("https://third.ru", "");
            var result = ReportCreator.keySetSubtractionAsString(minuend, subtrahend, "\n");
            Assertions.assertEquals("https://two.ru", result);
        }

        @Test
        void EmptyTest() {
            HashMap<String, String> minuend = new HashMap<>();
            HashMap<String, String> subtrahend = new HashMap<>();
            var result = ReportCreator.keySetSubtractionAsString(minuend, subtrahend, "\n");
            Assertions.assertEquals("нет изменений", result);
        }

        @Test
        void InputTest() {
            HashMap<String, String> minuend = new HashMap<>();
            minuend.put("https://one.ru", "");
            HashMap<String, String> subtrahend = new HashMap<>();
            subtrahend.put("https://one.ru", "");
            var result = ReportCreator.keySetSubtractionAsString(minuend, subtrahend, "\n");
            Assertions.assertEquals("нет изменений", result);
        }

        @Test
        void TwoUrlTest() {
            HashMap<String, String> minuend = new HashMap<>();
            minuend.put("https://one.ru", "");
            minuend.put("https://two.ru", "");
            minuend.put("https://third.ru", "");
            HashMap<String, String> subtrahend = new HashMap<>();
            subtrahend.put("https://one.ru", "");
            var result = ReportCreator.keySetSubtractionAsString(minuend, subtrahend, " ");
            Assertions.assertTrue(result.contains("https://two.ru"));
            Assertions.assertTrue(result.contains("https://third.ru"));
            Assertions.assertTrue(result.contains(" "));
        }

        @Test
        void ChangedTest() {
            HashMap<String, String> minuend = new HashMap<>();
            minuend.put("https://one.ru", "one");
            minuend.put("https://two.ru", "two");
            HashMap<String, String> subtrahend = new HashMap<>();
            subtrahend.put("https://one.ru", "new one");
            subtrahend.put("https://third.ru", "new two");
            var result = ReportCreator.keySetSubtractionAsString(minuend, subtrahend, "\n");
            Assertions.assertEquals("https://two.ru", result);
        }
    }

    @Nested
    class GetChangedURLTest {
        @Test
        void CommonTest() {
            HashMap<String, String> hashMap1 = new HashMap<>();
            hashMap1.put("https://one.ru", "one");
            hashMap1.put("https://two.ru", "two");
            HashMap<String, String> hashMap2 = new HashMap<>();
            hashMap2.put("https://one.ru", "new one");
            hashMap2.put("https://third.ru", "third");
            var result = ReportCreator.getChangedURL(hashMap1, hashMap2);
            Assertions.assertEquals("https://one.ru", result);
        }

        @Test
        void EmptyInputTest() {
            HashMap<String, String> hashMap1 = new HashMap<>();
            hashMap1.put("https://one.ru", "one");
            hashMap1.put("https://two.ru", "two");
            HashMap<String, String> hashMap2 = new HashMap<>();
            hashMap2.put("https://one.ru", "one");
            hashMap2.put("https://third.ru", "third");
            var result = ReportCreator.getChangedURL(hashMap1, hashMap2);
            Assertions.assertEquals("нет изменений", result);
        }

        @Test
        void EmptyMapsTest() {
            HashMap<String, String> hashMap1 = new HashMap<>();
            HashMap<String, String> hashMap2 = new HashMap<>();
            var result = ReportCreator.getChangedURL(hashMap1, hashMap2);
            Assertions.assertEquals("нет изменений", result);
        }
    }

    @Nested
    class CreateReportTest {
        @Test
        void commonTest() {
            HashMap<String, String> yesterdayMap = new HashMap<>();
            yesterdayMap.put("https://one.ru", "<html><head><title>ONE</title></head><body><p>OLD</p></body></html>");
            yesterdayMap.put("https://two.ru", "<html><head><title>TWO</title></head><body><p>OLD</p></body></html>");

            HashMap<String, String> todayMap = new HashMap<>();
            todayMap.put("https://one.ru", "<html><head><title>ONE</title></head><body><p>NEW</p></body></html>");
            todayMap.put("https://third.ru", "<html><head><title>THIRD</title></head><body><p>NEW</p></body></html>");

            String report = ReportCreator.createReport(yesterdayMap, todayMap);
            Assertions.assertEquals("""
            Исчезли следующие страницы:
            https://two.ru
            Появились следующие новые страницы:
            https://third.ru
            Изменились следующие страницы:
            https://one.ru""", report);
        }

        @Test
        void emptyInputTest() {
            HashMap<String, String> yesterdayMap = new HashMap<>();
            HashMap<String, String> todayMap = new HashMap<>();
            String report = ReportCreator.createReport(yesterdayMap, todayMap);
            Assertions.assertEquals("""
            Исчезли следующие страницы:
            нет изменений
            Появились следующие новые страницы:
            нет изменений
            Изменились следующие страницы:
            нет изменений""", report);
        }
    }
}