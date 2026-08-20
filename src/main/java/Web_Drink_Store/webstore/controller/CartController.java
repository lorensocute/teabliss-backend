package Web_Drink_Store.webstore.controller;

import Web_Drink_Store.webstore.dto.*;
import Web_Drink_Store.webstore.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public ApiResponse<CartResponse> getCart(Authentication auth) {
        return ApiResponse.success(cartService.getCart(auth.getName()));
    }

    @PostMapping("/items")
    public ApiResponse<CartResponse> addItem(Authentication auth, @Valid @RequestBody CartItemRequest request) {
        return ApiResponse.success(cartService.addItem(auth.getName(), request));
    }

    @PutMapping("/items/{itemId}")
    public ApiResponse<CartResponse> updateItem(Authentication auth, @PathVariable Long itemId, @RequestParam Integer quantity) {
        return ApiResponse.success(cartService.updateItem(auth.getName(), itemId, quantity));
    }

    @DeleteMapping("/items/{itemId}")
    public ApiResponse<CartResponse> removeItem(Authentication auth, @PathVariable Long itemId) {
        return ApiResponse.success(cartService.removeItem(auth.getName(), itemId));
    }

    @DeleteMapping
    public ApiResponse<Void> clearCart(Authentication auth) {
        cartService.clearCart(auth.getName());
        return ApiResponse.success(null);
    }
}
