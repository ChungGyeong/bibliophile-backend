package chunggyeong.bibliophile.domain.myBook.presentation.dto.request;

import jakarta.validation.constraints.NotNull;

public record AddMyBookRequest(
        @NotNull
        Long bookId
) {
}
