package com.acueducto.arpa.domain.model.vo;

import java.util.Objects;

public final class Comment {
    private final String value;

    public Comment(String value) {
        if (value != null && value.length() > 255) {
            throw new IllegalArgumentException("Comment must be at most 255 characters");
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
        Comment comment = (Comment) o;
        return Objects.equals(value, comment.value);
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