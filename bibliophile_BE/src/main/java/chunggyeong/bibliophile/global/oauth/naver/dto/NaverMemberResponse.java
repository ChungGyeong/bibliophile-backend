package chunggyeong.bibliophile.global.oauth.naver.dto;

import chunggyeong.bibliophile.domain.oauth.domain.OauthMember;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import static chunggyeong.bibliophile.domain.oauth.domain.OauthServerType.NAVER;
import static com.fasterxml.jackson.databind.PropertyNamingStrategies.*;

@JsonNaming(SnakeCaseStrategy.class)
public record NaverMemberResponse (
        String resultcode,
        String message,
        Response response
){
    public OauthMember toDomain() {
        return OauthMember.builder()
                .name(response.name)
                .email(response.email)
                .oauthServerType(NAVER)
                .build();


    }
    @JsonNaming(SnakeCaseStrategy.class)
    public record Response(
            String id,
            String nickname,
            String name,
            String email,
            String gender,
            String age,
            String birthday,
            String profileImage,
            String birthyear,
            String mobile


    ){

    }
}
