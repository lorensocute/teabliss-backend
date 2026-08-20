package Web_Drink_Store.webstore.service;

import Web_Drink_Store.webstore.dto.OrderRequest;
import Web_Drink_Store.webstore.dto.OrderResponse;
import java.util.List;

public interface OrderService {
    OrderResponse createOrder(String email, OrderRequest request);
    List<OrderResponse> getMyOrders(String email);
    OrderResponse getById(Long id);
    List<OrderResponse> getAll();
    OrderResponse updateStatus(Long id, String status);
}
