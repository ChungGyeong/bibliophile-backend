package chunggyeong.bibliophile.domain.user.domain;

import chunggyeong.bibliophile.domain.bookmark.domain.Bookmark;
import chunggyeong.bibliophile.domain.fox.domain.Fox;
import chunggyeong.bibliophile.domain.interest.domain.Interest;
import chunggyeong.bibliophile.domain.myBook.domain.MyBook;
import chunggyeong.bibliophile.domain.oauth.domain.OauthServerType;
import chunggyeong.bibliophile.domain.review.domain.Review;
import chunggyeong.bibliophile.domain.streak.domain.Streak;
import chunggyeong.bibliophile.global.database.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String nickname;
    private String email;
    private String profileImageUrl;
    private LocalDate birthday;
    private String wordCloudImgUrl;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private OauthServerType oauthServerType;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Interest> interestList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Bookmark> bookmarkList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<MyBook> myBookList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Streak> streakList = new ArrayList<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Fox fox;

    @Builder
    public User(String nickname, String email, String profileImageUrl, LocalDate birthday, String wordCloudImgUrl, Gender gender, OauthServerType oauthServerType) {
        this.nickname = nickname;
        this.email = email;
        this.profileImageUrl = profileImageUrl;
        this.wordCloudImgUrl = wordCloudImgUrl;
        this.birthday = birthday;
        this.gender = gender;
        this.oauthServerType = oauthServerType;
    }

    public static User createUser(String nickname, String email, String profileImageUrl, LocalDate birthday, Gender gender, OauthServerType oauthServerType) {
        return builder()
                .nickname(nickname)
                .email(email)
                .profileImageUrl(profileImageUrl)
                .wordCloudImgUrl(null)
                .birthday(birthday)
                .gender(gender)
                .oauthServerType(oauthServerType)
                .build();
    }

    public void updateUser(String nickname, String profileImageUrl) {
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
    }

    public void updateWordCloudImgUrl(String wordCloudImgUrl) {
        this.wordCloudImgUrl = wordCloudImgUrl;
    }
}
