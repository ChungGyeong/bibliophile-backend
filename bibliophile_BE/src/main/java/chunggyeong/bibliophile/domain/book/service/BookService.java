package chunggyeong.bibliophile.domain.book.service;

import chunggyeong.bibliophile.domain.book.domain.Book;
import chunggyeong.bibliophile.domain.book.domain.repository.BookRepository;
import chunggyeong.bibliophile.domain.book.exception.BookNotFoundException;
import chunggyeong.bibliophile.domain.book.presentation.dto.request.ContentRecommendationRequest;
import chunggyeong.bibliophile.domain.book.presentation.dto.request.TagRecommendationRequest;
import chunggyeong.bibliophile.domain.book.presentation.dto.response.BookResponse;
import chunggyeong.bibliophile.domain.book.presentation.dto.response.ContentRecommendResponse;
import chunggyeong.bibliophile.domain.book.presentation.dto.response.RecommendResponse;
import chunggyeong.bibliophile.domain.book.presentation.dto.response.TagRecommendResponse;
import chunggyeong.bibliophile.domain.bookmark.domain.repository.BookmarkRepository;
import chunggyeong.bibliophile.domain.myBook.domain.repository.MyBookRepository;
import chunggyeong.bibliophile.domain.recommendcache.domain.RecommendCache;
import chunggyeong.bibliophile.domain.recommendcache.domain.repository.RecommendCacheRepository;
import chunggyeong.bibliophile.domain.user.domain.Gender;
import chunggyeong.bibliophile.domain.user.domain.User;
import chunggyeong.bibliophile.global.utils.user.UserUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookService implements BookServiceUtils {

    private final BookRepository bookRepository;
    private final MyBookRepository myBookRepository;
    private final UserUtils userUtils;
    private final RestTemplate restTemplate;
    private final BookmarkRepository bookmarkRepository;
    private final RecommendCacheRepository recommendCacheRepository;

    @Override
    public BookResponse findBookByIsbn(String isbn) {
        Book book = bookRepository.findByIsbn(isbn).orElseThrow(() -> BookNotFoundException.EXCEPTION);
        User user = userUtils.getUserFromSecurityContext();
        boolean isBookmarked = existsByUserAndBook(user, book);
        return new BookResponse(book, isBookmarked);
    }

    @Override
    public Page<BookResponse> findBooksByTitle(String title, Pageable pageable) {
        User user = userUtils.getUserFromSecurityContext();
        return bookRepository.searchByTitleUsingFullText(title, pageable)
                .map(myBook -> new BookResponse(myBook,existsByUserAndBook(user,myBook)));
    }

    public BookResponse findBookByBookId(Long bookId) {
        Book book = queryBook(bookId);
        User user = userUtils.getUserFromSecurityContext();
        boolean isBookmarked = existsByUserAndBook(user, book);
        return new BookResponse(book, isBookmarked);
    }

    @Override
    public Book queryBook(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> BookNotFoundException.EXCEPTION);
    }

    @Override
    public Page<BookResponse> findPopularBooksByAgeAndGender(String gender, int ageGroup, Pageable pageable){
        ageGroup = ageGroup/10;
        User user = userUtils.getUserFromSecurityContext();
        return myBookRepository.findAllByAgeAndGenderOrderByMyBookCountDesc(ageGroup, Gender.valueOf(gender), pageable)
                .map(myBook -> new BookResponse(myBook.getBook(),existsByUserAndBook(user,myBook.getBook())));
    }

    public List<BookResponse> findRecommendBooksByUserInterest() {
        User user = userUtils.getUserFromSecurityContext();
        String url = "https://j11b204.p.ssafy.io/recommend/collaborative?id="+user.getId();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> requestBody = new HashMap<>();
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<RecommendResponse> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                RecommendResponse.class
        );

        List<BookResponse> books = new ArrayList<>();
        if (response.getBody() != null) {
            RecommendResponse recommendResponse = response.getBody();

            log.debug("[Spring] user : {}",user);
            List<String> recommendedBooks = recommendResponse.recommended_books();
            if (recommendedBooks != null && !recommendedBooks.isEmpty()) {
                for (String book : recommendedBooks) {
                    Optional<Book> bookOptional = bookRepository.findById(Long.parseLong(book));
                    if(bookOptional.isPresent()){
                        boolean isBookmarked = existsByUserAndBook(user, bookOptional.get());
                        books.add(new BookResponse(bookOptional.get(), isBookmarked));
                    }
                }
            }
        }
        return books;
    }

    public List<BookResponse> findRecommendBooksRelatedContent(ContentRecommendationRequest contentRecommendationRequest) {
        User user = userUtils.getUserFromSecurityContext();
        Book book = bookRepository.findById(contentRecommendationRequest.bookId()).orElseThrow(()-> BookNotFoundException.EXCEPTION);

        List<BookResponse> bookResponses = new ArrayList<>();
        RecommendCache recommendCache = recommendCacheRepository.findFirstByRecommendations(book.getTitle()).orElse(null);
        if(recommendCache!=null){
            String[] titles =recommendCache.getTitleNm().split(", ");
            for(String recommendationTitle:titles){
                Optional<Book> requestBook = bookRepository.findFirstByTitle(recommendationTitle);
                if(!requestBook.isPresent()){
                    continue;
                }
                if(!myBookRepository.existsByUserAndBook(user,requestBook.get())){
                    bookResponses.add(new BookResponse(requestBook.get(),true));
                }
                if(bookResponses.size()==6){
                    break;
                }
            }
            if(bookResponses.size()<6){
                for(int i=1;i<5;i++){
                    List<BookResponse> requestBookResponses = requestRecommendBookRelatedContent(book.getTitle(),i);
                    for(BookResponse bookResponse:requestBookResponses){
                        bookResponses.add(bookResponse);
                        if(bookResponses.size()==6){
                            break;
                        }
                    }
                }
            }
        }
        return bookResponses;
    }

    public List<BookResponse> findRecommendBooksRelatedTag(TagRecommendationRequest tagRecommendationRequest) {

        if(tagRecommendationRequest.tags()==null || tagRecommendationRequest.tags().isEmpty()){
            return findRecommendBooksByUserInterest();
        }

        User user = userUtils.getUserFromSecurityContext();
        String url = "https://j11b204.p.ssafy.io/recommend/tag";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("id",user.getId());
        requestBody.put("tags",tagRecommendationRequest.tags());
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<TagRecommendResponse> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                TagRecommendResponse.class
        );

        List<BookResponse> books = new ArrayList<>();
        if (response.getBody() != null) {
            TagRecommendResponse recommendResponse = response.getBody();

            log.debug("[Spring] user : {}",user);
            List<Long> recommendedBookTitles = recommendResponse.recommendations();
            if (recommendedBookTitles != null && !recommendedBookTitles.isEmpty()) {
                for (Long bookId : recommendedBookTitles) {
                    Optional<Book> bookOptional = bookRepository.findById(bookId);
                    if(bookOptional.isPresent()){
                        boolean isBookMarked = existsByUserAndBook(user, bookOptional.get());
                        books.add(new BookResponse(bookOptional.get(), isBookMarked));
                    }
                }
            }
        }
        return books;
    }

    public List<BookResponse> requestRecommendBookRelatedContent(String title, int requestNumber){
        User user = userUtils.getUserFromSecurityContext();
        String url = "https://j11b204.p.ssafy.io/recommend/content?title="
                +title
                +"&request_number="
                +requestNumber;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> requestBody = new HashMap<>();
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<ContentRecommendResponse> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                ContentRecommendResponse.class
        );

        List<BookResponse> books = new ArrayList<>();
        if (response.getBody() != null) {
            ContentRecommendResponse recommendResponse = response.getBody();

            log.debug("[Spring] user : {}",user);
            List<String> recommendedTitles = recommendResponse.recommendations();
            if (recommendedTitles != null && !recommendedTitles.isEmpty()) {
                for (String recommendedtitle : recommendedTitles) {
                    Optional<Book> bookOptional = bookRepository.findFirstByTitle(recommendedtitle);
                    if(bookOptional.isPresent()){
                        boolean isBookMarked = existsByUserAndBook(user, bookOptional.get());
                        books.add(new BookResponse(bookOptional.get(), isBookMarked));
                    }
                }
            }
        }
        return books;
    }

    public boolean existsByUserAndBook(User user, Book book) {
        return bookmarkRepository.existsByUserAndBook(user, book);
    }
}
