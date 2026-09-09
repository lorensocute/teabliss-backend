package Web_Drink_Store.webstore.controller;
import Web_Drink_Store.webstore.dto.ApiResponse; import Web_Drink_Store.webstore.dto.promotion.*; import Web_Drink_Store.webstore.exception.UnauthorizedException; import Web_Drink_Store.webstore.service.PromotionService; import jakarta.servlet.http.HttpSession; import org.springframework.web.bind.annotation.*; import java.util.List;
@RestController @RequestMapping("/api/promotions")
public class PromotionController {
    private final PromotionService service; public PromotionController(PromotionService s){service=s;} private void admin(HttpSession s){if(!"ADMIN".equals(s.getAttribute("role")))throw new UnauthorizedException("Cần quyền ADMIN");}
    @GetMapping public ApiResponse<List<PromotionResponse>> all(){return ApiResponse.ok("OK",service.getAll());}
    @PostMapping public ApiResponse<PromotionResponse> create(@RequestBody PromotionRequest r,HttpSession s){admin(s);return ApiResponse.ok("Tạo thành công",service.create(r));}
    @PutMapping("/{id}") public ApiResponse<PromotionResponse> update(@PathVariable Long id,@RequestBody PromotionRequest r,HttpSession s){admin(s);return ApiResponse.ok("Cập nhật thành công",service.update(id,r));}
    @DeleteMapping("/{id}") public ApiResponse<Void> delete(@PathVariable Long id,HttpSession s){admin(s);service.deactivate(id);return ApiResponse.ok("Đã chuyển sang INACTIVE",null);}
}
