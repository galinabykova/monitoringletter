package ru.nsu.bykova.monitoringletter;

class LetterCreator {
    private final static String LETTER_TEMPLATE =
            """
                    Здравствуйте, %s,
                    
                    %s

                    С уважением,
                    %s""";

    static String createLetter(String greeting, String body, String signature) {
        return String.format(
                LETTER_TEMPLATE, greeting, body, signature
        );
    }
}
