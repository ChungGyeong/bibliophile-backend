package chunggyeong.bibliophile.domain.bookreport.presentation.dto.response;

import chunggyeong.bibliophile.domain.bookreport.domain.BookReport;

import java.time.LocalDateTime;
import java.util.List;

public record BookReportResponse(
        Long bookReportId,
        String content,
        List<String> bookReportImgUrlList,
        LocalDateTime createdDate,
        LocalDateTime lastModifyDate
) {
    public BookReportResponse(BookReport bookReport, List<String> bookReportImgUrlList) {
        this(bookReport.getId(), bookReport.getContent(), bookReportImgUrlList, bookReport.getCreatedDate(), bookReport.getLastModifyDate());
    }
}
