package chunggyeong.bibliophile.domain.interest.domain.repository;

import chunggyeong.bibliophile.domain.interest.domain.Classification;
import chunggyeong.bibliophile.domain.interest.domain.Interest;
import chunggyeong.bibliophile.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterestRepository extends JpaRepository<Interest, Long> {

    List<Interest> findAllByUser(User user);

    boolean existsByUserAndClassification(User user, Classification classification);

    Integer countByUser(User user);

    void deleteAllByUser(User user);
}
