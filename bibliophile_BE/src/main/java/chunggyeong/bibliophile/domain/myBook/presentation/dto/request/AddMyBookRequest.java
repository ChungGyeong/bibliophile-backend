package chunggyeong.bibliophile.domain.myBook.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AddMyBookRequest(
        @NotBlank
        Long bookId
) {
}
