package chunggyeong.bibliophile.domain.timer.domain;

import chunggyeong.bibliophile.domain.myBook.domain.MyBook;
import chunggyeong.bibliophile.global.database.BaseEntity;
import chunggyeong.bibliophile.global.utils.DurationConverter;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Timer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "timer_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "my_book_id")
    private MyBook myBook;

    @Convert(converter = DurationConverter.class)
    private Duration duration;

    @Builder
    public Timer(MyBook myBook, Duration duration) {
        this.myBook = myBook;
        this.duration = duration;
    }

    public static Timer createTimer(MyBook myBook, Duration duration) {
        return builder()
                .myBook(myBook)
                .duration(duration)
                .build();
    }
}
