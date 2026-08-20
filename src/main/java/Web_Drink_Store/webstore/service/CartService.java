package Web_Drink_Store.webstore.service;

import Web_Drink_Store.webstore.dto.CartItemRequest;
import Web_Drink_Store.webstore.dto.CartResponse;

public interface CartService {
    CartResponse getCart(String email);
    CartResponse addItem(String email, CartItemRequest request);
    CartResponse updateItem(String email, Long itemId, Integer quantity);
    CartResponse removeItem(String email, Long itemId);
    void clearCart(String email);
}
