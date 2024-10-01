package chunggyeong.bibliophile.domain.fox.presentation.dto.response;

import chunggyeong.bibliophile.domain.fox.domain.Fox;
import chunggyeong.bibliophile.domain.fox.domain.FoxStatus;
import chunggyeong.bibliophile.domain.fox.domain.FoxType;

import java.time.LocalDateTime;

public record FoxResponse (
        Long foxId,
        int level,
        int exp,
        int feedCount,
        FoxType foxType,
        FoxStatus foxStatus,
        LocalDateTime createdDate,
        LocalDateTime lastModifyDate
){
    public FoxResponse(Fox fox){
        this(fox.getId(),fox.getLevel(),fox.getExp(),fox.getFeedCount(),
                fox.getType(),fox.getStatus(),fox.getCreatedDate(), fox.getLastModifyDate());
    }
}
