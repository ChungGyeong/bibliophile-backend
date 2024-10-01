package chunggyeong.bibliophile.domain.fox.domain;

import chunggyeong.bibliophile.domain.user.domain.User;
import chunggyeong.bibliophile.global.database.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Fox extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "fox_id")
    private Long id;

    private int level;
    private int exp;
    private int feedCount;

    @Enumerated(STRING)
    private FoxType type;

    @Enumerated(STRING)
    private FoxStatus status;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Fox(User user) {
        this.level = 0;
        this.exp = 1;
        this.feedCount = 0;
        this.type = FoxType.BABY;
        this.status = FoxStatus.GOOD;
        this.user = user;
    }

    public static Fox createFox(User user) {
        return builder()
                .user(user)
                .build();
    }

    public void updateFeedCount() {
        this.feedCount++;

        if(this.level<3 && this.feedCount==1){
            levelUp();
        }
        else if(this.level>=3 && this.level<=9 && this.feedCount==3){
            levelUp();
        }
        else if(this.level>=10 && this.feedCount==5){
            levelUp();
        }
    }

    public void levelUp(){
        this.level++;
        this.feedCount=0;
        this.status = FoxStatus.GOOD;
    }

    public void updateFoxStatus(FoxStatus status) {
        this.status = status;
    }

}
