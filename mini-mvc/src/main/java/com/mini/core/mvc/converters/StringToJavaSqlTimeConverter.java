package com.mini.core.mvc.converters;


import org.jetbrains.annotations.NotNull;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static java.lang.Long.parseLong;
import static java.time.format.DateTimeFormatter.ofPattern;

@ConfigurationPropertiesBinding
public class StringToJavaSqlTimeConverter extends AbstractDateConverter<java.sql.Time> {
    public StringToJavaSqlTimeConverter(DataConvertersFormat convertersFormat) {
        super(convertersFormat);
    }

    @Override
    public java.sql.Time convert(@NotNull String source) {
        try {
            DateTimeFormatter format = ofPattern(getTimeFormat());
            LocalTime time = LocalTime.parse(source, format);
            return java.sql.Time.valueOf(time);
        } catch (DateTimeParseException ignored) {
        }
        return new java.sql.Time(parseLong(source));
    }
}
