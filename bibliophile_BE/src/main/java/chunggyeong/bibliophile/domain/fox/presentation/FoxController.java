package chunggyeong.bibliophile.domain.fox.presentation;

import chunggyeong.bibliophile.domain.fox.presentation.dto.response.FoxResponse;
import chunggyeong.bibliophile.domain.fox.service.FoxService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "여우", description = "여우 관련 API")
@RestController
@RequestMapping("/api/foxes")
@RequiredArgsConstructor
public class FoxController {

    private final FoxService foxService;

    @Operation(summary = "내 여우 조회")
    @GetMapping
    public FoxResponse findMyFox() {
        return foxService.findMyFoxByUser();
    }

    @Operation(summary = "내 여우 먹이주기")
    @PatchMapping("/feed")
    public FoxResponse updateFox() {
        return foxService.updateMyFoxFeedCount();
    }
}
