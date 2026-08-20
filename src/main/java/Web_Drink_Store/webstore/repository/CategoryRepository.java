package Web_Drink_Store.webstore.repository;

import Web_Drink_Store.webstore.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
