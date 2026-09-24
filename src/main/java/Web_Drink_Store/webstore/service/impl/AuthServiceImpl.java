package Web_Drink_Store.webstore.service.impl;

import Web_Drink_Store.webstore.dto.auth.AuthResponse;
import Web_Drink_Store.webstore.dto.auth.LoginRequest;
import Web_Drink_Store.webstore.dto.auth.RegisterRequest;
import Web_Drink_Store.webstore.entity.Cart;
import Web_Drink_Store.webstore.entity.User;
import Web_Drink_Store.webstore.enums.Role;
import Web_Drink_Store.webstore.enums.UserStatus;
import Web_Drink_Store.webstore.exception.BadRequestException;
import Web_Drink_Store.webstore.repository.CartRepository;
import Web_Drink_Store.webstore.repository.UserRepository;
import Web_Drink_Store.webstore.service.AuthService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(
            UserRepository userRepository,
            CartRepository cartRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // =========================
    // ĐĂNG KÝ
    // =========================
    @Override
    @Transactional
    public AuthResponse register(RegisterRequest request) {

        if (request == null
                || request.getFullName() == null
                || request.getFullName().isBlank()
                || request.getEmail() == null
                || request.getEmail().isBlank()
                || request.getPassword() == null
                || request.getPassword().length() < 8) {

            throw new BadRequestException(
                    "Dữ liệu đăng ký không hợp lệ"
            );
        }

        String email =
                request.getEmail()
                        .trim()
                        .toLowerCase();

        if (userRepository.existsByEmail(email)) {

            throw new BadRequestException(
                    "Email đã tồn tại"
            );
        }

        User user = new User();

        user.setFullName(
                request.getFullName().trim()
        );

        user.setEmail(email);

        user.setPhone(
                request.getPhone()
        );

        user.setPassword(
                passwordEncoder.encode(
                        request.getPassword()
                )
        );

        user.setRole(
                Role.CUSTOMER
        );

        user.setStatus(
                UserStatus.ACTIVE
        );

        userRepository.save(user);

        // Mỗi user có một cart
        Cart cart = new Cart();

        cart.setUser(user);

        cartRepository.save(cart);

        return new AuthResponse(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getRole()
        );
    }

    // =========================
    // ĐĂNG NHẬP
    // =========================
    @Override
    public AuthResponse login(LoginRequest request) {

        if (request == null
                || request.getEmail() == null
                || request.getEmail().isBlank()
                || request.getPassword() == null
                || request.getPassword().isBlank()) {

            throw new BadRequestException(
                    "Email và mật khẩu không được để trống"
            );
        }

        String email =
                request.getEmail()
                        .trim()
                        .toLowerCase();

        User user =
                userRepository
                        .findByEmail(email)
                        .orElseThrow(() ->
                                new BadRequestException(
                                        "Email hoặc mật khẩu không đúng"
                                )
                        );

        if (user.getStatus() != UserStatus.ACTIVE) {

            throw new BadRequestException(
                    "Email hoặc mật khẩu không đúng"
            );
        }

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        )) {

            throw new BadRequestException(
                    "Email hoặc mật khẩu không đúng"
            );
        }

        return new AuthResponse(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getRole()
        );
    }
}