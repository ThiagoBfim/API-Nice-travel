package com.nicetravel.nicetravel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Configuration
public class Formatter {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    @Bean
    public org.springframework.format.Formatter<LocalTime> localDateTimeFormatter() {
        return new org.springframework.format.Formatter<LocalTime>() {
            @Override
            public LocalTime parse(String text, Locale locale) {
                return LocalTime.parse(text, DATE_TIME_FORMATTER);
            }

            @Override
            public String print(LocalTime object, Locale locale) {
                return DATE_TIME_FORMATTER.format(object);
            }
        };
    }
}
