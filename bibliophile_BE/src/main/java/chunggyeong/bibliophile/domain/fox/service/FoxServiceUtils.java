package chunggyeong.bibliophile.domain.fox.service;

import chunggyeong.bibliophile.domain.fox.domain.Fox;
import chunggyeong.bibliophile.domain.user.domain.User;

public interface FoxServiceUtils {

    Fox queryFoxByUser(User user);
}
