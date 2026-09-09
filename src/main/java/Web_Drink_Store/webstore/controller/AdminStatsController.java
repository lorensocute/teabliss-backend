package Web_Drink_Store.webstore.controller;
import Web_Drink_Store.webstore.dto.ApiResponse; import Web_Drink_Store.webstore.dto.statistics.AdminStatsResponse; import Web_Drink_Store.webstore.exception.UnauthorizedException; import Web_Drink_Store.webstore.service.AdminService; import jakarta.servlet.http.HttpSession; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/admin/stats")
public class AdminStatsController {
    private final AdminService service; public AdminStatsController(AdminService s){service=s;} private void admin(HttpSession s){if(!"ADMIN".equals(s.getAttribute("role")))throw new UnauthorizedException("Cần quyền ADMIN");}
    @GetMapping public ApiResponse<AdminStatsResponse> stats(HttpSession s){admin(s);return ApiResponse.ok("OK",service.getStats());}
}
