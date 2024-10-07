package chunggyeong.bibliophile.domain.myBook.presentation.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record UpdateMyBookRequest(
        @Min(0)
        int page
) {
}
