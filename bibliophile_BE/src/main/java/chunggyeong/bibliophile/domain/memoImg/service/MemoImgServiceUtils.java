package chunggyeong.bibliophile.domain.memoImg.service;

import chunggyeong.bibliophile.domain.memo.domain.Memo;
import chunggyeong.bibliophile.domain.memoImg.domain.MemoImg;

import java.util.List;

public interface MemoImgServiceUtils {
    List<MemoImg> addMemoImg(Memo memo, List<String> memoImgUrl);
}
