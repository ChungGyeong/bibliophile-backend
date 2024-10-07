package chunggyeong.bibliophile.domain.bookreport.service;

import chunggyeong.bibliophile.domain.bookreport.domain.BookReport;
import chunggyeong.bibliophile.domain.bookreport.domain.repository.BookReportRepository;
import chunggyeong.bibliophile.domain.bookreport.exception.BookReportNotFoundException;
import chunggyeong.bibliophile.domain.bookreport.exception.ExistBookReportException;
import chunggyeong.bibliophile.domain.bookreport.presentation.dto.request.AddBookReportRequest;
import chunggyeong.bibliophile.domain.bookreport.presentation.dto.request.UpdateBookReportRequest;
import chunggyeong.bibliophile.domain.bookreport.presentation.dto.response.BookReportResponse;
import chunggyeong.bibliophile.domain.bookreportimg.domain.BookReportImg;
import chunggyeong.bibliophile.domain.bookreportimg.domain.repository.BookReportImgRepository;
import chunggyeong.bibliophile.domain.bookreportimg.service.BookReportImgServiceUtils;
import chunggyeong.bibliophile.domain.fox.domain.Fox;
import chunggyeong.bibliophile.domain.fox.service.FoxServiceUtils;
import chunggyeong.bibliophile.domain.myBook.domain.MyBook;
import chunggyeong.bibliophile.domain.myBook.service.MyBookServiceUtils;
import chunggyeong.bibliophile.domain.user.domain.User;
import chunggyeong.bibliophile.global.utils.user.UserUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookReportService implements BookReportServiceUtils {

    private final BookReportRepository bookReportRepository;
    private final MyBookServiceUtils myBookServiceUtils;
    private final UserUtils userUtils;
    private final BookReportImgServiceUtils bookReportImgServiceUtils;
    private final BookReportImgRepository bookReportImgRepository;
    private final FoxServiceUtils foxServiceUtils;

    // 독후감 작성
    @Transactional
    public BookReportResponse addBookReport(AddBookReportRequest addBookReportRequest){
        User user = userUtils.getUserFromSecurityContext();
        MyBook myBook = myBookServiceUtils.queryMyBook(addBookReportRequest.myBookId());

        myBookServiceUtils.validUserIsHost(myBook, user);
        existsByMyBook(user,myBook);

        BookReport bookReport = BookReport.createBookReport(addBookReportRequest.content(), myBook);
        bookReportRepository.save(bookReport);

        List<String> bookReportImgUrlList = bookReportImgServiceUtils.addBookReportImg(bookReport, addBookReportRequest.ImgUrl()).stream()
                .map(BookReportImg::getImgUrl)
                .toList();

        Fox fox = foxServiceUtils.queryFoxByUser(user);
        fox.updateAddFoxFeedCount();

        return new BookReportResponse(bookReport, bookReportImgUrlList);
    }

    // 독후감 단건 조회
    public BookReportResponse findBookReportByBookId(Long bookReportId){
        BookReport bookReport = queryBookReport(bookReportId);
        List<String> bookReportImgUrlList = bookReport.getBookReportImgList().stream()
                .map(BookReportImg::getImgUrl)
                .toList();
        return new BookReportResponse(bookReport, bookReportImgUrlList);
    }

    // 내가 작성한 독후감 조회
    public BookReportResponse findBookReportByUserAndMyBookId(Long myBookId){
        MyBook myBook = myBookServiceUtils.queryMyBook(myBookId);
        User user = userUtils.getUserFromSecurityContext();

        myBookServiceUtils.validUserIsHost(myBook, user);

        BookReport bookReport = bookReportRepository.findByMyBook(myBook)
                .orElse(null);

        if(bookReport == null){ return null; }
        if(!bookReportImgRepository.existsByBookReport(bookReport)){
            return new BookReportResponse(bookReport, null);
        }
        List<String> bookReportImgUrlList = bookReport.getBookReportImgList().stream()
                .map(BookReportImg::getImgUrl)
                .toList();
        return new BookReportResponse(bookReport, bookReportImgUrlList);
    }

    // 독후감 수정
    @Transactional
    public BookReportResponse updateBookReport(Long bookReportId, UpdateBookReportRequest updateBookReportRequest){
        BookReport bookReport = queryBookReport(bookReportId);
        User user = userUtils.getUserFromSecurityContext();

        bookReport.validUserIsHost(user);
        bookReport.updateBookReport(updateBookReportRequest.content());

        List<String> bookReportImgUrlList = bookReportImgServiceUtils.addBookReportImg(bookReport, updateBookReportRequest.bookReportImgUrl()).stream()
                .map(BookReportImg::getImgUrl)
                .toList();

        return new BookReportResponse(bookReport, bookReportImgUrlList);
    }

    // 독후감 삭제
    @Transactional
    public void deleteBookReport(Long bookReportId){
        BookReport bookReport = queryBookReport(bookReportId);
        User user = userUtils.getUserFromSecurityContext();

        bookReport.validUserIsHost(user);

        bookReportRepository.delete(bookReport);
    }

    @Override
    public BookReport queryBookReport(Long bookReportId) {
        return bookReportRepository.findById(bookReportId).orElseThrow(() -> BookReportNotFoundException.EXCEPTION);
    }

    @Override
    public void existsByMyBook(User user, MyBook myBook) {
        if(bookReportRepository.existsByUserAndMyBook(user,myBook)){
            throw ExistBookReportException.EXCEPTION;
        }
    }

}
