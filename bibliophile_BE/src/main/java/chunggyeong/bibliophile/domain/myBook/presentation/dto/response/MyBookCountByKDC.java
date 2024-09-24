package chunggyeong.bibliophile.domain.myBook.presentation.dto.response;

public record MyBookCountByKDC(
        String kdc,
        int count
) {
}
