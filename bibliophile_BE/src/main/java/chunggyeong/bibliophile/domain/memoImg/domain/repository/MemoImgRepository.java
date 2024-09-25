package chunggyeong.bibliophile.domain.memoImg.domain.repository;

import chunggyeong.bibliophile.domain.memo.domain.Memo;
import chunggyeong.bibliophile.domain.memoImg.domain.MemoImg;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoImgRepository extends JpaRepository<MemoImg, Long> {
    boolean existsByMemo(Memo memo);

    void deleteAllByMemo(Memo memo);
}
