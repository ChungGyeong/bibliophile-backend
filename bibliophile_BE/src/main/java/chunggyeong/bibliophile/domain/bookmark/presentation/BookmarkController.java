package chunggyeong.bibliophile.domain.bookmark.presentation;

import chunggyeong.bibliophile.domain.bookmark.presentation.dto.request.AddBookmarkRequest;
import chunggyeong.bibliophile.domain.bookmark.presentation.dto.response.BookmarkResponse;
import chunggyeong.bibliophile.domain.bookmark.service.BookmarkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "즐겨찾기", description = "즐겨찾기 관련 API")
@RestController
@RequestMapping("/api/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @Operation(summary = "즐겨찾기 추가")
    @PostMapping()
    public BookmarkResponse addBookmark(@RequestBody @Valid AddBookmarkRequest addBookmarkRequest) {
        return bookmarkService.addBookmark(addBookmarkRequest);
    }

    @Operation(summary = "즐겨찾기 삭제")
    @DeleteMapping("/{bookId}")
    public void deleteBookmark(@PathVariable Long bookId) {
        bookmarkService.deleteBookmark(bookId);
    }

    @Operation(summary = "나의 즐겨찾기 목록 조회")
    @GetMapping()
    public List<BookmarkResponse> addBookmark() {
        return bookmarkService.findBookmarks();
    }
}
