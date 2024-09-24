package chunggyeong.bibliophile.domain.timer.domain;

import chunggyeong.bibliophile.domain.myBook.domain.MyBook;
import chunggyeong.bibliophile.global.database.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @Builder
    public Timer(MyBook myBook, LocalDateTime startTime, LocalDateTime endTime) {
        this.myBook = myBook;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static Timer createTimer(MyBook myBook, LocalDateTime startTime, LocalDateTime endTime) {
        return builder()
                .myBook(myBook)
                .startTime(startTime)
                .endTime(endTime)
                .build();
    }
}
