package chunggyeong.bibliophile.domain.review.presentation.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateReviewRequest(
        @NotBlank @Size(max = 100, message = "내용은 400자 이하이어야 합니다.")
        String content,
        @NotBlank
        @Min(value = 1, message = "별점은 1 이상이어야 합니다.")
        @Max(value = 5, message = "별점은 5 이하이어야 합니다.")
        int star
) {
}
