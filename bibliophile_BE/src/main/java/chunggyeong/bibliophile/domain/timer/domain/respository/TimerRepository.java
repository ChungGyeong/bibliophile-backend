package chunggyeong.bibliophile.domain.timer.domain.respository;

import chunggyeong.bibliophile.domain.timer.domain.Timer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TimerRepository extends JpaRepository<Timer, Long> {

    @Query("SELECT FUNCTION('MONTH', t.startTime) as month, SUM(FUNCTION('TIMESTAMPDIFF', SECOND, t.startTime, t.endTime)) as totalSeconds " +
            "FROM Timer t WHERE t.myBook.user.id = :userId AND FUNCTION('YEAR', t.startTime) = :year " +
            "GROUP BY FUNCTION('MONTH', t.startTime) " +
            "ORDER BY month")
    List<Object[]> sumMonthlyDurationByUserIdAndYear(@Param("userId") Long userId, @Param("year") int year);
}
