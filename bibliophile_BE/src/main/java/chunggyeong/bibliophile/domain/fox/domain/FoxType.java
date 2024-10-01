package chunggyeong.bibliophile.domain.fox.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FoxType {
    BABY("아기"),
    YOUTH("청소년"),
    ADULT("어른");

    private String value;
}
