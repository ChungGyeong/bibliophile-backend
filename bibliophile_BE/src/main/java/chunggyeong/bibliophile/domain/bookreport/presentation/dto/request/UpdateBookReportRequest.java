package chunggyeong.bibliophile.domain.bookreport.presentation.dto.request;

import java.util.List;

public record UpdateBookReportRequest(
        String content,
        List<String> bookReportImgUrl
) {
}
