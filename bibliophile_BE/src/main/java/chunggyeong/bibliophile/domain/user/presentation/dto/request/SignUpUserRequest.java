package chunggyeong.bibliophile.domain.user.presentation.dto.request;

import chunggyeong.bibliophile.domain.interest.domain.Classification;
import chunggyeong.bibliophile.domain.oauth.domain.OauthServerType;
import chunggyeong.bibliophile.domain.user.domain.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

public record SignUpUserRequest(
        @NotBlank
        @Size(min = 2, max = 8, message = "닉네임은 2자 이상 8자 이하여야 합니다.")
        String nickname,

        @NotBlank
        String email,

        @NotBlank
        Gender gender,

        @NotBlank
        @PastOrPresent(message = "미래의 날짜는 선택하지 못합니다.")
        LocalDate birthday,

        @NotBlank
        List<Classification> classification,

        @NotBlank
        String profileImage,

        @NotBlank
        OauthServerType oauthServerType
) {
}