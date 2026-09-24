package Web_Drink_Store.webstore.service.impl;

import Web_Drink_Store.webstore.dto.cart.CartItemRequest;
import Web_Drink_Store.webstore.dto.cart.CartItemResponse;
import Web_Drink_Store.webstore.dto.cart.CartResponse;
import Web_Drink_Store.webstore.entity.Cart;
import Web_Drink_Store.webstore.entity.CartItem;
import Web_Drink_Store.webstore.entity.Product;
import Web_Drink_Store.webstore.entity.User;
import Web_Drink_Store.webstore.enums.ProductStatus;
import Web_Drink_Store.webstore.exception.BadRequestException;
import Web_Drink_Store.webstore.exception.ResourceNotFoundException;
import Web_Drink_Store.webstore.exception.UnauthorizedException;
import Web_Drink_Store.webstore.repository.CartItemRepository;
import Web_Drink_Store.webstore.repository.CartRepository;
import Web_Drink_Store.webstore.repository.ProductRepository;
import Web_Drink_Store.webstore.repository.UserRepository;
import Web_Drink_Store.webstore.service.CartService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository carts;
    private final CartItemRepository items;
    private final ProductRepository products;
    private final UserRepository users;

    public CartServiceImpl(
            CartRepository carts,
            CartItemRepository items,
            ProductRepository products,
            UserRepository users
    ) {
        this.carts = carts;
        this.items = items;
        this.products = products;
        this.users = users;
    }

    private Cart cart(Long userId) {
        return carts.findByUserId(userId)
                .orElseGet(() -> {

                    User user = users.findById(userId)
                            .orElseThrow(() ->
                                    new ResourceNotFoundException(
                                            "Không tìm thấy user"
                                    )
                            );

                    Cart cart = new Cart();
                    cart.setUser(user);

                    return carts.save(cart);
                });
    }

    private CartResponse map(Cart cart) {

        List<CartItemResponse> responses =
                items.findByCartId(cart.getId())
                        .stream()
                        .map(item -> {

                            BigDecimal lineTotal =
                                    item.getProduct()
                                            .getPrice()
                                            .multiply(
                                                    BigDecimal.valueOf(
                                                            item.getQuantity()
                                                    )
                                            );

                            return new CartItemResponse(
                                    item.getId(),
                                    item.getProduct().getId(),
                                    item.getProduct().getName(),
                                    item.getProduct().getPrice(),
                                    item.getQuantity(),
                                    lineTotal
                            );
                        })
                        .toList();

        BigDecimal total = responses.stream()
                .map(CartItemResponse::getLineTotal)
                .reduce(
                        BigDecimal.ZERO,
                        BigDecimal::add
                );

        return new CartResponse(
                cart.getId(),
                responses,
                total
        );
    }

    @Override
    public CartResponse getCart(Long userId) {
        return map(cart(userId));
    }

    @Override
    @Transactional
    public CartResponse addItem(
            Long userId,
            CartItemRequest request
    ) {

        if (request.getQuantity() == null
                || request.getQuantity() <= 0) {

            throw new BadRequestException(
                    "Số lượng phải > 0"
            );
        }

        Cart cart = cart(userId);

        Product product = products
                .findById(request.getProductId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Không tìm thấy product"
                        )
                );

        if (product.getStatus() != ProductStatus.ACTIVE) {
            throw new BadRequestException(
                    "Sản phẩm không hoạt động"
            );
        }

        CartItem item = items
                .findByCartIdAndProductId(
                        cart.getId(),
                        product.getId()
                )
                .orElseGet(CartItem::new);

        int quantity;

        // Nếu chưa có sản phẩm trong giỏ
        if (item.getId() == null) {
            quantity = request.getQuantity();
        } else {
            // Nếu đã có thì cộng thêm số lượng
            quantity = item.getQuantity()
                    + request.getQuantity();
        }

        if (quantity > product.getStockQuantity()) {
            throw new BadRequestException(
                    "Vượt quá tồn kho"
            );
        }

        item.setCart(cart);
        item.setProduct(product);
        item.setQuantity(quantity);

        items.save(item);

        cart.touch();
        carts.save(cart);

        return map(cart);
    }

    @Override
    @Transactional
    public CartResponse updateItem(
            Long userId,
            Long itemId,
            Integer quantity
    ) {

        if (quantity == null || quantity <= 0) {
            throw new BadRequestException(
                    "Số lượng phải > 0"
            );
        }

        Cart cart = cart(userId);

        CartItem item = items.findById(itemId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Không tìm thấy cart item"
                        )
                );

        // Không được sửa cart item của user khác
        if (!item.getCart()
                .getId()
                .equals(cart.getId())) {

            throw new UnauthorizedException(
                    "Không có quyền"
            );
        }

        // Product đã bị INACTIVE thì không được cập nhật
        if (item.getProduct().getStatus()
                != ProductStatus.ACTIVE) {

            throw new BadRequestException(
                    "Sản phẩm không hoạt động"
            );
        }

        // Không được vượt tồn kho
        if (quantity
                > item.getProduct().getStockQuantity()) {

            throw new BadRequestException(
                    "Vượt quá tồn kho"
            );
        }

        item.setQuantity(quantity);

        items.save(item);

        cart.touch();
        carts.save(cart);

        return map(cart);
    }

    @Override
    @Transactional
    public CartResponse removeItem(
            Long userId,
            Long itemId
    ) {

        Cart cart = cart(userId);

        CartItem item = items.findById(itemId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Không tìm thấy cart item"
                        )
                );

        // Không được xóa cart item của user khác
        if (!item.getCart()
                .getId()
                .equals(cart.getId())) {

            throw new UnauthorizedException(
                    "Không có quyền"
            );
        }

        items.delete(item);

        cart.touch();
        carts.save(cart);

        return map(cart);
    }

    @Override
    @Transactional
    public void clear(Long userId) {

        Cart cart = cart(userId);

        items.deleteByCartId(cart.getId());

        cart.touch();
        carts.save(cart);
    }
}