package chunggyeong.bibliophile.domain.book.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ContentRecommendationRequest(
        @NotBlank
        String title,
        @NotBlank
        int requestNumber
) {
}
