package chunggyeong.bibliophile.domain.book.presentation.dto.response;

import java.util.List;

public record TagRecommendResponse(
        Long user_name,
        List<Long> recommendations
) {
}
