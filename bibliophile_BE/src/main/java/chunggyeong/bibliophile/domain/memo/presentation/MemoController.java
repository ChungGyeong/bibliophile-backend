package chunggyeong.bibliophile.domain.memo.presentation;

import chunggyeong.bibliophile.domain.memo.presentation.dto.request.AddMemoRequest;
import chunggyeong.bibliophile.domain.memo.presentation.dto.request.UpdateMemoRequest;
import chunggyeong.bibliophile.domain.memo.presentation.dto.response.MemoResponse;
import chunggyeong.bibliophile.domain.memo.service.MemoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "메모", description = "메모 관련 API")
@RestController
@RequestMapping("/api/memos")
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    @Operation(summary = "메모 추가")
    @PostMapping()
    public MemoResponse addBookmark(@RequestBody @Valid AddMemoRequest addMemoRequest) {
        return memoService.addMemo(addMemoRequest);
    }

    @Operation(summary = "메모 단건 보기")
    @GetMapping("/{memoId}")
    public MemoResponse findMemoByMemoId(@PathVariable Long memoId) {
        return memoService.findMemoByMemoId(memoId);
    }

    @Operation(summary = "나의 책 내가 작성한 메모 리스트 보기")
    @GetMapping("/mine/{myBookId}")
    public List<MemoResponse> findMemoByUser(@PathVariable Long myBookId) {
        return memoService.findMemoByUser(myBookId);
    }

    @Operation(summary = "메모 수정")
    @PatchMapping("/{memoId}")
    public MemoResponse updateMemo(@PathVariable Long memoId, @RequestBody @Valid UpdateMemoRequest updateMemoRequest) {
        return memoService.updateMemo(memoId, updateMemoRequest);
    }

    @Operation(summary = "메모 삭제")
    @DeleteMapping("/{memoId}")
    public void deleteMemo(@PathVariable Long memoId) {
        memoService.deleteMemo(memoId);
    }
}
