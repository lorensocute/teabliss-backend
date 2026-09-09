package Web_Drink_Store.webstore.dto.product;
import Web_Drink_Store.webstore.enums.ProductStatus;
import java.math.BigDecimal;
public class ProductResponse {
    private Long id; private String name; private String description; private String imageUrl; private BigDecimal price; private Integer stockQuantity; private Long categoryId; private String categoryName; private ProductStatus status;
    public ProductResponse(Long id,String name,String description,String imageUrl,BigDecimal price,Integer stockQuantity,Long categoryId,String categoryName,ProductStatus status){this.id=id;this.name=name;this.description=description;this.imageUrl=imageUrl;this.price=price;this.stockQuantity=stockQuantity;this.categoryId=categoryId;this.categoryName=categoryName;this.status=status;}
    public Long getId(){return id;} public String getName(){return name;} public String getDescription(){return description;} public String getImageUrl(){return imageUrl;} public BigDecimal getPrice(){return price;} public Integer getStockQuantity(){return stockQuantity;} public Long getCategoryId(){return categoryId;} public String getCategoryName(){return categoryName;} public ProductStatus getStatus(){return status;}
}
