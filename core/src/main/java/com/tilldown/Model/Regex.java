package com.tilldown.Model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Regex {
    password("^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%&*()_]).{8,}$"),
    ;

    private final String pattern;

    Regex(String pattern) {
        this.pattern = pattern;
    }

    public Matcher getMatcher(String command) {
        Matcher matcher = Pattern.compile(pattern).matcher(command);
        if (matcher.matches()) return matcher;
        return null;
    }
}
