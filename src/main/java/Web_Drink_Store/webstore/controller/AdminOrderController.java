package Web_Drink_Store.webstore.controller;
import Web_Drink_Store.webstore.dto.ApiResponse; import Web_Drink_Store.webstore.dto.order.*; import Web_Drink_Store.webstore.exception.UnauthorizedException; import Web_Drink_Store.webstore.service.OrderService; import jakarta.servlet.http.HttpSession; import org.springframework.web.bind.annotation.*; import java.util.List;
@RestController @RequestMapping("/api/admin/orders")
public class AdminOrderController {
    private final OrderService service; public AdminOrderController(OrderService s){service=s;} private void admin(HttpSession s){if(!"ADMIN".equals(s.getAttribute("role")))throw new UnauthorizedException("Cần quyền ADMIN");}
    @GetMapping public ApiResponse<List<OrderResponse>> all(HttpSession s){admin(s);return ApiResponse.ok("OK",service.getAll());}
    @PatchMapping("/{id}/status") public ApiResponse<OrderResponse> status(@PathVariable Long id,@RequestBody UpdateOrderStatusRequest r,HttpSession s){admin(s);return ApiResponse.ok("Đã cập nhật trạng thái",service.updateStatus(id,r.getStatus()));}
}
