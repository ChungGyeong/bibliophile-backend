package chunggyeong.bibliophile.domain.review.presentation.dto.request;

public record UpdateReviewRequest(
        String content,
        int star
) {
}
