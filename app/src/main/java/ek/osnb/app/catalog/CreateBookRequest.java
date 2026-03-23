package ek.osnb.app.catalog;

import jakarta.validation.constraints.NotBlank;

public record CreateBookRequest(
        @NotBlank String title,
        @NotBlank String isbn
) {
}
