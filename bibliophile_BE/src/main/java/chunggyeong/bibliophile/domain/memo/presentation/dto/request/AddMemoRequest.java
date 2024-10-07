package chunggyeong.bibliophile.domain.memo.presentation.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public record AddMemoRequest(
        @NotBlank
        Long myBookId,
        @NotBlank @Size(max = 400, message = "내용은 400자 이하이어야 합니다.")
        String content,
        @NotBlank @Min(0)
        int memoPage,
        @NotBlank
        List<String> memoImgUrl
) {
}
