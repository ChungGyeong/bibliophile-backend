package chunggyeong.bibliophile.domain.review.service;

import chunggyeong.bibliophile.domain.book.domain.Book;
import chunggyeong.bibliophile.domain.book.service.BookServiceUtils;
import chunggyeong.bibliophile.domain.fox.domain.Fox;
import chunggyeong.bibliophile.domain.fox.service.FoxServiceUtils;
import chunggyeong.bibliophile.domain.myBook.domain.MyBook;
import chunggyeong.bibliophile.domain.myBook.service.MyBookServiceUtils;
import chunggyeong.bibliophile.domain.review.domain.Review;
import chunggyeong.bibliophile.domain.review.domain.repository.ReviewRepository;
import chunggyeong.bibliophile.domain.review.exception.DuplicateReviewException;
import chunggyeong.bibliophile.domain.review.exception.ReviewNotFoundException;
import chunggyeong.bibliophile.domain.review.presentation.dto.request.AddReviewRequest;
import chunggyeong.bibliophile.domain.review.presentation.dto.request.UpdateReviewRequest;
import chunggyeong.bibliophile.domain.review.presentation.dto.response.ReviewResponse;
import chunggyeong.bibliophile.domain.user.domain.User;
import chunggyeong.bibliophile.global.utils.user.UserUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService implements ReviewServiceUtils{

    private final ReviewRepository reviewRepository;
    private final BookServiceUtils bookServiceUtils;
    private final MyBookServiceUtils myBookServiceUtils;
    private final UserUtils userUtils;
    private final FoxServiceUtils foxServiceUtils;

    // 리뷰 작성
    @Transactional
    public ReviewResponse addReview(AddReviewRequest addReviewRequest) {
        User user = userUtils.getUserFromSecurityContext();
        Book book = bookServiceUtils.queryBook(addReviewRequest.bookId());

        if (reviewRepository.existsByUserAndBook(user, book)) {
            throw DuplicateReviewException.EXCEPTION;
        }

        Review review = Review.createReview(user, book, addReviewRequest.content(), addReviewRequest.star());

        Fox fox = foxServiceUtils.queryFoxByUser(user);
        fox.updateAddFoxFeedCount();

        reviewRepository.save(review);

        return new ReviewResponse(review, user.getNickname(), true);
    }

    // 리뷰 단건 보기
    public ReviewResponse fndReviewByReviewId(Long reviewId) {
        Review review = queryReview(reviewId);
        User user = userUtils.getUserFromSecurityContext();

        boolean isHost = Objects.equals(review.getUser().getId(), user.getId());

        return new ReviewResponse(review, user.getNickname(), isHost);
    }

    // 해당 책 내가 작성한 리뷰 보기
    public ReviewResponse findReviewsByUser(Long myBookId) {
        User user = userUtils.getUserFromSecurityContext();
        MyBook myBook = myBookServiceUtils.queryMyBook(myBookId);
        Book book = bookServiceUtils.queryBook(myBook.getBook().getId());
        Review review = reviewRepository.findByUserAndBook(user, book).orElse(null);
        if(review == null) { return null; }

        return new ReviewResponse(review, user.getNickname(), true);
    }

    // 리뷰 수정
    @Transactional
    public ReviewResponse updateReview(Long reviewId, UpdateReviewRequest updateReviewRequest) {
        Review review = queryReview(reviewId);
        User user = userUtils.getUserFromSecurityContext();

        review.validUserIsHost(user);

        review.updateReview(updateReviewRequest.content(), updateReviewRequest.star());

        return new ReviewResponse(review, user.getNickname(), true);
    }

    // 리뷰 삭제
    @Transactional
    public void deleteReview(Long reviewId) {
        Review review = queryReview(reviewId);
        User user = userUtils.getUserFromSecurityContext();

        review.validUserIsHost(user);

        reviewRepository.delete(review);
    }

    // 해당하는 책 리뷰 리스트 보기
    public List<ReviewResponse> findReviewsByBookId(Long bookId) {
        Book book = bookServiceUtils.queryBook(bookId);
        List<Review> reviewList = reviewRepository.findAllByBook(book);
        User user = userUtils.getUserFromSecurityContext();

        return reviewList.stream()
                .map(review -> {
                    boolean isHost = Objects.equals(review.getUser().getId(), user.getId());

                    return new ReviewResponse(review, review.getUser().getNickname(), isHost);
                })
                .collect(Collectors.toList());
    }

    @Override
    public Review queryReview(Long reviewId) {
        return reviewRepository.findById(reviewId).orElseThrow(() -> ReviewNotFoundException.EXCEPTION);
    }
}
