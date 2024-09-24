package chunggyeong.bibliophile.domain.timer.presentation;

import chunggyeong.bibliophile.domain.timer.presentation.dto.request.AddTimerRequest;
import chunggyeong.bibliophile.domain.timer.presentation.dto.response.ReadingStatisticsResponse;
import chunggyeong.bibliophile.domain.timer.presentation.dto.response.TimerResponse;
import chunggyeong.bibliophile.domain.timer.service.TimerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "타이머", description = "타이머 관련 API")
@RestController
@RequestMapping("/api/timers")
@RequiredArgsConstructor
public class TimerController {

    private final TimerService timerService;

    @Operation(summary = "독서 타이머 실행")
    @PostMapping()
    public TimerResponse addTimer(@RequestBody @Valid AddTimerRequest addTimerRequest) {
        return timerService.addTimer(addTimerRequest);
    }

    @Operation(summary = "읽은 시간 통계 조회")
    @GetMapping()
    public List<ReadingStatisticsResponse> addTimer() {
        return timerService.findTotalReadingTime();
    }
}
