package chunggyeong.bibliophile.domain.streak.service;

import chunggyeong.bibliophile.domain.streak.domain.Streak;
import chunggyeong.bibliophile.domain.user.domain.User;

import java.util.List;

public interface StreakServiceUtils {

    List<Streak> queryStreakByUserAndYearAndMonth(User user, int year, int month);
}
