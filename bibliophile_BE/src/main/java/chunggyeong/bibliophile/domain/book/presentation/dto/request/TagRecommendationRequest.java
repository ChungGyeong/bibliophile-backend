package chunggyeong.bibliophile.domain.book.presentation.dto.request;

import java.util.List;

public record TagRecommendationRequest(
        int id,
        List<String> tags
) {
}
