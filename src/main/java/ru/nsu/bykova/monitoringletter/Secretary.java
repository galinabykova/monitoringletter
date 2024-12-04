package ru.nsu.bykova.monitoringletter;

/**
 * Хранит информацию о секретаре.
 * Возможная реализация получателя письма.
 */
public class Secretary implements Receiver{
    private final static String WOMAN_ADJECTIVE = "дорогая";
    private final static String MAN_ADJECTIVE = "дорогой";

    private final String name;
    private final String lastname;
    private final Gender gender;

    /**
     * Конструктор.
     * @param name имя секретаря
     * @param lastname отчество секретаря
     * @param gender гендер секретаря (для формирования обращения)
     */
    public Secretary(String name, String lastname, Gender gender) {
        this.name = name;
        this.lastname = lastname;
        this.gender = gender;
    }

    @Override
    public String getPersonalTitle() {
        String adjective = switch (gender) {
            case MAN -> MAN_ADJECTIVE;
            case WOMAN -> WOMAN_ADJECTIVE;
        };
        return String.format("%s %s %s", adjective, name, lastname).trim();
    }
}
