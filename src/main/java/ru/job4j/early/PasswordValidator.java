package ru.job4j.early;

import org.apache.commons.lang3.StringUtils;

public class PasswordValidator {
    public static String validate(String password) {
        if (password == null) {
            throw new IllegalArgumentException("Password can't be null");
        }
        if (password.length() < 8 || password.length() > 32) {
            throw new IllegalArgumentException("Password should be length [8, 32]");
        }
        checkUpperCase(password);
        checkLowerCase(password);
        checkNumber(password);
        checkSpecialSymbol(password);
        checkSubString(password);
        return password;
    }

    public static void checkUpperCase(String password) {
        boolean rsl = false;
        for (int i = 0; i < password.length(); i++) {
            if (Character.isUpperCase(password.codePointAt(i))) {
                rsl = true;

            }
        }
        if (!rsl) {
            throw new IllegalArgumentException("Password should contain "
                    + "at least one uppercase letter");
        }
    }

    public static void checkLowerCase(String password) {
        boolean rsl = false;
        for (int i = 0; i < password.length(); i++) {
            if (Character.isLowerCase(password.codePointAt(i))) {
                rsl = true;

            }
        }
        if (!rsl) {
            throw new IllegalArgumentException("Password should contain "
                    + "at least one lowercase letter");
        }
    }

    public static void checkNumber(String password) {
        boolean rsl = false;
        for (int i = 0; i < password.length(); i++) {
            if (Character.isDigit(password.codePointAt(i))) {
                rsl = true;

            }
        }
        if (!rsl) {
            throw new IllegalArgumentException("Password should contain at least one figure");
        }
    }

    public static void checkSubString(String password) {
        String[] subString = {"qwerty", "12345", "password", "admin", "user"};
        boolean rsl = true;
        for (String s : subString) {
            if (StringUtils.containsIgnoreCase(password, s)) {
                rsl = false;
                break;
            }
        }
        if (!rsl) {
            throw new IllegalArgumentException("Password shouldn't contain substrings:"
                   + " qwerty, 12345, password, admin, user");
        }
    }

    public static void checkSpecialSymbol(String password) {
        boolean rsl = false;
        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) > 32 && password.charAt(i) < 48) {
                rsl = true;
                break;
            }
        }
        if (!rsl) {
            throw new IllegalArgumentException("Password should contain "
                   + "at least one special symbol");
        }
    }
}
