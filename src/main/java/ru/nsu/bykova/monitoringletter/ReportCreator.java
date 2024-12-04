package ru.nsu.bykova.monitoringletter;

import java.util.Map;

class ReportCreator {
    private final static String BODY_TEMPLATE = """
            Исчезли следующие страницы:
            %s
            Появились следующие новые страницы:
            %s
            Изменились следующие страницы:
            %s""";
    private final static String NOT_CONTENTS = "нет изменений";

    static String createReport(Map<String, String> yesterdayTable, Map<String, String> todayTable) {
        String removedURLs = getRemovedURL(yesterdayTable, todayTable);
        String newURLs = getNewURL(yesterdayTable, todayTable);
        String changedURLs = getChangedURL(yesterdayTable, todayTable);
        return String.format(BODY_TEMPLATE, removedURLs, newURLs, changedURLs);
    }

    static String keySetSubtractionAsString(Map<String, ?> minuend, Map<String, ?> subtrahend, String delimiter) {
        StringBuilder sb = minuend.keySet().stream()
                .filter(
                yesterdayKey -> !(subtrahend.containsKey(yesterdayKey)))
                .reduce(
                    new StringBuilder(),
                    (acc, a) -> acc.append(a).append(delimiter),
                    StringBuilder::append);
        return postProcessingString(sb);
    }

    static String getRemovedURL(Map<String, ?> yesterdayTable, Map<String, ?> todayTable) {
        return keySetSubtractionAsString(yesterdayTable, todayTable, "\n");
    }

    static String getNewURL(Map<String, ?> yesterdayTable, Map<String, ?> todayTable) {
        return keySetSubtractionAsString(todayTable, yesterdayTable, "\n");
    }

    static String getChangedURL(Map<String, String> yesterdayTable, Map<String, String> todayTable) {
        StringBuilder changed = new StringBuilder();
        for (var yesterdayEntry : yesterdayTable.entrySet()) {
            if (!todayTable.containsKey(yesterdayEntry.getKey())) {
                continue;
            }
            if (!yesterdayEntry.getValue().equals(todayTable.get(yesterdayEntry.getKey()))) {
                changed.append(yesterdayEntry.getKey());
                changed.append("\n");
            }
        }
        return postProcessingString(changed);
    }

    private static String postProcessingString(StringBuilder sb) {
        if (sb.isEmpty()) {
            return NOT_CONTENTS;
        } else {
            sb.deleteCharAt(sb.length() - 1);
            return sb.toString();
        }
    }
}

