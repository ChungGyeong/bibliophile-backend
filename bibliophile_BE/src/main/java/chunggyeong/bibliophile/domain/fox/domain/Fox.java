package chunggyeong.bibliophile.domain.fox.domain;

import chunggyeong.bibliophile.domain.fox.exception.NoFeedAvailableException;
import chunggyeong.bibliophile.domain.user.domain.User;
import chunggyeong.bibliophile.global.database.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    private double percent;

    @Enumerated(STRING)
    private FoxType type;

    @Enumerated(STRING)
    private FoxStatus status;

    private LocalDateTime lastFeedDate;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Fox(User user) {
        this.level = 0;
        this.exp = 0;
        this.feedCount = 0;
        this.type = FoxType.BABY;
        this.status = FoxStatus.GOOD;
        this.user = user;
        this.percent = 0.0;
        this.lastFeedDate = LocalDateTime.now().minusDays(1);
    }

    public static Fox createFox(User user) {
        return builder()
                .user(user)
                .build();
    }

    public void updateExpCount() {
        if(this.feedCount<=0){
            throw NoFeedAvailableException.EXCEPTION;
        }
        this.feedCount--;
        this.exp++;
        this.lastFeedDate = LocalDateTime.now();
        this.status = FoxStatus.GOOD;

        if(this.level<5){
            this.percent = exp/3.0*100;
            if(this.exp==3){
                levelUp();
            }
        }
        else if(this.level<=9){
            this.percent = exp/5.0*100;
            if(this.exp==5){
                levelUp();
            }
        }
        else if(this.level>=10) {
            this.percent = exp/10.0*100;
            if(this.exp==10){
                levelUp();
            }
        }
    }

    public void levelUp(){
        this.level++;
        this.exp=0;
        this.percent = 0.0;
        if(this.level==5){
            this.type = FoxType.YOUTH;
        }
        else if(this.level==10){
            this.type = FoxType.ADULT;
        }
    }

    public void updateFoxStatus(FoxStatus status) {
        this.status = status;
    }

    public void updateAddFoxFeedCount(){
        this.feedCount++;
    }

}
