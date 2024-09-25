package chunggyeong.bibliophile.domain.memoImg.domain;

import chunggyeong.bibliophile.domain.memo.domain.Memo;
import chunggyeong.bibliophile.domain.myBook.domain.MyBook;
import chunggyeong.bibliophile.global.database.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class MemoImg extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "memo_img_id")
    private Long id;

    String imgUrl;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "memo_id")
    private Memo memo;

    @Builder
    public MemoImg(Memo memo, String imgUrl) {
        this.memo = memo;
        this.imgUrl = imgUrl;
    }

    public static MemoImg craeteMemoImg(Memo memo, String imgUrl) {
        return builder()
                .memo(memo)
                .imgUrl(imgUrl)
                .build();
    }
}
