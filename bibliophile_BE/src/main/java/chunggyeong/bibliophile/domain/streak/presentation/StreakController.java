package chunggyeong.bibliophile.domain.streak.presentation;

import chunggyeong.bibliophile.domain.streak.presentation.dto.StreakResponse;
import chunggyeong.bibliophile.domain.streak.service.StreakService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "스트릭", description = "스트릭 관련 API")
@RestController
@RequestMapping("/api/streaks")
@RequiredArgsConstructor
public class StreakController {

    private final StreakService streakService;

    @Operation(summary = "년/월별 스트릭 조회")
    @GetMapping
    public List<StreakResponse> fndReviewByReviewId(@RequestParam("year") int year,
                                                    @RequestParam("month") int month) {
        return streakService.findAllStreaksByUserAndYearAndMonth(year,month);
    }
}
