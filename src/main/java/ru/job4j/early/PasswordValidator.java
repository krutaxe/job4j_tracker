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

        boolean upper = false;
        boolean lower = false;
        boolean number = false;
        boolean special = false;

        for (int i = 0; i < password.length(); i++) {
            if (Character.isUpperCase(password.codePointAt(i))) {
                upper = true;
            }
            if (Character.isLowerCase(password.codePointAt(i))) {
                lower = true;
            }
            if (Character.isDigit(password.codePointAt(i))) {
                number = true;
            }
            if (password.charAt(i) > 32 && password.charAt(i) < 48) {
                special = true;
            }
            if (upper && lower && number && special) {
                break;
            }
        }

        if (!upper) {
            throw new IllegalArgumentException("Password should contain "
                    + "at least one uppercase letter");
        }
        if (!lower) {
            throw new IllegalArgumentException("Password should contain "
                    + "at least one lowercase letter");
        }
        if (!number) {
            throw new IllegalArgumentException("Password should contain at least one figure");
        }
        if (!special) {
            throw new IllegalArgumentException("Password should contain "
                    + "at least one special symbol");
        }
        checkSubString(password);
        return password;
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
}
