package chunggyeong.bibliophile.domain.recommendcache.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class RecommendCache {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "recommend_cache_id")
    private Long id;

    @Column(name = "title_nm")
    private String titleNm;

    @Column(columnDefinition = "TEXT")
    private String recommendations;

}
