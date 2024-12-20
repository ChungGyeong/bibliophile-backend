package chunggyeong.bibliophile.domain.user.presentation.dto.request;

import chunggyeong.bibliophile.domain.interest.domain.Classification;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public record UpdateUserRequest(
        @NotBlank
        @Size(min = 2, max = 8, message = "닉네임은 2자 이상 8자 이하여야 합니다.")
        String nickname,

        List<Classification> classification,

        @NotBlank
        String profileImage
) {
}
