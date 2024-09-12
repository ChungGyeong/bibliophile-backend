package chunggyeong.bibliophile.domain.interest.service;

import chunggyeong.bibliophile.domain.interest.domain.Classification;
import chunggyeong.bibliophile.domain.user.domain.User;

public interface InterestServiceUtils {

    void addInterest(User user, Classification classification);
}
