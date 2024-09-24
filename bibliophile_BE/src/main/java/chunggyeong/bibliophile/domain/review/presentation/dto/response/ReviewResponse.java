package chunggyeong.bibliophile.domain.review.presentation.dto.response;

import chunggyeong.bibliophile.domain.review.domain.Review;

import java.time.LocalDateTime;

public record ReviewResponse(
        Long reviewId,
        String content,
        int star,
        String nickname,
        boolean isHost,
        LocalDateTime createdDate,
        LocalDateTime lastModifyDate
) {
    public ReviewResponse(Review review, String nickname, boolean isHost) {
        this(review.getId(), review.getContent(), review.getStar(), nickname, isHost, review.getCreatedDate(), review.getLastModifyDate());
    }
}
