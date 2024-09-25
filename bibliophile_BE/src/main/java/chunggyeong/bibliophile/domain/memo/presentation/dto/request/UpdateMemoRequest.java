package chunggyeong.bibliophile.domain.memo.presentation.dto.request;

import java.util.List;

public record UpdateMemoRequest(
        int memoPage,
        String content,
        List<String> memoImgUrl
) {
}
