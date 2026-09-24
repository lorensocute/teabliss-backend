package Web_Drink_Store.webstore.controller;

import Web_Drink_Store.webstore.dto.ApiResponse;
import Web_Drink_Store.webstore.dto.address.AddressRequest;
import Web_Drink_Store.webstore.dto.address.AddressResponse;
import Web_Drink_Store.webstore.exception.UnauthorizedException;
import Web_Drink_Store.webstore.service.AddressService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profile/addresses")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    private Long getUserId(HttpSession session) {
        Object userId = session.getAttribute("userId");

        if (userId == null) {
            throw new UnauthorizedException("Chưa đăng nhập");
        }

        return (Long) userId;
    }

    @GetMapping
    public ApiResponse<List<AddressResponse>> getAll(
            HttpSession session
    ) {
        return ApiResponse.ok(
                "OK",
                addressService.getAll(getUserId(session))
        );
    }

    @PostMapping
    public ApiResponse<AddressResponse> create(
            @RequestBody AddressRequest request,
            HttpSession session
    ) {
        return ApiResponse.ok(
                "Thêm địa chỉ thành công",
                addressService.create(
                        getUserId(session),
                        request
                )
        );
    }

    @PutMapping("/{id}")
    public ApiResponse<AddressResponse> update(
            @PathVariable Long id,
            @RequestBody AddressRequest request,
            HttpSession session
    ) {
        return ApiResponse.ok(
                "Cập nhật địa chỉ thành công",
                addressService.update(
                        getUserId(session),
                        id,
                        request
                )
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(
            @PathVariable Long id,
            HttpSession session
    ) {
        addressService.delete(
                getUserId(session),
                id
        );

        return ApiResponse.ok(
                "Xóa địa chỉ thành công",
                null
        );
    }
}