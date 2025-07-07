package com.acueducto.arpa.domain.model.vo;

import java.util.Objects;

public final class Make {
    private final String value;

    public Make(String value) {
        if (value == null || value.isBlank() || value.length() > 100) {
            throw new IllegalArgumentException("Make must be non-empty and at most 100 characters");
        }
        this.value = value;
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Make make = (Make) o;
        return value.equals(make.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
} 