package Web_Drink_Store.webstore.controller;
import Web_Drink_Store.webstore.dto.ApiResponse; import Web_Drink_Store.webstore.dto.address.*; import Web_Drink_Store.webstore.exception.UnauthorizedException; import Web_Drink_Store.webstore.service.AddressService; import jakarta.servlet.http.HttpSession; import org.springframework.web.bind.annotation.*; import java.util.List;
@RestController @RequestMapping("/api/addresses")
public class AddressController {
    private final AddressService service; public AddressController(AddressService s){service=s;} private Long uid(HttpSession s){Object v=s.getAttribute("userId");if(v==null)throw new UnauthorizedException("Chưa đăng nhập");return (Long)v;}
    @GetMapping public ApiResponse<List<AddressResponse>> all(HttpSession s){return ApiResponse.ok("OK",service.getAll(uid(s)));}
    @PostMapping public ApiResponse<AddressResponse> create(@RequestBody AddressRequest r,HttpSession s){return ApiResponse.ok("Tạo địa chỉ thành công",service.create(uid(s),r));}
    @PutMapping("/{id}") public ApiResponse<AddressResponse> update(@PathVariable Long id,@RequestBody AddressRequest r,HttpSession s){return ApiResponse.ok("Cập nhật thành công",service.update(uid(s),id,r));}
    @DeleteMapping("/{id}") public ApiResponse<Void> delete(@PathVariable Long id,HttpSession s){service.delete(uid(s),id);return ApiResponse.ok("Xóa thành công",null);}
}
