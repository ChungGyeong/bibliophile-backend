package chunggyeong.bibliophile.domain.timer.presentation.dto.response;

public record ReadingStatisticsResponse(
        int month,
        String readingTime
) {
}