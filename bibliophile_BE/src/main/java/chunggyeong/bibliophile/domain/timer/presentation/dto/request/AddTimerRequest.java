package chunggyeong.bibliophile.domain.timer.presentation.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.Duration;

public record AddTimerRequest(
        @NotNull
        Long myBookId,
        @NotNull
        String duration
) {
        public Duration getDurationAsDuration() {
                return Duration.parse(duration);
        }
}
