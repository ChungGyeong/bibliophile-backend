package chunggyeong.bibliophile.domain.memo.presentation.dto.response;

import chunggyeong.bibliophile.domain.memo.domain.Memo;

import java.time.LocalDateTime;
import java.util.List;

public record MemoResponse(
        Long memoId,
        String content,
        int memoPage,
        List<String> memoImgUrlList,
        LocalDateTime createdDate,
        LocalDateTime lastModifyDate
) {
    public MemoResponse(Memo memo, List<String> memoImgUrlList) {
        this(memo.getId(), memo.getContent(), memo.getMemoPage(), memoImgUrlList, memo.getCreatedDate(), memo.getLastModifyDate());
    }
}
