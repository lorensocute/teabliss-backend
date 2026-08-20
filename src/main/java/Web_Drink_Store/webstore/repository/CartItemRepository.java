package Web_Drink_Store.webstore.repository;

import Web_Drink_Store.webstore.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}