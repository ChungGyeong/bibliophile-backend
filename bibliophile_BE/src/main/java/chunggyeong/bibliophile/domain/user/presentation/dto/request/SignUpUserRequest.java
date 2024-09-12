package chunggyeong.bibliophile.domain.user.presentation.dto.request;

import chunggyeong.bibliophile.domain.interest.domain.Classification;
import chunggyeong.bibliophile.domain.oauth.domain.OauthServerType;
import chunggyeong.bibliophile.domain.user.domain.Gender;

import java.time.LocalDate;
import java.util.List;

public record SignUpUserRequest(
    String nickname,
    String email,
    Gender gender,
    LocalDate birthday,
    List<Classification> classification,
    String profileImage,
    OauthServerType oauthServerType
) {
}
