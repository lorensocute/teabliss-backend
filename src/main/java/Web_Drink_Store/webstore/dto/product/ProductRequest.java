package Web_Drink_Store.webstore.dto.product;
import java.math.BigDecimal;
public class ProductRequest {
    private String name; private String description; private String imageUrl; private BigDecimal price; private Integer stockQuantity; private Long categoryId;
    public String getName(){return name;} public void setName(String v){name=v;} public String getDescription(){return description;} public void setDescription(String v){description=v;}
    public String getImageUrl(){return imageUrl;} public void setImageUrl(String v){imageUrl=v;} public BigDecimal getPrice(){return price;} public void setPrice(BigDecimal v){price=v;}
    public Integer getStockQuantity(){return stockQuantity;} public void setStockQuantity(Integer v){stockQuantity=v;} public Long getCategoryId(){return categoryId;} public void setCategoryId(Long v){categoryId=v;}
}
