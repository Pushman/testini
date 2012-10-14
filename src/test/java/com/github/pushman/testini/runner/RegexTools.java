package com.github.pushman.testini.runner;

public final class RegexTools {

    public static final String QUOTE_START = "\\Q";
    public static final String QUOTE_END = "\\E";
    private static final int LAST_ASCII_CODEPOINT = 127;

    private RegexTools() {
    }

    public static String unicodeCaseInsensitiveQuoted(String query) {
        final int length = query.length();
        StringBuilder insensitiveQueryBuilder = new StringBuilder();
        boolean quotingStarted = false;

        for (int i = 0; i < length; i++) {
            int characterCodePoint = query.codePointAt(i);
            if (characterCodePoint <= LAST_ASCII_CODEPOINT) {
                if (!quotingStarted) {
                    insensitiveQueryBuilder.append(QUOTE_START);
                    quotingStarted = true;
                }
                insensitiveQueryBuilder.append(query.charAt(i));
            } else {
                if (quotingStarted) {
                    insensitiveQueryBuilder.append(QUOTE_END);
                    quotingStarted = false;
                }
                insensitiveQueryBuilder.append("[");
                insensitiveQueryBuilder.append(Character.toChars(Character.toLowerCase(characterCodePoint)));
                insensitiveQueryBuilder.append(Character.toChars(Character.toUpperCase(characterCodePoint)));
                insensitiveQueryBuilder.append("]");
            }
        }
        if (quotingStarted) {
            insensitiveQueryBuilder.append(QUOTE_END);
        }
        return insensitiveQueryBuilder.toString();
    }
}
