package chunggyeong.bibliophile.domain.user.domain.repository;

import chunggyeong.bibliophile.domain.oauth.domain.OauthServerType;
import chunggyeong.bibliophile.domain.user.domain.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByOauthServerTypeAndEmail(OauthServerType oauthServerType, String email);

    boolean existsByNickname(String nickname);

    boolean existsByEmailAndOauthServerType(String email, OauthServerType oauthServerType);

}
