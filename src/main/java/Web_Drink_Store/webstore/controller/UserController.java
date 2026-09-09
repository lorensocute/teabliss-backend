package Web_Drink_Store.webstore.controller;
import Web_Drink_Store.webstore.dto.ApiResponse; import Web_Drink_Store.webstore.dto.user.*; import Web_Drink_Store.webstore.exception.UnauthorizedException; import Web_Drink_Store.webstore.service.UserService; import jakarta.servlet.http.HttpSession; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/users")
public class UserController {
    private final UserService service; public UserController(UserService s){service=s;} private Long uid(HttpSession s){Object v=s.getAttribute("userId");if(v==null)throw new UnauthorizedException("Chưa đăng nhập");return (Long)v;}
    @GetMapping("/profile") public ApiResponse<UserResponse> profile(HttpSession s){return ApiResponse.ok("OK",service.getProfile(uid(s)));}
    @PutMapping("/profile") public ApiResponse<UserResponse> update(@RequestBody UpdateProfileRequest r,HttpSession s){return ApiResponse.ok("Cập nhật thành công",service.updateProfile(uid(s),r));}
}
