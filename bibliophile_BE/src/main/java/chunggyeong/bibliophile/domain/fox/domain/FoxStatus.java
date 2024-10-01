package chunggyeong.bibliophile.domain.fox.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FoxStatus {
    GOOD("좋아요"),
    BAD("배고파요");

    private String value;
}
