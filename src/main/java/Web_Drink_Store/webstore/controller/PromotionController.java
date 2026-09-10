package Web_Drink_Store.webstore.controller;

import Web_Drink_Store.webstore.dto.ApiResponse;
import Web_Drink_Store.webstore.dto.promotion.PromotionRequest;
import Web_Drink_Store.webstore.dto.promotion.PromotionResponse;
import Web_Drink_Store.webstore.exception.UnauthorizedException;
import Web_Drink_Store.webstore.service.PromotionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/promotions")
public class PromotionController {

    private final PromotionService service;

    public PromotionController(PromotionService service) {
        this.service = service;
    }

    private void admin(HttpSession session) {

        System.out.println("PROMOTION SESSION ID = " + session.getId());
        System.out.println("PROMOTION USER ID = " + session.getAttribute("userId"));
        System.out.println("PROMOTION ROLE = " + session.getAttribute("role"));

        Object role = session.getAttribute("role");

        if (!"ADMIN".equals(role)) {
            throw new UnauthorizedException(
                    "Cần quyền ADMIN. Role hiện tại: " + role
            );
        }
    }

    @GetMapping
    public ApiResponse<List<PromotionResponse>> all() {
        return ApiResponse.ok(
                "OK",
                service.getAll()
        );
    }

    @PostMapping
    public ApiResponse<PromotionResponse> create(
            @RequestBody PromotionRequest request,
            HttpSession session) {

        admin(session);

        return ApiResponse.ok(
                "Tạo thành công",
                service.create(request)
        );
    }

    @PutMapping("/{id}")
    public ApiResponse<PromotionResponse> update(
            @PathVariable Long id,
            @RequestBody PromotionRequest request,
            HttpSession session) {

        admin(session);

        return ApiResponse.ok(
                "Cập nhật thành công",
                service.update(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(
            @PathVariable Long id,
            HttpSession session) {

        admin(session);

        service.deactivate(id);

        return ApiResponse.ok(
                "Đã chuyển sang INACTIVE",
                null
        );
    }
}