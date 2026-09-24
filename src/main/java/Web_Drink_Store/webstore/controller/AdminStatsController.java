package Web_Drink_Store.webstore.controller;

import Web_Drink_Store.webstore.dto.ApiResponse;

import Web_Drink_Store.webstore.dto.statistics.AdminStatsResponse;
import Web_Drink_Store.webstore.dto.statistics.OrderStatsResponse;
import Web_Drink_Store.webstore.dto.statistics.ProductStatsResponse;

import Web_Drink_Store.webstore.exception.UnauthorizedException;

import Web_Drink_Store.webstore.service.AdminService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/admin/statistics")
public class AdminStatsController {

    private final AdminService adminService;

    public AdminStatsController(
            AdminService adminService
    ) {
        this.adminService = adminService;
    }

    // =========================
    // KIỂM TRA ADMIN
    // =========================
    private void admin(
            HttpSession session
    ) {

        if (!"ADMIN".equals(
                session.getAttribute("role")
        )) {

            throw new UnauthorizedException(
                    "Cần quyền ADMIN"
            );
        }
    }

    // =========================
    // OVERVIEW
    // =========================
    @GetMapping("/overview")
    public ApiResponse<AdminStatsResponse>
    getOverview(
            HttpSession session
    ) {

        admin(session);

        return ApiResponse.ok(
                "Lấy thống kê tổng quan thành công",
                adminService.getStatistics()
        );
    }

    // =========================
    // REVENUE
    // =========================
    @GetMapping("/revenue")
    public ApiResponse<BigDecimal>
    getRevenue(
            @RequestParam LocalDate from,
            @RequestParam LocalDate to,
            HttpSession session
    ) {

        admin(session);

        return ApiResponse.ok(
                "Lấy doanh thu thành công",
                adminService.getRevenue(
                        from,
                        to
                )
        );
    }

    // =========================
    // ORDERS
    // =========================
    @GetMapping("/orders")
    public ApiResponse<OrderStatsResponse>
    getOrders(
            HttpSession session
    ) {

        admin(session);

        return ApiResponse.ok(
                "Lấy thống kê đơn hàng thành công",
                adminService.getOrderStatistics()
        );
    }

    // =========================
    // PRODUCTS
    // =========================
    @GetMapping("/products")
    public ApiResponse<List<ProductStatsResponse>>
    getProducts(
            HttpSession session
    ) {

        admin(session);

        return ApiResponse.ok(
                "Lấy thống kê sản phẩm bán chạy thành công",
                adminService.getProductStatistics()
        );
    }
}