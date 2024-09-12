package chunggyeong.bibliophile.domain.user.presentation;

import chunggyeong.bibliophile.domain.user.presentation.dto.request.CheckNicknameRequest;
import chunggyeong.bibliophile.domain.user.presentation.dto.request.SignUpUserRequest;
import chunggyeong.bibliophile.domain.user.presentation.dto.response.CheckNicknameResponse;
import chunggyeong.bibliophile.domain.user.presentation.dto.response.UserProfileResponse;
import chunggyeong.bibliophile.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "유저", description = "유저 관련 API")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @SecurityRequirements
    @Operation(summary = "회원가입")
    @PostMapping("/signup")
    public UserProfileResponse signUpUser(
            @RequestBody @Valid SignUpUserRequest signUpUserRequest,
            HttpServletResponse response) {
        return userService.signUp(signUpUserRequest, response);
    }

    @Operation(summary = "닉네임 중복 확인")
    @GetMapping("/check-nickname")
    public CheckNicknameResponse checkNickname(@RequestBody CheckNicknameRequest nicknameCheckRequest) {
        return userService.checkNickname(nicknameCheckRequest);
    }

    @Operation(summary = "로그아웃")
    @PostMapping("/logout")
    public void logout(HttpServletResponse response) {
        userService.logout(response);
    }
}
