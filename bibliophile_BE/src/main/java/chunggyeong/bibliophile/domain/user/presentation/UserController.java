package chunggyeong.bibliophile.domain.user.presentation;

import chunggyeong.bibliophile.domain.user.presentation.dto.request.CheckNicknameRequest;
import chunggyeong.bibliophile.domain.user.presentation.dto.request.SignUpUserRequest;
import chunggyeong.bibliophile.domain.user.presentation.dto.request.UpdateUserRequest;
import chunggyeong.bibliophile.domain.user.presentation.dto.response.CheckNicknameResponse;
import chunggyeong.bibliophile.domain.user.presentation.dto.response.UserProfileResponse;
import chunggyeong.bibliophile.domain.user.presentation.dto.response.UserWordCloudResponse;
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

    @Operation(summary = "로그아웃")
    @PostMapping("/logout")
    public void logout(HttpServletResponse response) {
        userService.logout(response);
    }

    @Operation(summary = "회원 정보 조회")
    @GetMapping
    public UserProfileResponse findUser() {
        return userService.findUser();
    }

    @Operation(summary = "회원 정보 수정")
    @PatchMapping
    public UserProfileResponse updateUser(@RequestBody @Valid UpdateUserRequest updateUserRequest) {
        return userService.updateUser(updateUserRequest);
    }

    @Operation(summary = "회원 탈퇴")
    @DeleteMapping
    public void withdraw(HttpServletResponse response) {
        userService.withdraw(response);
    }

    @Operation(summary = "워드 클라우드 조회")
    @GetMapping("/word-cloud")
    public UserWordCloudResponse getWordCloud() {
        return userService.getWordCloud();
    }
}
