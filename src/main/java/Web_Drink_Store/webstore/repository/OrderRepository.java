package Web_Drink_Store.webstore.repository;

import Web_Drink_Store.webstore.entity.Order;
import Web_Drink_Store.webstore.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    // Dùng cho User xem danh sách đơn hàng của mình
    List<Order> findByUserIdOrderByCreatedAtDesc(Long userId);

    // Dùng cho Statistics tính tổng doanh thu
    @Query("""
        SELECT COALESCE(SUM(o.totalAmount), 0)
        FROM Order o
        WHERE o.status = :status
    """)
    BigDecimal sumTotalAmountByStatus(
            @Param("status") OrderStatus status
    );
}