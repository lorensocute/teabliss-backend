package Web_Drink_Store.webstore.service.impl;

import Web_Drink_Store.webstore.dto.*;
import Web_Drink_Store.webstore.entity.*;
import Web_Drink_Store.webstore.exception.BadRequestException;
import Web_Drink_Store.webstore.exception.ResourceNotFoundException;
import Web_Drink_Store.webstore.repository.*;
import Web_Drink_Store.webstore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    @Override
    public OrderResponse createOrder(String email, OrderRequest request) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Khong tim thay nguoi dung"));

        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseThrow(() -> new BadRequestException("Gio hang trong"));

        if (cart.getItems().isEmpty()) {
            throw new BadRequestException("Gio hang trong, khong the dat hang");
        }

        Order order = Order.builder()
                .user(user)
                .shippingAddress(request.getShippingAddress())
                .phone(request.getPhone())
                .note(request.getNote())
                .status(OrderStatus.PENDING)
                .build();

        List<OrderItem> orderItems = cart.getItems().stream().map(ci -> OrderItem.builder()
                .order(order)
                .product(ci.getProduct())
                .productName(ci.getProduct().getName())
                .price(ci.getProduct().getPrice())
                .quantity(ci.getQuantity())
                .size(ci.getSize())
                .build()).collect(Collectors.toList());

        BigDecimal total = orderItems.stream()
                .map(i -> i.getPrice().multiply(BigDecimal.valueOf(i.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setItems(orderItems);
        order.setTotalAmount(total);

        orderRepository.save(order);

        cart.getItems().clear();
        cartRepository.save(cart);

        return toResponse(order);
    }

    @Override
    public List<OrderResponse> getMyOrders(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Khong tim thay nguoi dung"));
        return orderRepository.findByUserIdOrderByCreatedAtDesc(user.getId())
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public OrderResponse getById(Long id) {
        return toResponse(findEntity(id));
    }

    @Override
    public List<OrderResponse> getAll() {
        return orderRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public OrderResponse updateStatus(Long id, String status) {
        Order order = findEntity(id);
        try {
            order.setStatus(OrderStatus.valueOf(status.toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Trang thai khong hop le: " + status);
        }
        orderRepository.save(order);
        return toResponse(order);
    }

    private Order findEntity(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Khong tim thay don hang id=" + id));
    }

    private OrderResponse toResponse(Order order) {
        List<OrderItemResponse> items = order.getItems().stream().map(i -> OrderItemResponse.builder()
                .productId(i.getProduct().getId())
                .productName(i.getProductName())
                .price(i.getPrice())
                .quantity(i.getQuantity())
                .size(i.getSize())
                .build()).collect(Collectors.toList());

        return OrderResponse.builder()
                .id(order.getId())
                .customerName(order.getUser().getFullName())
                .items(items)
                .totalAmount(order.getTotalAmount())
                .status(order.getStatus().name())
                .shippingAddress(order.getShippingAddress())
                .phone(order.getPhone())
                .note(order.getNote())
                .createdAt(order.getCreatedAt())
                .build();
    }
}
