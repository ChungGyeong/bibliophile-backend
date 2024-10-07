package chunggyeong.bibliophile.domain.book.presentation.dto.request;

import chunggyeong.bibliophile.domain.user.domain.User;
import jakarta.validation.constraints.NotNull;

public record UserRecommendationRequest(
        @NotNull
        Long id
)
{
    public UserRecommendationRequest(User user) {
        this(user.getId());
    }
}

