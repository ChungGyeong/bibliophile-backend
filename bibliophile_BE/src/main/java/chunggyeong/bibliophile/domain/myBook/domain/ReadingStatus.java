package chunggyeong.bibliophile.domain.myBook.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReadingStatus {

    UNREAD("읽지 않은 책"),
    READING("읽는 중인 책"),
    READ("완독한 책");

    private String value;
}
