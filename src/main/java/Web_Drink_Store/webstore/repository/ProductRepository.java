package Web_Drink_Store.webstore.repository;
import Web_Drink_Store.webstore.entity.Product; import Web_Drink_Store.webstore.enums.ProductStatus; import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
public interface ProductRepository extends JpaRepository<Product,Long>{ List<Product> findByStatus(ProductStatus status); List<Product> findByCategoryIdAndStatus(Long categoryId,ProductStatus status); }
