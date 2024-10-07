package chunggyeong.bibliophile.domain.bookreport.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record AddBookReportRequest(
        @NotNull
        Long myBookId,
        @NotBlank @Size(max = 400, message = "내용은 400자 이하이어야 합니다.")
        String content,
        @NotBlank
        List<String> ImgUrl
){
}
