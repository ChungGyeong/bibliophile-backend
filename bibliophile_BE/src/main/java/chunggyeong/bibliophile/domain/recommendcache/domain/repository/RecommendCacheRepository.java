package chunggyeong.bibliophile.domain.recommendcache.domain.repository;

import chunggyeong.bibliophile.domain.recommendcache.domain.RecommendCache;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecommendCacheRepository extends JpaRepository<RecommendCache,Long> {
    Optional<RecommendCache> findFirstByTitleNm(String title);
}
