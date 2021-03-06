package com.mini.core.mvc.validation;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;

import javax.validation.ConstraintViolation;
import java.util.Locale;

import static java.util.Optional.ofNullable;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class ValidateException extends RuntimeException {
    private final HttpStatus status;
    private final Object[] args;
    private final Integer code;
    private final String field;

    public ValidateException(String message, HttpStatus status, Integer code, Object[] args, String field) {
        super(message);
        this.status = status;
        this.field = field;
        this.code = code;
        this.args = args;
    }

    public ValidateException(String message, HttpStatus status, String field) {
        this(message, status, null, new Object[0], field);
    }

    public ValidateException(ConstraintViolation<?> validation, HttpStatus status) {
        this(validation.getMessage(), status, validation.getPropertyPath().toString());
    }

    public ValidateException(ObjectError error, HttpStatus status) {
        this(error.getDefaultMessage(), status, error.getObjectName());
    }

    public final String getMessage(@Nullable MessageSource source, Locale locale) {
        String msg = ofNullable(getMessage()).orElse("Bad Request").strip();
        if (source == null || !msg.startsWith("{") || !msg.endsWith("}")) {
            return msg;
        }
        String message = msg.substring(1, msg.length() - 1);
        return source.getMessage(message, args, locale);
    }

    @NotNull
    public final HttpStatus getStatus() {
        if (this.status == null) {
            return BAD_REQUEST;
        }
        return status;
    }

    public final Object[] getArgs() {
        return args;
    }

    public final Integer getCode() {
        return code;
    }

    public final String getField() {
        return field;
    }
}
