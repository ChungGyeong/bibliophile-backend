package chunggyeong.bibliophile.domain.bookmark.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AddBookmarkRequest(
    @NotBlank
    Long bookId
) {
}
