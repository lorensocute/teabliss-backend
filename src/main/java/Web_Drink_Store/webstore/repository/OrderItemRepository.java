package Web_Drink_Store.webstore.repository;
import Web_Drink_Store.webstore.entity.OrderItem; import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
public interface OrderItemRepository extends JpaRepository<OrderItem,Long>{ List<OrderItem> findByOrderId(Long orderId); }
