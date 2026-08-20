package Web_Drink_Store.webstore.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProductRequest {
    @NotBlank
    private String name;
    private String description;

    @NotNull @Positive
    private BigDecimal price;

    private String imageUrl;
    private Long categoryId;
    private String tag;
}
