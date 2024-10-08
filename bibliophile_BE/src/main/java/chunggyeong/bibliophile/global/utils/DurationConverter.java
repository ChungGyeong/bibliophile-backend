package chunggyeong.bibliophile.global.utils;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.Duration;

@Converter(autoApply = true)
public class DurationConverter implements AttributeConverter<Duration, Long> {

    @Override
    public Long convertToDatabaseColumn(Duration duration) {
        return duration == null ? null : duration.getSeconds(); // 초 단위로 저장
    }

    @Override
    public Duration convertToEntityAttribute(Long seconds) {
        return seconds == null ? null : Duration.ofSeconds(seconds); // 초 단위로 변환
    }
}
