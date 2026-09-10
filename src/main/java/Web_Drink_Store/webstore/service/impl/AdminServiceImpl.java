package Web_Drink_Store.webstore.service.impl;

import Web_Drink_Store.webstore.dto.statistics.AdminStatsResponse;
import Web_Drink_Store.webstore.enums.OrderStatus;
import Web_Drink_Store.webstore.repository.OrderRepository;
import Web_Drink_Store.webstore.repository.ProductRepository;
import Web_Drink_Store.webstore.repository.UserRepository;
import Web_Drink_Store.webstore.service.AdminService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public AdminServiceImpl(
            UserRepository userRepository,
            ProductRepository productRepository,
            OrderRepository orderRepository
    ) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public AdminStatsResponse getStatistics() {

        long totalUsers = userRepository.count();
        long totalProducts = productRepository.count();
        long totalOrders = orderRepository.count();

        BigDecimal totalRevenue =
                orderRepository.sumTotalAmountByStatus(
                        OrderStatus.COMPLETED
                );

        return new AdminStatsResponse(
                totalUsers,
                totalProducts,
                totalOrders,
                totalRevenue
        );
    }
}