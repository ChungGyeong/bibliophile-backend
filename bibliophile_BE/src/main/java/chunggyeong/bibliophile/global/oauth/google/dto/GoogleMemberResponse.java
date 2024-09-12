package chunggyeong.bibliophile.global.oauth.google.dto;

import chunggyeong.bibliophile.domain.oauth.domain.OauthMember;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import static chunggyeong.bibliophile.domain.oauth.domain.OauthServerType.GOOGLE;
import static com.fasterxml.jackson.databind.PropertyNamingStrategies.*;

@JsonNaming(value = SnakeCaseStrategy.class)
public record GoogleMemberResponse(
        String id,
        String email,
        Boolean verifiedEmail,
        String name,
        String givenName,
        String familyName,
        String picture,
        String locale
) {
    public OauthMember toDomain() {
        return OauthMember.builder()
                .name(name)
                .email(email)
                .oauthServerType(GOOGLE)
                .build();
    }
}
