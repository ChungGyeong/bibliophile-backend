package chunggyeong.bibliophile.domain.oauth.domain.repository;

import chunggyeong.bibliophile.domain.oauth.domain.OauthMember;
import chunggyeong.bibliophile.domain.oauth.domain.OauthServerType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OauthMemberRepository extends JpaRepository<OauthMember, Long> {

    Optional<OauthMember> findByOauthServerTypeAndEmail(OauthServerType oauthServerType, String email);
}
