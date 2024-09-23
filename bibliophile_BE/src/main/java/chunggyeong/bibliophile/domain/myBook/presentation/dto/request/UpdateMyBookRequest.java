package chunggyeong.bibliophile.domain.myBook.presentation.dto.request;

public record UpdateMyBookRequest(
        Long myBookId,
        int page
) {
}
