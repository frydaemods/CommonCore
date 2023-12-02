package dev.frydae.beguild.utils;

import java.util.regex.Pattern;

public final class Patterns {
    public static final Pattern COLON = Pattern.compile(":");
    public static final Pattern PIPE = Pattern.compile("\\|");
    public static final Pattern NEWLINE = Pattern.compile("\n");
    public static final Pattern COMMA = Pattern.compile(",");
    public static final Pattern COMMASPACE = Pattern.compile("[, ]");
    public static final Pattern PERIOD = Pattern.compile("\\.");
    public static final Pattern DASH = Pattern.compile("-");
    public static final Pattern SPACE = Pattern.compile("\\s+");
    public static final Pattern EQUALS = Pattern.compile("=");
    public static final Pattern SEMICOLON = Pattern.compile(";");
    public static final Pattern AND = Pattern.compile("&");
    public static final Pattern SECTION = Pattern.compile("§");
    public static final Pattern SECTION_REPEATED = Pattern.compile("§+");
    public static final Pattern DOUBLE_SECTION = Pattern.compile("§§");
    public static final Pattern USER_ID_SIGN = Pattern.compile("^#user\\|\\d+$");
    public static final Pattern CHAT_COLOR_CODES = Pattern.compile("(?<!\\\\)&(?=[0-9a-fklmnor])");
    public static final Pattern BROKEN_SIGN_PATTERN = Pattern.compile("\\{\"text\":\"(.*?)\"}");
    public static final Pattern VALID_EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$");
    public static final Pattern URL = Pattern.compile("^((http[s]?|ftp):/)?/?([^:/\\s]+)((/\\w+)*/)([\\w\\-.]+[^#?\\s]+)(.*)?(#[\\w\\-]+)?$");

    public static Pattern getPattern(String pattern) {
        return Pattern.compile(pattern);
    }
}
