package Web_Drink_Store.webstore.controller;

import Web_Drink_Store.webstore.dto.ApiResponse;
import Web_Drink_Store.webstore.dto.user.UserResponse;
import Web_Drink_Store.webstore.exception.UnauthorizedException;
import Web_Drink_Store.webstore.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
public class AdminUserController {

    private final UserService userService;

    public AdminUserController(
            UserService userService
    ) {
        this.userService = userService;
    }

    private void admin(HttpSession session) {

        if (!"ADMIN".equals(
                session.getAttribute("role")
        )) {
            throw new UnauthorizedException(
                    "Cần quyền ADMIN"
            );
        }
    }

    // Danh sách user
    @GetMapping
    public ApiResponse<List<UserResponse>> getAll(
            HttpSession session
    ) {

        admin(session);

        return ApiResponse.ok(
                "OK",
                userService.getAllUsers()
        );
    }

    // Chi tiết user
    @GetMapping("/{id}")
    public ApiResponse<UserResponse> getById(
            @PathVariable Long id,
            HttpSession session
    ) {

        admin(session);

        return ApiResponse.ok(
                "OK",
                userService.getUserById(id)
        );
    }

    // Khóa / mở khóa user
    @PatchMapping("/{id}/status")
    public ApiResponse<UserResponse> updateStatus(
            @PathVariable Long id,
            HttpSession session
    ) {

        admin(session);

        return ApiResponse.ok(
                "Cập nhật trạng thái thành công",
                userService.toggleUserStatus(id)
        );
    }
}