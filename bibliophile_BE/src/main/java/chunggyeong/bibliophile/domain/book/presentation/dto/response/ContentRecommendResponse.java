package chunggyeong.bibliophile.domain.book.presentation.dto.response;

import java.util.List;

public record ContentRecommendResponse(
        Long user_id,
        List<String> recommendations
) {
}
