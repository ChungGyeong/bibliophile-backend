package chunggyeong.bibliophile.domain.streak.domain;

import chunggyeong.bibliophile.domain.user.domain.User;
import chunggyeong.bibliophile.global.database.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Streak extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "streak_id")
    private Long id;

    private LocalDate streakDate;

    private int total;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Streak(User user) {
        this.streakDate = LocalDate.now();
        this.total = 0;
        this.user = user;
    }

    public static Streak createStreak(User user) {
        return builder()
                .user(user)
                .build();
    }

    public void updateIncreaseStreakTotal(int page){
        this.total+=page;
    }
}
