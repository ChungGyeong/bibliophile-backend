package chunggyeong.bibliophile.domain.user.presentation.dto.response;

import chunggyeong.bibliophile.domain.interest.domain.Classification;
import chunggyeong.bibliophile.domain.oauth.domain.OauthServerType;
import chunggyeong.bibliophile.domain.user.domain.Gender;
import chunggyeong.bibliophile.domain.user.domain.User;

import java.time.LocalDate;
import java.util.List;

public record UserProfileResponse(
    Long userId,
    String email,
    String nickname,
    Gender gender,
    LocalDate birthday,
    List<Classification> classification,
    String profileImage,
    OauthServerType oauthServerType
) {
    public UserProfileResponse(User user, List<Classification> classification) {
        this(user.getId(), user.getEmail(), user.getNickname(), user.getGender(), user.getBirthday(),
                classification, user.getProfileImageUrl(), user.getOauthServerType());
    }
}
