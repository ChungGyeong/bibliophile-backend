package chunggyeong.bibliophile.global.oauth.google.dto;

import com.fasterxml.jackson.databind.annotation.JsonNaming;

import static com.fasterxml.jackson.databind.PropertyNamingStrategies.*;

@JsonNaming(value = SnakeCaseStrategy.class)
public record GoogleToken(
        String accessToken,
        int expiresIn,
        String scope,
        String tokenType,
        String idToken
) {
}
