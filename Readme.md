Основной метод createLetter содержится в классе MonitoringLetterCreator:

```java
public static String createLetter(Map<String, String> yesterdayTable, Map<String, String> todayTable, Receiver receiver) {
    //...
}
```

В задании не было указано, 
в каких типах данных хранятся URL и HTML-страница.
Я хотела использовать java.net.URL для ключей 
и Document из библиотеки jsoup для значений, 
но, так как столкнулась с тем,
что URL не рекомендуется использовать как ключ в хэш-таблице,
решила, что, раз тип данных не был указан в задании, 
наверно, это не критично и использовала простые строки.
Тем более, что сравнение html-страниц, скорее всего, 
реализовано в библиотеке.

Для написания письма мне нужно обращение к секретарю, 
поэтому я создала интерфейс Receiver для получения необходимой информации
и его реализацию Secretary.

Написала unit-тесты для методов с нетривиальной реализацией 
и документацию для публичных классов и методов.

Некоторые методы имеют package-private видимость,
чтобы их можно было покрыть тестами.