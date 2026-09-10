package Web_Drink_Store.webstore.controller;

import Web_Drink_Store.webstore.dto.ApiResponse;
import Web_Drink_Store.webstore.dto.statistics.AdminStatsResponse;
import Web_Drink_Store.webstore.exception.UnauthorizedException;
import Web_Drink_Store.webstore.service.AdminService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/statistics")
public class AdminStatsController {

    private final AdminService adminService;

    public AdminStatsController(AdminService adminService) {
        this.adminService = adminService;
    }

    private void admin(HttpSession session) {
        if (!"ADMIN".equals(session.getAttribute("role"))) {
            throw new UnauthorizedException("Cần quyền ADMIN");
        }
    }

    @GetMapping
    public ApiResponse<AdminStatsResponse> getStatistics(
            HttpSession session
    ) {

        admin(session);

        return ApiResponse.ok(
                "Lấy thống kê thành công",
                adminService.getStatistics()
        );
    }
}