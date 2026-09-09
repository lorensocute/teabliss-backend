package Web_Drink_Store.webstore.service;
import Web_Drink_Store.webstore.dto.order.*; import Web_Drink_Store.webstore.enums.OrderStatus; import java.util.List;
public interface OrderService { OrderResponse create(Long userId,OrderRequest request); List<OrderResponse> getMyOrders(Long userId); List<OrderResponse> getAll(); OrderResponse updateStatus(Long orderId,OrderStatus status); }
