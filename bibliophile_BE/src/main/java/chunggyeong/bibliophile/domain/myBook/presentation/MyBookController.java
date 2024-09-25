package chunggyeong.bibliophile.domain.myBook.presentation;

import chunggyeong.bibliophile.domain.myBook.domain.ReadingStatus;
import chunggyeong.bibliophile.domain.myBook.presentation.dto.request.AddMyBookRequest;
import chunggyeong.bibliophile.domain.myBook.presentation.dto.request.UpdateMyBookRequest;
import chunggyeong.bibliophile.domain.myBook.presentation.dto.request.UpdateMyBookStatusRequest;
import chunggyeong.bibliophile.domain.myBook.presentation.dto.response.MyBookCountByKDC;
import chunggyeong.bibliophile.domain.myBook.presentation.dto.response.MyBookResponse;
import chunggyeong.bibliophile.domain.myBook.service.MyBookService;
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

    @Operation(summary = "나의 책 단건 조회")
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

    @Operation(summary = "나의 책 리스트 조회")
    @GetMapping("/statistics")
    public List<MyBookCountByKDC> findMyBooksStatistics() {
        return myBookService.findMyBooksStatistics();
    }
}
