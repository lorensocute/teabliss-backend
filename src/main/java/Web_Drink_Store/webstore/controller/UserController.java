package Web_Drink_Store.webstore.controller;

import Web_Drink_Store.webstore.dto.ApiResponse;
import Web_Drink_Store.webstore.dto.user.UpdateProfileRequest;
import Web_Drink_Store.webstore.dto.user.UserResponse;
import Web_Drink_Store.webstore.exception.UnauthorizedException;
import Web_Drink_Store.webstore.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;
import Web_Drink_Store.webstore.dto.user.ChangePasswordRequest;
@RestController
@RequestMapping("/api/profile")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    private Long getUserId(HttpSession session) {
        Object userId = session.getAttribute("userId");

        if (userId == null) {
            throw new UnauthorizedException("Chưa đăng nhập");
        }

        return (Long) userId;
    }

    @GetMapping
    public ApiResponse<UserResponse> getProfile(HttpSession session) {
        return ApiResponse.ok(
                "OK",
                userService.getProfile(getUserId(session))
        );
    }

    @PutMapping
    public ApiResponse<UserResponse> updateProfile(
            @RequestBody UpdateProfileRequest request,
            HttpSession session
    ) {
        return ApiResponse.ok(
                "Cập nhật thông tin thành công",
                userService.updateProfile(
                        getUserId(session),
                        request
                )
        );
    }
    @PutMapping("/password")
    public ApiResponse<Void> changePassword(
            @RequestBody ChangePasswordRequest request,
            HttpSession session
    ) {
        userService.changePassword(
                getUserId(session),
                request
        );

        return ApiResponse.ok(
                "Đổi mật khẩu thành công",
                null
        );
    }
}