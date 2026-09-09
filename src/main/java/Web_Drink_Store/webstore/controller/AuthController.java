package Web_Drink_Store.webstore.controller;
import Web_Drink_Store.webstore.dto.ApiResponse; import Web_Drink_Store.webstore.dto.auth.*; import Web_Drink_Store.webstore.service.AuthService; import jakarta.servlet.http.HttpSession; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/auth")
public class AuthController {
    private final AuthService auth; public AuthController(AuthService auth){this.auth=auth;}
    @PostMapping("/register") public ApiResponse<AuthResponse> register(@RequestBody RegisterRequest r){return ApiResponse.ok("Đăng ký thành công",auth.register(r));}
    @PostMapping("/login") public ApiResponse<AuthResponse> login(@RequestBody LoginRequest r,HttpSession session){AuthResponse res=auth.login(r);session.setAttribute("userId",res.getId());session.setAttribute("role",res.getRole().name());return ApiResponse.ok("Đăng nhập thành công",res);}
    @PostMapping("/logout") public ApiResponse<Void> logout(HttpSession session){session.invalidate();return ApiResponse.ok("Đăng xuất thành công",null);}
}
