package chunggyeong.bibliophile.domain.myBook.presentation.dto.request;

import chunggyeong.bibliophile.domain.myBook.domain.ReadingStatus;
import jakarta.validation.constraints.NotBlank;

public record UpdateMyBookStatusRequest(
        @NotBlank
        ReadingStatus status
) {
}
