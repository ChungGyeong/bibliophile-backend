package chunggyeong.bibliophile.domain.streak.domain.repository;

import chunggyeong.bibliophile.domain.streak.domain.Streak;
import chunggyeong.bibliophile.domain.user.domain.User;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface StreakRepository extends JpaRepository<Streak, Long> {

    boolean existsByUserAndStreakDate(User user, LocalDate streakDate);

    Optional<Streak> findByUserAndStreakDate(User user, LocalDate streakDate);

    @Query("SELECT s FROM Streak s WHERE s.user = :user AND YEAR(s.streakDate) = :year AND MONTH(s.streakDate) = :month ORDER BY s.streakDate ASC")
    List<Streak> findAllByUserAndYearAndMonth(@Param("user") User user, @Param("year") int year, @Param("month") int month);
}
