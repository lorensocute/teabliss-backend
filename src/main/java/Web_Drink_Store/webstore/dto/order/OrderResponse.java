package Web_Drink_Store.webstore.dto.order;
import Web_Drink_Store.webstore.enums.OrderStatus;
import java.math.BigDecimal; import java.time.LocalDateTime; import java.util.List;
public class OrderResponse {
    private Long id; private OrderStatus status; private BigDecimal subtotal; private BigDecimal discountAmount; private BigDecimal totalAmount; private LocalDateTime createdAt; private List<OrderItemResponse> items;
    public OrderResponse(Long id,OrderStatus status,BigDecimal subtotal,BigDecimal discountAmount,BigDecimal totalAmount,LocalDateTime createdAt,List<OrderItemResponse> items){this.id=id;this.status=status;this.subtotal=subtotal;this.discountAmount=discountAmount;this.totalAmount=totalAmount;this.createdAt=createdAt;this.items=items;}
    public Long getId(){return id;} public OrderStatus getStatus(){return status;} public BigDecimal getSubtotal(){return subtotal;} public BigDecimal getDiscountAmount(){return discountAmount;} public BigDecimal getTotalAmount(){return totalAmount;} public LocalDateTime getCreatedAt(){return createdAt;} public List<OrderItemResponse> getItems(){return items;}
}
