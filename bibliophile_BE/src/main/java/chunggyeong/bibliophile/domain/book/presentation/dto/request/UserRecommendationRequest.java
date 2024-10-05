package chunggyeong.bibliophile.domain.book.presentation.dto.request;

import chunggyeong.bibliophile.domain.user.domain.User;

public record UserRecommendationRequest(
        Long id
)
{
    public UserRecommendationRequest(User user) {
        this(user.getId());
    }
}

