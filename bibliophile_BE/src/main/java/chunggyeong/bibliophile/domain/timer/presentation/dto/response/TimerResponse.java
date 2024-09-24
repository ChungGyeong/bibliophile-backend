package chunggyeong.bibliophile.domain.timer.presentation.dto.response;

import chunggyeong.bibliophile.domain.timer.domain.Timer;

import java.time.Duration;
import java.time.LocalDateTime;

public record TimerResponse(
        LocalDateTime startTime,
        LocalDateTime endTime,
        String readingTime,
        LocalDateTime createdDate,
        LocalDateTime lastModifyDate
) {

    public TimerResponse(Timer timer, String readingTime) {
        this(timer.getStartTime(), timer.getEndTime(), readingTime, timer.getCreatedDate(), timer.getLastModifyDate());
    }
}
