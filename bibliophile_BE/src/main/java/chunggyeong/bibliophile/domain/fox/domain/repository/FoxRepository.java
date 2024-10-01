package chunggyeong.bibliophile.domain.fox.domain.repository;

import chunggyeong.bibliophile.domain.fox.domain.Fox;
import chunggyeong.bibliophile.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FoxRepository extends JpaRepository<Fox, Long> {

    boolean existsByUser(User user);

    Optional<Fox> findByUser(User user);
}
