package chunggyeong.bibliophile.domain.bookreport.presentation;

import chunggyeong.bibliophile.domain.bookreport.presentation.dto.request.AddBookReportRequest;
import chunggyeong.bibliophile.domain.bookreport.presentation.dto.request.UpdateBookReportRequest;
import chunggyeong.bibliophile.domain.bookreport.presentation.dto.response.BookReportResponse;
import chunggyeong.bibliophile.domain.bookreport.service.BookReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "독후감", description = "독후감 관련 API")
@RestController
@RequestMapping("/api/book-reports")
@RequiredArgsConstructor
public class BookReportController {

    private final BookReportService bookReportService;

    @Operation(summary = "독후감 추가")
    @PostMapping
    public BookReportResponse addBookReport(@RequestBody @Valid AddBookReportRequest addBookReportRequest){
        return bookReportService.addBookReport(addBookReportRequest);
    }

    @Operation(summary = "독후감 단건 조회")
    @GetMapping("/{bookReportId}")
    public BookReportResponse findBookReportByBookReportId(@PathVariable Long bookReportId) {
        return bookReportService.findBookReportByBookId(bookReportId);
    }

    @Operation(summary = "독후감 수정")
    @PatchMapping("/{bookReportId}")
    public BookReportResponse updateBookReport(@PathVariable Long bookReportId, @RequestBody @Valid UpdateBookReportRequest updateBookReportRequest) {
        return bookReportService.updateBookReport(bookReportId, updateBookReportRequest);
    }

    @Operation(summary = "독후감 삭제")
    @DeleteMapping("/{bookReportId}")
    public void deleteBookReport(@PathVariable Long bookReportId) {
        bookReportService.deleteBookReport(bookReportId);
    }
}
