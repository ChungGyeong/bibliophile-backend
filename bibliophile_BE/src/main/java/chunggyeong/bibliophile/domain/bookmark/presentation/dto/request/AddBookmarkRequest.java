package chunggyeong.bibliophile.domain.bookmark.presentation.dto.request;

import jakarta.validation.constraints.NotNull;

public record AddBookmarkRequest(
    @NotNull
    Long bookId
) {
}
