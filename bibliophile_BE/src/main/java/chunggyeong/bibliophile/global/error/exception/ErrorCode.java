package chunggyeong.bibliophile.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /* 400 BAD_REQUEST : 잘못된 요청 */
    FILE_SIZE(400, "업로드 된 파일 사이즈가 초과되었습니다."),
    BAD_FILE_EXTENSION(400, "유효한 확장명의 파일이 아닙니다."),
    FILE_UPLOAD_FAIL(400, "파일 업로드에 실패하였습니다."),
    PAGE_LIMIT_EXCEEDED(400, "해당 책의 페이지를 초과하였습니다."),
    MISSING_PARAMS(400,"필수 파라미터가 누락되었습니다."),
    BAD_REQUEST(400,"잘못된 입력입니다."),
    NO_FEED_AVAILABLE(400,"여우에게 줄 수 있는 먹이가 없습니다."),
    BAD_PAGE(400,"이전에 입력한 페이지 보다 적은 페이지를 입력할 수 없습니다."),
    NO_TAG_PROVIDED(400,"입력된 태그가 없습니다."),

    /* 401 UNAUTHORIZED : 인증되지 않은 사용자 */
    EXPIRED_TOKEN(401, "토큰이 만료되었습니다."),
    INVALID_TOKEN(401, "토큰이 유효하지 않습니다."),
    INVALID_REFRESH_TOKEN(401, "리프레시 토큰이 유효하지 않습니다"),
    INVALID_ACCESS_TOKEN(401, "Access 토큰이 유효하지 않습니다"),
    INVALID_URL(401, "URL 주소가 유요하지 않습니다."),

    /* 403 */
    REGISTER_EXPIRED_TOKEN(403,"만료된 리프레쉬 토큰입니다."),
    USER_INFO_NOT_FOUND(404, "회원가입을 진행해 주세요."),
    USER_NOT_STORYBOOK_HOST(403, "해당하는 즐겨찾기의 소유자가 아닙니다."),
    USER_NOT_MY_BOOK_HOST(403, "해당하는 나의 책의 소유자가 아닙니다."),
    USER_NOT_REVIEW_HOST(403, "해당하는 리뷰의 소유자가 아닙니다."),

    /* 404 NOT_FOUND : Resource를 찾을 수 없음 */
    USER_NOT_FOUND(404, "해당하는 정보의 사용자를 찾을 수 없습니다."),
    NO_ERROR_TYPE(404, "오류 발생"),
    OAUTH_MEMBER_NOT_FOUND(404, "해당하는 로그인 정보를 찾을 수 없습니다."),
    FILE_EMPTY(404, "업로드 된 파일을 찾을 수 없습니다."),
    BOOK_NOT_FOUND(404, "해당하는 책 정보를 찾을 수 없습니다."),
    BOOKMARK_NOT_FOUND(404, "해당하는 즐겨찾기 정보를 찾을 수 없습니다."),
    MY_BOOK_NOT_FOUND(404, "해당하는 나의 책 정보를 찾을 수 없습니다."),
    REVIEW_NOT_FOUND(404, "해당하는 리뷰 정보를 찾을 수 없습니다."),
    BOOK_REPORT_NOT_FOUND(404,"해당하는 독후감 정보를 찾을 수 없습니다."),
    MEMO_NOT_FOUND(404, "해당하는 메모 정보를 찾을 수 없습니다."),
    FOX_NOT_FOUND(404,"내 계정에 속해있는 여우를 찾을 수 없습니다."),
    NO_EXIST_INTEREST(404,"내가 현재 등록해둔 관심사가 없어 추천책을 가져올 수 없습니다."),

    /* 409 중복된 리소스 */
    USER_DUPLICATION(409, "이미 가입된 사용자입니다."),
    NICKNAME_DUPLICATION(409, "이미 사용중인 닉네임입니다."),
    INTEREST_DUPLICATION(409, "이미 등록한 관심사입니다."),
    BOOKMARK_DUPLICATION(409, "이미 등록한 즐겨찾기입니다."),
    BOOKREVIEW_DUPLICATION(409,"이미 등록한 리뷰입니다."),
    REVIEW_DUPLICATION(409, "해당 책에 이미 리뷰를 작성했습니다."),
    BOOK_REPORT_EXIST(409,"이미 등록된 독후감이 존재합니다."),
    MY_BOOK_DUPLICATION(409, "해당 책을 이미 읽고 있습니다."),
    ALREADY_HAS_FOX(409,"이미 생성된 내 여우가 존재합니다."),

    /* 429 요청 횟수 초과 */
    MAX_INTEREST_LIMIT_EXCEEDED(429, "등록 가능한 관심사의 수를 초과했습니다."),

    /* 500 SERVER_ERROR */
    INTERNAL_SERVER_ERROR(500,"서버 에러"),
    IMAGE_PROCESSING(500, "이미지 처리 중 오류가 발생했습니다.");

    private int status;
    private String reason;
}
