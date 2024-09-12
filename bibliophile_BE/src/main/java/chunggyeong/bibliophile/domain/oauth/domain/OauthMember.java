package chunggyeong.bibliophile.domain.oauth.domain;

import chunggyeong.bibliophile.domain.oauth.domain.vo.OauthMemberInfoVO;
import chunggyeong.bibliophile.global.database.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class OauthMember extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Enumerated(STRING)
    private OauthServerType oauthServerType;

    private String name;
    private String email;

    public OauthMemberInfoVO getOauthMemberInfo() {
        return new OauthMemberInfoVO(
                email,
                name,
                oauthServerType
        );
    }
}
