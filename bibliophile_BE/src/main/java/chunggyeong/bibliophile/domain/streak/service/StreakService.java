package chunggyeong.bibliophile.domain.streak.service;

import chunggyeong.bibliophile.domain.streak.domain.Streak;
import chunggyeong.bibliophile.domain.streak.domain.repository.StreakRepository;
import chunggyeong.bibliophile.domain.streak.exception.BadPageException;
import chunggyeong.bibliophile.domain.streak.presentation.dto.StreakResponse;
import chunggyeong.bibliophile.domain.user.domain.User;
import chunggyeong.bibliophile.global.utils.user.UserUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StreakService implements StreakServiceUtils {

    private final StreakRepository streakRepository;
    private final UserUtils userUtils;

    @Transactional
    public StreakResponse addStreak(int page){
        User user = userUtils.getUserFromSecurityContext();
        Optional<Streak> optionalStreak = streakRepository.findByUserAndStreakDate(user, LocalDate.now());
        if(page<0) {
            throw BadPageException.EXCEPTION;
        }
        if(optionalStreak.isPresent()){
            Streak existStreak = optionalStreak.get();
            existStreak.updateIncreaseStreakTotal(page);
            return new StreakResponse(existStreak);
        };
        Streak streak = Streak.createStreak(user);
        streakRepository.save(streak);
        return new StreakResponse(streak);
    }

    public List<StreakResponse> findAllStreaksByUserAndYearAndMonth(int year, int month){
        User user = userUtils.getUserFromSecurityContext();
        List<Streak> streaks = queryStreakByUserAndYearAndMonth(user,year,month);
        return streaks.stream().map(StreakResponse::new).toList();
    }

    @Override
    public List<Streak> queryStreakByUserAndYearAndMonth(User user, int year, int month) {
        return streakRepository.findAllByUserAndYearAndMonth(user,year,month);
    }
}
