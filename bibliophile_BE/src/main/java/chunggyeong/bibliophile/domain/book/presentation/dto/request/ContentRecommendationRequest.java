package chunggyeong.bibliophile.domain.book.presentation.dto.request;

public record ContentRecommendationRequest(
        String title,
        int requestNumber
) {
}
