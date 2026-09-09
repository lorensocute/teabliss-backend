package Web_Drink_Store.webstore.service;
import Web_Drink_Store.webstore.dto.cart.*;
public interface CartService { CartResponse getCart(Long userId); CartResponse addItem(Long userId,CartItemRequest request); CartResponse updateItem(Long userId,Long itemId,Integer quantity); CartResponse removeItem(Long userId,Long itemId); void clear(Long userId); }
