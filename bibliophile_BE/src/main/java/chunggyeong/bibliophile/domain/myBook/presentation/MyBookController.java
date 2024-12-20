package chunggyeong.bibliophile.domain.myBook.presentation;

import chunggyeong.bibliophile.domain.file.presentation.dto.response.UploadFileResponse;
import chunggyeong.bibliophile.domain.myBook.domain.ReadingStatus;
import chunggyeong.bibliophile.domain.myBook.presentation.dto.request.AddMyBookRequest;
import chunggyeong.bibliophile.domain.myBook.presentation.dto.request.UpdateMyBookRequest;
import chunggyeong.bibliophile.domain.myBook.presentation.dto.request.UpdateMyBookStatusRequest;
import chunggyeong.bibliophile.domain.myBook.presentation.dto.response.CheckMyBookResponse;
import chunggyeong.bibliophile.domain.myBook.presentation.dto.response.MyBookCountByKDC;
import chunggyeong.bibliophile.domain.myBook.presentation.dto.response.MyBookResponse;
import chunggyeong.bibliophile.domain.myBook.service.MyBookService;
import chunggyeong.bibliophile.domain.user.domain.User;
import chunggyeong.bibliophile.global.utils.user.UserUtilsImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "나의 책", description = "나의 책 관련 API")
@RestController
@RequestMapping("/api/my-book")
@RequiredArgsConstructor
public class MyBookController {

    private final MyBookService myBookService;
    private final UserUtilsImpl userUtilsImpl;

    @Operation(summary = "나의 책 추가")
    @PostMapping()
    public MyBookResponse addBookmark(@RequestBody @Valid AddMyBookRequest addMyBookRequest) {
        return myBookService.addMyBook(addMyBookRequest);
    }

    @Operation(summary = "나의 책 단건 조회")
    @GetMapping("/{myBookId}")
    public MyBookResponse findMyBookByMyBookId(@PathVariable Long myBookId) {
        return myBookService.findMyBookByMyBookId(myBookId);
    }

    @Operation(summary = "나의 책장에서 보기")
    @GetMapping("/book/{bookId}")
    public MyBookResponse findMyBookByBookId(@PathVariable Long bookId) {
        return myBookService.findMyBookByBookId(bookId);
    }

    @Operation(summary = "나의 책 현재까지 읽은 페이지 변경")
    @PatchMapping("/page/{myBookId}")
    public MyBookResponse updateMyBook(@PathVariable Long myBookId, @RequestBody @Valid UpdateMyBookRequest updateMyBookRequest) {
        return myBookService.updateMyBook(myBookId, updateMyBookRequest);
    }

    @Operation(summary = "나의 책 삭제")
    @DeleteMapping("/{myBookId}")
    public void deleteMyBook(@PathVariable Long myBookId) {
        myBookService.deleteMyBook(myBookId);
    }

    @Operation(summary = "나의 책 상태 변경")
    @PatchMapping("/status/{myBookId}")
    public MyBookResponse updateMyBookStatus(@PathVariable Long myBookId, @RequestBody @Valid UpdateMyBookStatusRequest updateMyBookStatusRequest) {
        return myBookService.updateMyBookStatus(myBookId, updateMyBookStatusRequest);
    }

    @Operation(summary = "나의 책 리스트 조회")
    @GetMapping()
    public List<MyBookResponse> findMyBooksByStatus(@RequestParam("status") ReadingStatus readingStatus) {
        return myBookService.findMyBooksByStatus(readingStatus);
    }

    @Operation(summary = "나의 책 분야별 통계 조회")
    @GetMapping("/statistics")
    public List<MyBookCountByKDC> findMyBooksStatistics() {
        return myBookService.findMyBooksStatistics();
    }

    @Operation(summary = "책 다시 읽기")
    @PatchMapping("/re-read/{myBookId}")
    public MyBookResponse reReadBook(@PathVariable Long myBookId) {
        return myBookService.reReadBook(myBookId);
    }

    @Operation(summary = "해당 책이 나의 책에 있는지 확인")
    @GetMapping("/check/{bookId}")
    public CheckMyBookResponse checkMyBook(@PathVariable Long bookId) {
        return myBookService.checkMyBook(bookId);
    }
}
