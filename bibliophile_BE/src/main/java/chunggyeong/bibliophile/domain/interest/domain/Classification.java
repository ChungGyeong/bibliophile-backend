package chunggyeong.bibliophile.domain.interest.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Classification {
    GENERAL_WORKS("총류"),
    PHILOSOPHY("철학"),
    RELIGION("종교"),
    SOCIAL_SCIENCES("사회과학"),
    NATURAL_SCIENCES("언어"),
    TECHNOLOGY("기술과학"),
    ARTS("예술"),
    LANGUAGE("언어"),
    LITERATURE("문학"),
    HISTORY("역사");

    private final String value;
}
