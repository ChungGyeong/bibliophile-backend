package chunggyeong.bibliophile.domain.memo.service;

import chunggyeong.bibliophile.domain.fox.domain.Fox;
import chunggyeong.bibliophile.domain.fox.service.FoxServiceUtils;
import chunggyeong.bibliophile.domain.memo.domain.Memo;
import chunggyeong.bibliophile.domain.memo.domain.repository.MemoRepository;
import chunggyeong.bibliophile.domain.memo.exception.MemoNotFoundException;
import chunggyeong.bibliophile.domain.memo.presentation.dto.request.AddMemoRequest;
import chunggyeong.bibliophile.domain.memo.presentation.dto.request.UpdateMemoRequest;
import chunggyeong.bibliophile.domain.memo.presentation.dto.response.MemoResponse;
import chunggyeong.bibliophile.domain.memoImg.domain.MemoImg;
import chunggyeong.bibliophile.domain.memoImg.service.MemoImgServiceUtils;
import chunggyeong.bibliophile.domain.myBook.domain.MyBook;
import chunggyeong.bibliophile.domain.myBook.exception.PageLimitExceededException;
import chunggyeong.bibliophile.domain.myBook.service.MyBookServiceUtils;
import chunggyeong.bibliophile.domain.user.domain.User;
import chunggyeong.bibliophile.global.utils.user.UserUtils;
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
public class MemoService implements MemoServiceUtils {

    private final MemoRepository memoRepository;
    private final MyBookServiceUtils myBookServiceUtils;
    private final UserUtils userUtils;
    private final MemoImgServiceUtils memoImgServiceUtils;
    private final FoxServiceUtils foxServiceUtils;

    // 메모 작성
    @Transactional
    public MemoResponse addMemo(AddMemoRequest addMemoRequest) {
        MyBook myBook = myBookServiceUtils.queryMyBook(addMemoRequest.myBookId());
        User user = userUtils.getUserFromSecurityContext();

        myBookServiceUtils.validUserIsHost(myBook, user);
        validMemoPage(myBook, addMemoRequest.memoPage());

        Memo memo = Memo.createMemo(addMemoRequest.content(), addMemoRequest.memoPage(), myBook);

        memoRepository.save(memo);

        List<String> memoImgList = memoImgServiceUtils.addMemoImg(memo, addMemoRequest.memoImgUrl()).stream()
                .map(MemoImg::getImgUrl)
                .toList();

        Fox fox = foxServiceUtils.queryFoxByUser(user);
        fox.updateAddFoxFeedCount();

        return new MemoResponse(memo, memoImgList);
    }


    // 메모 단건 보기
    public MemoResponse findMemoByMemoId(Long memoId) {
        Memo memo = queryMemo(memoId);
        List<String> memoImgurlList = memo.getMemoImgList().stream()
                .map(MemoImg::getImgUrl)
                .toList();

        return new MemoResponse(memo, memoImgurlList);
    }

    // 나의 책 내가 작성한 메모 리스트 보기
    public List<MemoResponse> findMemoByUser(Long myBookId) {
        MyBook myBook = myBookServiceUtils.queryMyBook(myBookId);
        User user = userUtils.getUserFromSecurityContext();

        myBookServiceUtils.validUserIsHost(myBook, user);

        List<Memo> memoList = memoRepository.findAllByMyBook(myBook);

        return memoList.stream()
                .map(memo -> {
                    List<String> memoImgurlList = memo.getMemoImgList().stream()
                            .map(MemoImg::getImgUrl)
                            .collect(Collectors.toList());
                    return new MemoResponse(memo, memoImgurlList);
                })
                .collect(Collectors.toList());
    }


    // 메모 수정
    @Transactional
    public MemoResponse updateMemo(Long memoId, UpdateMemoRequest updateMemoRequest) {
        Memo memo = queryMemo(memoId);
        User user = userUtils.getUserFromSecurityContext();

        memo.validUserIsHost(user);
        validMemoPage(memo.getMyBook(), updateMemoRequest.memoPage());
        memo.updateMemo(updateMemoRequest.content(), updateMemoRequest.memoPage());

        List<String> imgurlList = memoImgServiceUtils.addMemoImg(memo, updateMemoRequest.memoImgUrl()).stream()
                .map(MemoImg::getImgUrl)
                .toList();

        return new MemoResponse(memo, imgurlList);
    }

    // 메모 삭제
    @Transactional
    public void deleteMemo(Long memoId) {
        Memo memo = queryMemo(memoId);
        User user = userUtils.getUserFromSecurityContext();

        memo.validUserIsHost(user);

        memoRepository.delete(memo);
    }

    private void validMemoPage(MyBook myBook, int memoPage) {
        if (myBook.getBook().getPage() < memoPage) {
            throw PageLimitExceededException.EXCEPTION;
        }
    }

    @Override
    public Memo queryMemo(Long memoId) {
        return memoRepository.findById(memoId).orElseThrow(() -> MemoNotFoundException.EXCEPTION);
    }
}
