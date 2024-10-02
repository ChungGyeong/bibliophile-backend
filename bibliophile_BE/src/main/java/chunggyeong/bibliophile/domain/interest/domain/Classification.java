package chunggyeong.bibliophile.domain.interest.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Classification {
    ECONOMICS("경제"),
    COMICS("만화"),
    SOCIETY("사회"),
    LOVE("사랑"),
    FICTION("소설"),
    TRAVEL("여행"),
    IT_SCIENCE("IT/과학"),
    ARTS("예술"),
    LANGUAGE("언어"),
    HISTORY("역사");

    private final String value;
}
