package Web_Drink_Store.webstore.controller;

import Web_Drink_Store.webstore.dto.*;
import Web_Drink_Store.webstore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ApiResponse<UserResponse> getProfile(Authentication authentication) {
        return ApiResponse.success(userService.getProfile(authentication.getName()));
    }

    @PutMapping("/me")
    public ApiResponse<UserResponse> updateProfile(Authentication authentication, @RequestBody UpdateProfileRequest request) {
        return ApiResponse.success(userService.updateProfile(authentication.getName(), request));
    }
}
