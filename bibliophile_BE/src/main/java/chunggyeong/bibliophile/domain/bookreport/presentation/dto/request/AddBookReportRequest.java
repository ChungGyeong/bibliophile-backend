package chunggyeong.bibliophile.domain.bookreport.presentation.dto.request;

import java.util.List;

public record AddBookReportRequest(
        Long myBookId,
        String content,
        List<String> ImgUrl
){
}
