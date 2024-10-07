package chunggyeong.bibliophile.domain.book.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record TagRecommendationRequest(
        @NotBlank
        List<String> tags
) {
}
