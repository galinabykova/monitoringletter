package ru.nsu.bykova.monitoringletter;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

class ReportCreator {
    private final static String BODY_TEMPLATE = """
            Исчезли следующие страницы:
            %s
            Появились следующие новые страницы:
            %s
            Изменились следующие страницы:
            %s""";
    private final static String NOT_CONTENTS = "нет изменений";

    static String createReport(Map<String, ?> yesterdayTable, Map<String, ?> todayTable) {
        String removedURLs = getRemovedURL(yesterdayTable, todayTable);
        String newURLs = getNewURL(yesterdayTable, todayTable);
        String changedURLs = getChangedURL(yesterdayTable, todayTable);
        return String.format(BODY_TEMPLATE, removedURLs, newURLs, changedURLs);
    }

    static String keySetSubtractionAsString(Map<String, ?> minuend, Map<String, ?> subtrahend, String delimiter) {
        String subResult = minuend.keySet().stream()
                .filter(yesterdayKey -> !(subtrahend.containsKey(yesterdayKey)))
                .collect(Collectors.joining(delimiter));

        return postProcessString(subResult);
    }

    static String getRemovedURL(Map<String, ?> yesterdayTable, Map<String, ?> todayTable) {
        return keySetSubtractionAsString(yesterdayTable, todayTable, "\n");
    }

    static String getNewURL(Map<String, ?> yesterdayTable, Map<String, ?> todayTable) {
        return keySetSubtractionAsString(todayTable, yesterdayTable, "\n");
    }

    static String getChangedURL(Map<String, ?> yesterdayTable, Map<String, ?> todayTable) {
        String changed = yesterdayTable.entrySet().stream()
                .filter(
                        yesterdayEntry ->
                            todayTable.containsKey(yesterdayEntry.getKey())
                            &&
                            !Objects.equals(yesterdayEntry.getValue(), (todayTable.get(yesterdayEntry.getKey())))
                )
                .map(Map.Entry::getKey)
                .collect(Collectors.joining("\n"));

        return postProcessString(changed);
    }

    private static String postProcessString(String s) {
        if (s.isEmpty()) {
            return NOT_CONTENTS;
        }
        return s;
    }
}
