package chunggyeong.bibliophile.domain.memo.domain.repository;

import chunggyeong.bibliophile.domain.memo.domain.Memo;
import chunggyeong.bibliophile.domain.myBook.domain.MyBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {

    List<Memo> findAllByMyBook(MyBook myBook);
}
