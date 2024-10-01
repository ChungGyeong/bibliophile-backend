package chunggyeong.bibliophile.domain.streak.presentation.dto;

import chunggyeong.bibliophile.domain.streak.domain.Streak;

import java.time.LocalDate;

public record StreakResponse(
        LocalDate streakDate,
        int totalCount
) {
    public StreakResponse(Streak streak){
        this(streak.getStreakDate(), streak.getTotal());
    }
}
