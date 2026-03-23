package ek.osnb.app.catalog;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
class ISBN {
    @Column(name = "isbn", unique = true, nullable = false, length = 13)
    private String value;

    protected ISBN() {
    }

    private ISBN(String value) {
        String normalized = normalize(value);

        if (!isValidIsbn13(normalized)) {
            throw new IllegalArgumentException("ISBN must be a valid ISBN-13");
        }
        this.value = value;
    }

    static ISBN of(String raw) {
        return new ISBN(raw);
    }

    private static String normalize(String raw) {
        if (raw == null) {
            throw new IllegalArgumentException("ISBN must not be null");
        }

        String normalized = raw.replace("-", "")
                .replace(" ", "")
                .trim();

        if (normalized.isEmpty()) {
            throw new IllegalArgumentException("ISBN must not be blank");
        }

        return normalized;
    }

    private static boolean isValidIsbn13(String isbn) {
        if (!isbn.matches("\\d{13}")) {
            return false;
        }

        int sum = 0;
        for (int i = 0; i < 12; i++) {
            int digit = Character.getNumericValue(isbn.charAt(i));
            sum += (i % 2 == 0) ? digit : digit * 3;
        }

        int expectedCheckDigit = (10 - (sum % 10)) % 10;
        int actualCheckDigit = Character.getNumericValue(isbn.charAt(12));

        return expectedCheckDigit == actualCheckDigit;
    }

    String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

}
