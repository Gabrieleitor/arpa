package com.acueducto.arpa.domain.model.vo;

import java.util.Objects;

public final class Serial {
    private final String value;

    public Serial(String value) {
        if (value == null || value.isBlank() || value.length() > 50) {
            throw new IllegalArgumentException("Serial must be non-empty and at most 50 characters");
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
        Serial serial = (Serial) o;
        return value.equals(serial.value);
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