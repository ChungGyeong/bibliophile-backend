package chunggyeong.bibliophile.domain.review.presentation;

import chunggyeong.bibliophile.domain.myBook.domain.ReadingStatus;
import chunggyeong.bibliophile.domain.myBook.presentation.dto.request.AddMyBookRequest;
import chunggyeong.bibliophile.domain.myBook.presentation.dto.response.MyBookResponse;
import chunggyeong.bibliophile.domain.review.presentation.dto.request.AddReviewRequest;
import chunggyeong.bibliophile.domain.review.presentation.dto.request.UpdateReviewRequest;
import chunggyeong.bibliophile.domain.review.presentation.dto.response.ReviewResponse;
import chunggyeong.bibliophile.domain.review.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "리뷰", description = "리뷰 관련 API")
@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "리뷰 작성")
    @PostMapping()
    public ReviewResponse addBookmark(@RequestBody @Valid AddReviewRequest addReviewRequest) {
        return reviewService.addReview(addReviewRequest);
    }

    @Operation(summary = "리뷰 단건 보기")
    @GetMapping("/{reviewId}")
    public ReviewResponse fndReviewByReviewId(@PathVariable Long reviewId) {
        return reviewService.fndReviewByReviewId(reviewId);
    }

    @Operation(summary = "리뷰 단건 보기")
    @GetMapping("/mine/{myBookId}")
    public ReviewResponse findReviewsByUser(@PathVariable Long myBookId) {
        return reviewService.findReviewsByUser(myBookId);
    }

    @Operation(summary = "리뷰 수정")
    @PatchMapping("/{reviewId}")
    public ReviewResponse updateReview(@PathVariable Long reviewId, @RequestBody @Valid UpdateReviewRequest updateReviewRequest) {
        return reviewService.updateReview(reviewId, updateReviewRequest);
    }

    @Operation(summary = "리뷰 삭제")
    @DeleteMapping("/{reviewId}")
    public void deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
    }

    @Operation(summary = "해당하는 책 리뷰 리스트 보기")
    @GetMapping()
    public List<ReviewResponse> findReviewsByBookId(@RequestParam("book") Long bookId) {
        return reviewService.findReviewsByBookId(bookId);
    }
}
