package chunggyeong.bibliophile.domain.timer.domain.respository;

import chunggyeong.bibliophile.domain.timer.domain.Timer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TimerRepository extends JpaRepository<Timer, Long> {

    @Query("SELECT FUNCTION('MONTH', t.createdDate) as month, SUM(t.duration) as totalSeconds " +
            "FROM Timer t WHERE t.myBook.user.id = :userId " +
            "GROUP BY FUNCTION('MONTH', t.createdDate) " +
            "ORDER BY month")
    List<Object[]> sumMonthlyDurationByUserId(@Param("userId") Long userId);

}
