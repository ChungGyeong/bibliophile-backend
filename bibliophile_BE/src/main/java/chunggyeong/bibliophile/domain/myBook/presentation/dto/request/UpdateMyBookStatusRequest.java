package chunggyeong.bibliophile.domain.myBook.presentation.dto.request;

import chunggyeong.bibliophile.domain.myBook.domain.ReadingStatus;

public record UpdateMyBookStatusRequest(
        Long myBookId,
        ReadingStatus status
) {
}
