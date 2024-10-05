package chunggyeong.bibliophile.domain.book.presentation.dto.response;

import java.util.List;

public record RecommendResponse(
        Long user_id,
        List<String> recommended_books
) {
}
