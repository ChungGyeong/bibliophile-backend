package chunggyeong.bibliophile.domain.memoImg.service;

import chunggyeong.bibliophile.domain.file.presentation.dto.response.UploadFileResponse;
import chunggyeong.bibliophile.domain.file.service.FileServiceUtils;
import chunggyeong.bibliophile.domain.memo.domain.Memo;
import chunggyeong.bibliophile.domain.memoImg.domain.MemoImg;
import chunggyeong.bibliophile.domain.memoImg.domain.repository.MemoImgRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemoImgService implements MemoImgServiceUtils {

    private final MemoImgRepository memoImgRepository;

    // 메모 이미지 등록
    @Override
    public List<MemoImg> addMemoImg(Memo memo, List<String> memoImgUrl) {
        if (memoImgRepository.existsByMemo(memo)) {
            memoImgRepository.deleteAllByMemo(memo);
        }

        return memoImgUrl.stream()
                .map(url -> {
                    MemoImg memoImg = MemoImg.craeteMemoImg(memo, url);
                    return memoImgRepository.save(memoImg);
                })
                .collect(Collectors.toList());
    }

}
