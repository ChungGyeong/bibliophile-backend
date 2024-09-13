package chunggyeong.bibliophile.domain.interest.service;

import chunggyeong.bibliophile.domain.interest.domain.Classification;
import chunggyeong.bibliophile.domain.user.domain.User;

import java.util.List;

public interface InterestServiceUtils {

    void addInterest(User user, Classification classification);

    List<Classification> findInterestsByUser(User user);

    void deleteAllByUser(User user);
}
