package Web_Drink_Store.webstore.dto.category;
import Web_Drink_Store.webstore.enums.CategoryStatus;
public class CategoryResponse {
    private Long id; private String name; private String description; private CategoryStatus status;
    public CategoryResponse(Long id,String name,String description,CategoryStatus status){this.id=id;this.name=name;this.description=description;this.status=status;}
    public Long getId(){return id;} public String getName(){return name;} public String getDescription(){return description;} public CategoryStatus getStatus(){return status;}
}
