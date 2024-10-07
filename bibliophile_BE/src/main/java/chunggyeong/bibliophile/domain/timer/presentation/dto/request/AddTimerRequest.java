package chunggyeong.bibliophile.domain.timer.presentation.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record AddTimerRequest(
        @NotBlank
        Long myBookId,
        @NotBlank @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS")
        LocalDateTime startTime,
        @NotBlank @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS")
        LocalDateTime endTime
) {
}
