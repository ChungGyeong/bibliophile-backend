package chunggyeong.bibliophile.domain.book.presentation;

import chunggyeong.bibliophile.domain.book.presentation.dto.request.ContentRecommendationRequest;
import chunggyeong.bibliophile.domain.book.presentation.dto.request.TagRecommendationRequest;
import chunggyeong.bibliophile.domain.book.presentation.dto.response.BookResponse;
import chunggyeong.bibliophile.domain.book.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "도서", description = "도서 관련 API")
@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @Operation(summary= "연령 및 성별에 따른 인기 도서 추천")
    @GetMapping("/popular")
    public List<BookResponse> recommandBookListByAgeAndGender(@PageableDefault(size = 6, sort = "id") Pageable pageable) {
        return bookService.findPopularBooksByAgeAndGender(pageable).stream().toList();
    }

    @Operation(summary = "제목 기반 책 검색")
    @GetMapping("/search")
    public List<BookResponse> searchBookListByTitle(@RequestParam String title,
                                                    @PageableDefault(size = 6, sort = "id") Pageable pageable) {
        return bookService.findBooksByTitle(title, pageable).stream().toList();
    }

    @Operation(summary = "isbn 기반 책 검색")
    @GetMapping
    public BookResponse searchBookListByIsbn(@RequestParam String isbn) {
        return bookService.findBookByIsbn(isbn);
    }

    @Operation(summary = "Book ID 기반 책 검색")
    @GetMapping("/{bookId}")
    public BookResponse searchBookById(@PathVariable Long bookId) {
        return bookService.findBookByBookId(bookId);
    }

    @Operation(summary = "유저 정보 기반 도서 추천")
    @GetMapping("/recommend/user")
    public List<BookResponse> recommendBooks() {
        return bookService.findRecommendBooksByUserInterest();
    }

    @Operation(summary = "도서 줄거리 기반 도서 추천")
    @PostMapping("/recommend/content")
    public List<BookResponse> recommendBooksRelatedContent(@RequestBody ContentRecommendationRequest contentRecommendationRequest) {
        return bookService.findRecommendBooksRelatedContent(contentRecommendationRequest);
    }

    @Operation(summary = "도서 태그 기반 도서 추천")
    @PostMapping("/recommend/tag")
    public List<BookResponse> recommendBooksRelatedTag(@RequestBody TagRecommendationRequest tagRecommendationRequest) {
        return bookService.findRecommendBooksRelatedTag(tagRecommendationRequest);
    }
}
