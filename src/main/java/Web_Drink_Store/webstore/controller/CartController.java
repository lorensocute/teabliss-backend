package Web_Drink_Store.webstore.controller;
import Web_Drink_Store.webstore.dto.ApiResponse; import Web_Drink_Store.webstore.dto.cart.*; import Web_Drink_Store.webstore.exception.UnauthorizedException; import Web_Drink_Store.webstore.service.CartService; import jakarta.servlet.http.HttpSession; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/cart")
public class CartController {
    private final CartService service; public CartController(CartService s){service=s;} private Long uid(HttpSession s){Object v=s.getAttribute("userId");if(v==null)throw new UnauthorizedException("Chưa đăng nhập");return (Long)v;}
    @GetMapping public ApiResponse<CartResponse> get(HttpSession s){return ApiResponse.ok("OK",service.getCart(uid(s)));}
    @PostMapping("/items") public ApiResponse<CartResponse> add(@RequestBody CartItemRequest r,HttpSession s){return ApiResponse.ok("Đã thêm vào giỏ",service.addItem(uid(s),r));}
    @PutMapping("/items/{id}") public ApiResponse<CartResponse> update(@PathVariable Long id,@RequestParam Integer quantity,HttpSession s){return ApiResponse.ok("Đã cập nhật",service.updateItem(uid(s),id,quantity));}
    @DeleteMapping("/items/{id}") public ApiResponse<CartResponse> remove(@PathVariable Long id,HttpSession s){return ApiResponse.ok("Đã xóa",service.removeItem(uid(s),id));}
}
