package chunggyeong.bibliophile.global.oauth.naver.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import static com.fasterxml.jackson.databind.PropertyNamingStrategies.*;

@JsonNaming(SnakeCaseStrategy.class)
public record NaverToken(
        String accessToken,
        String refreshToken,
        String tokenType,
        Integer expiresIn,
        String error,
        String errorDescription

) {
}
