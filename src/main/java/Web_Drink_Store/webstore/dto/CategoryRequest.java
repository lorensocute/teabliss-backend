package Web_Drink_Store.webstore.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryRequest {
    @NotBlank
    private String name;
    private String slug;
    private String description;
    private String imageUrl;
}
