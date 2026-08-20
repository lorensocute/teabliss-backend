package Web_Drink_Store.webstore.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OrderRequest {
    @NotBlank
    private String shippingAddress;

    @NotBlank
    private String phone;

    private String note;
}
