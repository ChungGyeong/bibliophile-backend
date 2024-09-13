package chunggyeong.bibliophile.domain.oauth.presentation.dto.request;

import jakarta.validation.constraints.NotNull;

public record TokenRefreshRequest(
        @NotNull
        String refreshToken
) {
}
