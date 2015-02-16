package com.gl.training.utils;

import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public enum Language {
    EN(Locale.ENGLISH),
    FR(Locale.FRENCH),
    IT(Locale.ITALIAN),
    RU(Locale.forLanguageTag("ru-RU")),
    UA(Locale.forLanguageTag("uk-UA")),
    DE(Locale.GERMAN);

    private final Locale current;

    Language(Locale locale) {
        current = locale;
    }

    @NotNull
    public Locale toLocale() {
        return current;
    }

    @NotNull
    public static Language getCurrentLanguage() {
        return Language.valueOf(System.getProperty("lang", "en").toUpperCase());
    }
}
