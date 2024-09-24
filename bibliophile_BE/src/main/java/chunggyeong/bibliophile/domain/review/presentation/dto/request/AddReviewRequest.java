package chunggyeong.bibliophile.domain.review.presentation.dto.request;

public record AddReviewRequest(
        String content,
        int star,
        Long bookId
) {
}
