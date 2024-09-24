package chunggyeong.bibliophile.domain.timer.service;

import chunggyeong.bibliophile.domain.myBook.domain.MyBook;
import chunggyeong.bibliophile.domain.myBook.service.MyBookServiceUtils;
import chunggyeong.bibliophile.domain.timer.domain.Timer;
import chunggyeong.bibliophile.domain.timer.domain.respository.TimerRepository;
import chunggyeong.bibliophile.domain.timer.presentation.dto.request.AddTimerRequest;
import chunggyeong.bibliophile.domain.timer.presentation.dto.response.ReadingStatisticsResponse;
import chunggyeong.bibliophile.domain.timer.presentation.dto.response.TimerResponse;
import chunggyeong.bibliophile.domain.user.domain.User;
import chunggyeong.bibliophile.global.utils.user.UserUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TimerService {

    private final TimerRepository timerRepository;
    private final MyBookServiceUtils myBookServiceUtils;
    private final UserUtils userUtils;

    // 독서 타이머 실행
    @Transactional
    public TimerResponse addTimer(AddTimerRequest addTimerRequest) {
        MyBook myBook = myBookServiceUtils.queryMyBook(addTimerRequest.myBookId());
        User user = userUtils.getUserFromSecurityContext();

        myBookServiceUtils.validUserIsHost(myBook, user);

        Timer timer = Timer.createTimer(myBook, addTimerRequest.startTime(), addTimerRequest.endTime());

        timerRepository.save(timer);

        String readingTime = formatDuration(Duration.between(timer.getStartTime(), timer.getEndTime()));

        return new TimerResponse(timer, readingTime);
    }

    // 읽은 시간 통계 조회
    public List<ReadingStatisticsResponse> findTotalReadingTime() {
        User user = userUtils.getUserFromSecurityContext();
        int currentYear = Year.now().getValue();

        List<Object[]> monthlyStats = timerRepository.sumMonthlyDurationByUserIdAndYear(user.getId(), currentYear);

        Map<Integer, Long> statsMap = monthlyStats.stream()
                .collect(Collectors.toMap(
                        stat -> ((Number) stat[0]).intValue(),
                        stat -> ((Number) stat[1]).longValue()
                ));

        List<ReadingStatisticsResponse> responses = new ArrayList<>();
        for (int month = 1; month <= 12; month++) {
            long totalSeconds = statsMap.getOrDefault(month, 0L);
            Duration duration = Duration.ofSeconds(totalSeconds);
            String formattedDuration = formatDuration(duration);
            responses.add(new ReadingStatisticsResponse(month, formattedDuration));
        }

        return responses;
    }

    private String formatDuration(Duration duration) {
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();
        return String.format("%d:%d", hours, minutes);
    }
}
