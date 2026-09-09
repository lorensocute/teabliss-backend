package Web_Drink_Store.webstore.repository;
import Web_Drink_Store.webstore.entity.Order; import Web_Drink_Store.webstore.enums.OrderStatus; import org.springframework.data.jpa.repository.JpaRepository; import java.math.BigDecimal; import java.util.List; import org.springframework.data.jpa.repository.Query;
public interface OrderRepository extends JpaRepository<Order,Long>{ List<Order> findByUserIdOrderByCreatedAtDesc(Long userId); @Query("select coalesce(sum(o.totalAmount),0) from Order o where o.status = :status") BigDecimal sumTotalByStatus(OrderStatus status); }
