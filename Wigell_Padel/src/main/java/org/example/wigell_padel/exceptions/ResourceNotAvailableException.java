package org.example.wigell_padel.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
public class ResourceNotAvailableException extends RuntimeException{
    private String name;
    private String field;
    private Object value;

    public ResourceNotAvailableException(String name, String field, Object value) {
        super(String.format("%s with %s '%s' is not available", name, field, value));
        this.name = name;
        this.field = field;
        this.value = value;
    }

    public String getItem() {
        return name;
    }

    public String getField() {
        return field;
    }

    public Object getValue() {
        return value;
    }
}
