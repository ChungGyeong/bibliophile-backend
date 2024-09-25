package chunggyeong.bibliophile.domain.memo.presentation.dto.request;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record AddMemoRequest(
        Long myBookId,
        String content,
        int memoPage,
        List<String> memoImgUrl
) {
}
