package chunggyeong.bibliophile.domain.interest.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Classification {
    SOCIETY("사회"),
    FICTION("소설"),
    SCIENCE("과학"),
    ARTS("예술"),
    LANGUAGE("언어"),
    HISTORY("역사");

    private final String value;
}
