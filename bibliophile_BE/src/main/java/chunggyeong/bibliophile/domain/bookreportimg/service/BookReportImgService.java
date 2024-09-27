package chunggyeong.bibliophile.domain.bookreportimg.service;

import chunggyeong.bibliophile.domain.bookreport.domain.BookReport;
import chunggyeong.bibliophile.domain.bookreportimg.domain.BookReportImg;
import chunggyeong.bibliophile.domain.bookreportimg.domain.repository.BookReportImgRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookReportImgService implements BookReportImgServiceUtils {

    private final BookReportImgRepository bookReportImgRepository;

    @Override
    public List<BookReportImg> addBookReportImg(BookReport bookReport, List<String> bookReviewImgUrl) {
        if(bookReportImgRepository.existsByBookReport(bookReport)){
            bookReportImgRepository.deleteAllByBookReport(bookReport);
        }

        return bookReviewImgUrl.stream()
                .map(url->{
                    BookReportImg bookReportImg = BookReportImg.createBookReportImg(bookReport,url);
                    return bookReportImgRepository.save(bookReportImg);
                })
                .collect(Collectors.toList());
    }
}
