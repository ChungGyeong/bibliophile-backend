package chunggyeong.bibliophile.domain.timer.presentation.dto.response;

import chunggyeong.bibliophile.domain.timer.domain.Timer;

import java.time.LocalDateTime;

public record TimerResponse(
        String readingTime,
        LocalDateTime createdDate,
        LocalDateTime lastModifyDate
) {

    public TimerResponse(Timer timer, String readingTime) {
        this(readingTime, timer.getCreatedDate(), timer.getLastModifyDate());
    }
}
