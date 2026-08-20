package Web_Drink_Store.webstore.controller;

import Web_Drink_Store.webstore.dto.*;
import Web_Drink_Store.webstore.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ApiResponse<OrderResponse> createOrder(Authentication auth, @Valid @RequestBody OrderRequest request) {
        return ApiResponse.success(orderService.createOrder(auth.getName(), request));
    }

    @GetMapping("/my")
    public ApiResponse<List<OrderResponse>> myOrders(Authentication auth) {
        return ApiResponse.success(orderService.getMyOrders(auth.getName()));
    }

    @GetMapping("/{id}")
    public ApiResponse<OrderResponse> getById(@PathVariable Long id) {
        return ApiResponse.success(orderService.getById(id));
    }
}
