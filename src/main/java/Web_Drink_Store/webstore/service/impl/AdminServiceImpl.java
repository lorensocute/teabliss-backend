package Web_Drink_Store.webstore.service.impl;

import Web_Drink_Store.webstore.dto.AdminStatsResponse;
import Web_Drink_Store.webstore.entity.Order;
import Web_Drink_Store.webstore.entity.OrderStatus;
import Web_Drink_Store.webstore.repository.OrderRepository;
import Web_Drink_Store.webstore.repository.ProductRepository;
import Web_Drink_Store.webstore.repository.UserRepository;
import Web_Drink_Store.webstore.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public AdminStatsResponse getStats() {
        var orders = orderRepository.findAll();

        BigDecimal revenue = orders.stream()
                .filter(o -> o.getStatus() == OrderStatus.COMPLETED)
                .map(Order::getTotalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return AdminStatsResponse.builder()
                .totalOrders((long) orders.size())
                .totalUsers(userRepository.count())
                .totalProducts(productRepository.count())
                .totalRevenue(revenue)
                .build();
    }
}
