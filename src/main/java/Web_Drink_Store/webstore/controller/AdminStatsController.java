package Web_Drink_Store.webstore.controller;

import Web_Drink_Store.webstore.dto.AdminStatsResponse;
import Web_Drink_Store.webstore.dto.ApiResponse;
import Web_Drink_Store.webstore.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/stats")
@RequiredArgsConstructor
public class AdminStatsController {

    private final AdminService adminService;

    @GetMapping
    public ApiResponse<AdminStatsResponse> getStats() {
        return ApiResponse.success(adminService.getStats());
    }
}
