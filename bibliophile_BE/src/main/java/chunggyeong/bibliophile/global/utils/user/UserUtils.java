package chunggyeong.bibliophile.global.utils.user;

import chunggyeong.bibliophile.domain.user.domain.User;

public interface UserUtils {
    User getUserById(Long id);

    User getUserFromSecurityContext();
}
