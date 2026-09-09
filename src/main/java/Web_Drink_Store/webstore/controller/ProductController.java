package Web_Drink_Store.webstore.controller;
import Web_Drink_Store.webstore.dto.ApiResponse; import Web_Drink_Store.webstore.dto.product.*; import Web_Drink_Store.webstore.exception.UnauthorizedException; import Web_Drink_Store.webstore.service.ProductService; import jakarta.servlet.http.HttpSession; import org.springframework.web.bind.annotation.*; import java.util.List;
@RestController @RequestMapping("/api/products")
public class ProductController {
    private final ProductService service; public ProductController(ProductService s){service=s;} private void admin(HttpSession s){if(!"ADMIN".equals(s.getAttribute("role")))throw new UnauthorizedException("Cần quyền ADMIN");}
    @GetMapping public ApiResponse<List<ProductResponse>> all(@RequestParam(required=false) Long categoryId){return ApiResponse.ok("OK",service.getActive(categoryId));}
    @GetMapping("/{id}") public ApiResponse<ProductResponse> one(@PathVariable Long id){return ApiResponse.ok("OK",service.getById(id));}
    @PostMapping public ApiResponse<ProductResponse> create(@RequestBody ProductRequest r,HttpSession s){admin(s);return ApiResponse.ok("Tạo thành công",service.create(r));}
    @PutMapping("/{id}") public ApiResponse<ProductResponse> update(@PathVariable Long id,@RequestBody ProductRequest r,HttpSession s){admin(s);return ApiResponse.ok("Cập nhật thành công",service.update(id,r));}
    @DeleteMapping("/{id}") public ApiResponse<Void> delete(@PathVariable Long id,HttpSession s){admin(s);service.deactivate(id);return ApiResponse.ok("Đã chuyển sang INACTIVE",null);}
}
