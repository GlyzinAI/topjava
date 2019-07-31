package ru.javawebinar.topjava.util;

import org.springframework.format.Formatter;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class TimesForm implements Formatter<LocalTime> {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    @Override
    public LocalTime parse(String text, Locale locale) throws ParseException {
        return StringUtils.isEmpty(text) ? null : LocalTime.parse(text, TIME_FORMATTER);
    }

    @Override
    public String print(LocalTime object, Locale locale) {
        return object.toString();
    }
}