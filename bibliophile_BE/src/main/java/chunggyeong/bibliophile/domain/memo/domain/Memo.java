package chunggyeong.bibliophile.domain.memo.domain;

import chunggyeong.bibliophile.domain.memoImg.domain.MemoImg;
import chunggyeong.bibliophile.domain.myBook.domain.MyBook;
import chunggyeong.bibliophile.domain.myBook.exception.UserNotMyBookHostException;
import chunggyeong.bibliophile.domain.user.domain.User;
import chunggyeong.bibliophile.global.database.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Memo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "memo_id")
    private Long id;

    private String content;
    private int memoPage;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "my_book_id")
    private MyBook myBook;

    @OneToMany(mappedBy = "memo", cascade = CascadeType.ALL)
    private List<MemoImg> memoImgList = new ArrayList<>();

    @Builder
    public Memo(String content, int memoPage, MyBook myBook) {
        this.content = content;
        this.memoPage = memoPage;
        this.myBook = myBook;
    }

    public static Memo createMemo(String content, int memoPage, MyBook myBook) {
        return builder()
                .content(content)
                .memoPage(memoPage)
                .myBook(myBook)
                .build();
    }

    public void validUserIsHost(User user) {
        if (!this.getMyBook().getUser().equals(user)) {
            throw UserNotMyBookHostException.EXCEPTION;
        }
    }

    public void updateMemo(String content, int memoPage) {
        this.content = content;
        this.memoPage = memoPage;
    }
}
