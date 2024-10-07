package chunggyeong.bibliophile.domain.timer.presentation.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AddTimerRequest(
        @NotNull
        Long myBookId,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS")
        LocalDateTime startTime,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS")
        LocalDateTime endTime
) {
}
